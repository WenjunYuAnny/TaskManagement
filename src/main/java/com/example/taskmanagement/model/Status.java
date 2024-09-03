package com.example.taskmanagement.model;

public enum Status {
	CREATED("created"),
	STARTED("started"),
	COMPLETED("started");
	
	private final String value;
	
	Status(String value) {
		this.value = value;
	}
	
	public String toString() {
		return value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static Status fromString(String value) {
		for (Status status : Status.values()) {
			if (status.value.equalsIgnoreCase(value)) {
				return status;
			}
		}
		throw new IllegalArgumentException("Not valid status");
	}
}
