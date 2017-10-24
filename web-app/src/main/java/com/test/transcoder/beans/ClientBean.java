package com.test.transcoder.beans;


import com.test.common.data.entity.ClientEntity;

/**
 * Created by ls on 11/16/16.
 */
public class ClientBean {

	private Long id;

	private String username;
	private String firstName;
	private String lastName;

	public ClientBean() {
	}

	public ClientBean(ClientEntity clientEntity) {
		this.id = clientEntity.getId();
		this.username = clientEntity.getUsername();
		this.firstName = clientEntity.getFirstName();
		this.lastName = clientEntity.getLastName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
