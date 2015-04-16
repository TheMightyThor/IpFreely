package com.cogentsolutions.model;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class IpFreelyCounter {
	
	
	@XmlElement
	public int noImages;
	@XmlElement
	public Calendar calendar;
	@XmlElement
	public String user;
	
	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public int getNoImages() {
		return noImages;
	}
	

	public void setNoImages(int noImages) {
		this.noImages = noImages;
	}


	public Calendar getCalendar() {
		return calendar;
	}


	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
	
	
}
