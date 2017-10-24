package com.test.common.security;

/**
 * Created by ls on 11/15/16.
 */
public enum UserType {
	ADMIN("ADMIN"),
	CLIENT("CLIENT");

	private String label;

	UserType(String label){
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public static UserType getByLabel(String label) {
		for (UserType userType : values()) {
			if (userType.getLabel().equals(label)) {
				return userType;
			}
		}
		return null;
	}

}
