package com.mycom.products.mywebsite.core.security;

import org.springframework.context.ApplicationEvent;

public class AuthorityChangeEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	private String eventType;

	public AuthorityChangeEvent(Object source, String eventType) {
		super(source);
		this.eventType = eventType;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

}
