package com.chandra.bus.payload.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class SignupRequest {
	@NotBlank
	@Size(min = 3, max = 50)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String firstName;

	@NotBlank
	@Size(max = 120)
	private String lastName;

	@NotBlank
	@Size(max = 120)
	private String mobileNumber;

	private Set<String> role;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;
}
