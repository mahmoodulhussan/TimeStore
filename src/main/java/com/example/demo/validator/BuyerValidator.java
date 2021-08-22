/*package com.example.demo.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.demo.model.Buyer;

//public class BuyerValidator implements Validator {
/*
	@Override
	public boolean supports(Class<?> clazz) {
		return Buyer.class.equals(clazz);
	}
	
	//if the object passes the support method, then the validate method is called
	@Override
	public void validate(Object target, Errors errors) {
		//Going to check to see that the inputted user object contains a first name
		//takes in the errors object, the attribute we want to validate
		//The error "status code"
		//The message we want to see if something goes wrong
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.empty", "The user must have a first name");
		Buyer u = (Buyer)target;
		if(u.getId() < 1) {
			//rejectValue is taking the name of the attribute we want to check
			//A error "status code"
			//the message we want to print if the error occurs
			errors.rejectValue("id", "invalid.id", "User id should be a positive number");
		}
	}

}*/