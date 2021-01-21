package com.ecom.security;

public enum ApplicationUserPermission {
	USER_READ("user:read"),
	USER_WRITE("user:write"),
	USER_UPDATE("user:update"),
	ADMIN_READ("admin:read"),
	ADMIN_WRITE("admin:write"),
	ADMIN_UPDATE("admin:update");
	
	
	
	private final String permission;

	ApplicationUserPermission(String permission){
		this.permission = permission;
	}
	
	public String getPermission() {
		return permission;
	}
	
	
}
