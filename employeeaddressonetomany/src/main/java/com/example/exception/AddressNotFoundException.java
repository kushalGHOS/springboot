package com.example.exception;

import java.text.MessageFormat;

public class AddressNotFoundException extends RuntimeException {
	public AddressNotFoundException(final Long id) {
		super(MessageFormat.format("Could not find address with id: {0}", id));
	}

	public AddressNotFoundException(final String street) {
		super(MessageFormat.format("Could not find address with street name: {0}", street));
	}

}
