package com.example.demo.controller;

import java.util.LinkedHashMap;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;



import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



import com.example.demo.model.Buyer;
import com.example.demo.model.Orders;
import com.example.demo.repos.BuyerRepo;
import com.example.demo.services.BuyerService;
//import com.example.demo.validator.BuyerValidator;

@RestController
@RequestMapping(value="/buyer")
@AllArgsConstructor(onConstructor=@__(@Autowired))
@NoArgsConstructor
@CrossOrigin(origins="*")
public class BuyerController {
	
	private BuyerService uServ;
	/*
	@InitBinder()
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new BuyerValidator());
	}*/

	@PostMapping("/register")
	public ResponseEntity<Buyer> createUser(@RequestBody LinkedHashMap<String, String> buyer){
		System.out.println(buyer);
		Buyer u = new Buyer(buyer.get("first"), buyer.get("last"), buyer.get("email"), buyer.get("pass"));
		u = uServ.registerUser(u);
		
		if(u != null) {
			return new ResponseEntity<Buyer>(u, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<Buyer>(u, HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<Buyer> loginUser(@RequestBody LinkedHashMap<String, String> buyer){
		Buyer u = uServ.loginUser(buyer.get("email"), buyer.get("pass"));
		if(u == null) {
			return new ResponseEntity<Buyer>(u, HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<Buyer>(u, HttpStatus.OK);
	}
	//I can change this to find user by id if needed
	@GetMapping("/{email}")
	public ResponseEntity<Buyer> getUser(@PathVariable("email")String email){
		Buyer u = uServ.displayUser(email);
		if(u == null) {
			return new ResponseEntity<Buyer>(u, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Buyer>(u, HttpStatus.OK);
	}
	
	@PostMapping("/reviewOrders")
	public ResponseEntity<List<Orders>> reviewOrders(@RequestBody LinkedHashMap<String, String> buyer){
		List<Orders> orders =uServ.getBuyerOrders(Integer.parseInt(buyer.get("buyerid")));
		if(orders == null) {
			return new ResponseEntity<List<Orders>>(orders, HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
	}
	
	/*
	@PostMapping("/validate")
	public ResponseEntity<String> validateUser(@RequestBody @Valid Buyer u, BindingResult result){
		if(result.hasErrors()) {
			System.out.println("Errors were had");
			System.out.println(result.getFieldError());
			return new ResponseEntity<String>(result.getFieldError().getCode() + " " + result.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<String>("User is valid", HttpStatus.OK);
	}*/
}