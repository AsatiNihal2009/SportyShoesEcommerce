package com.ecom.security;
import com.google.common.collect.Sets;


import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum ApplicationUserRole {
	USER(Sets.newHashSet(ApplicationUserPermission.USER_READ,ApplicationUserPermission.USER_WRITE,ApplicationUserPermission.USER_UPDATE)),
	ADMIN(Sets.newHashSet(ApplicationUserPermission.ADMIN_READ,ApplicationUserPermission.ADMIN_WRITE,ApplicationUserPermission.ADMIN_UPDATE)),
	TEMPADMIN(Sets.newHashSet(ApplicationUserPermission.ADMIN_READ));
	
	private final  Set<ApplicationUserPermission> permission;

	ApplicationUserRole(Set<ApplicationUserPermission> permission){
		this.permission = permission;
	}
	
	public Set<ApplicationUserPermission> getPermission(){
		return permission;
	}
	
	public Set<SimpleGrantedAuthority> getGrandtedAuthorities(){
		
		Set<SimpleGrantedAuthority> permissions = getPermission().stream()
		.map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
		
		permissions.add( new SimpleGrantedAuthority("ROLE_"+this.name()));
		
		return permissions;
	}
	
}
