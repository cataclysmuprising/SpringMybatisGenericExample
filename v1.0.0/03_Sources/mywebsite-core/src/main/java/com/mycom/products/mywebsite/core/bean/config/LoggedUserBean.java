package com.mycom.products.mywebsite.core.bean.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LoggedUserBean implements UserDetails {
	private static final long serialVersionUID = 1260667404772902490L;
	private UserBean user;
	private List<String> roles;

	public LoggedUserBean(UserBean user, List<String> roles) {
		this.user = user;
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// Login in with LoginId , not userName. So , this must set with email
		// If you don't understand , don't touch this.. :)
		return user.getLoginId();
	}

	// use this method to get user name
	public String getRealName() {
		return user.getName();
	}

	public int getId() {
		return user.getId();
	}

	public int getContentId() {
		return user.getContentId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
