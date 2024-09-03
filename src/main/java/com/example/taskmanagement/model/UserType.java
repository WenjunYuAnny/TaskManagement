package com.example.taskmanagement.model;

public enum UserType {
	MANAGER("manager"),
	MEMBER("member");
	
	private final String value;
	
	UserType(String value) {
		this.value = value;
	}
	
	public String toString() {
		return value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static UserType fromString(String value) {
		for (UserType type : UserType.values()) {
			if (type.value.equalsIgnoreCase(value)) {
				return type;
			}
		}
		throw new IllegalArgumentException("Not valid user type");
	}
}

