package com.example.exception;

import java.text.MessageFormat;

public class AddressAlreadyAssignedException extends RuntimeException {
	public AddressAlreadyAssignedException(final Long addressid, final Long employeeid) {
		super(MessageFormat.format("Item: {0} is already assigned to cart: {1} ", addressid, employeeid));
	}
}
