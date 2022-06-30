package com.chandra.bus.payload.request;

import com.chandra.bus.model.user.UserRoles;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRoleRequest {
	@ApiModelProperty(hidden = true)
	private Long id;

	private UserRoles role;

	public UserRoleRequest(UserRoles role) {
		this.role = role;
	}
}