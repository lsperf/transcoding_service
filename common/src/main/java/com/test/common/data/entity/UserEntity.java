package com.test.common.data.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ls on 8/20/16.
 */

@MappedSuperclass
public class UserEntity extends AbstractEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected Long id;

	@Column(name = "username")
	protected String username;

	@Column(name = "password")
	protected String password;

	@Column(name = "first_name")
	protected String firstName;

	@Column(name = "last_name")
	protected String lastName;

	@Column(name = "logged_in_at")
	protected Date loggedInAt;

	@Column(name = "last_active_at")
	protected Date lastActiveAt;

	@Column(name = "authorities")
	private String authorities;

	public UserEntity() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Date getLoggedInAt() {
		return loggedInAt;
	}

	public void setLoggedInAt(Date loggedInAt) {
		this.loggedInAt = loggedInAt;
	}

	public Date getLastActiveAt() {
		return lastActiveAt;
	}

	public void setLastActiveAt(Date lastActiveAt) {
		this.lastActiveAt = lastActiveAt;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
}
