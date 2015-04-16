package com.cogentsolutions.service;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

import com.cogentsolutions.model.IpFreelyCounter;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class WebService {
	
	private static Logger log = Logger.getLogger(WebService.class);
	static Appender myAppender = new ConsoleAppender(new SimpleLayout());
	
	public static void updateCloudNoImages(int noImages, String user) {
		 log.setLevel(Level.ALL);  
		 log.addAppender(myAppender); 
		
		log.info("Starting webservice client");
		
		IpFreelyCounter counter = new IpFreelyCounter();
		counter.setNoImages(noImages);
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		date.getTime();
		cal.setTime(date);
		counter.setUser(user);
		counter.setCalendar(cal);
		
		String xml = TransformerUtil.counterToXml(counter);
				
		Client client = Client.create();
		
		WebResource web = client.resource("http://cogentcloud.elasticbeanstalk.com/IpFreely/count");
//		WebResource web = client.resource("http://www.cogentClou/IpFreely/count");
		ClientResponse resp = web.post(ClientResponse.class, xml);
		
		String output = resp.getEntity(String.class);
		log.info(output.toString());
		System.out.println(output);
	}
	
	public static String fetchTotal(){
		String totalCount;
		try{
		Client client = Client.create();
		
		WebResource web = client.resource("http://cogentcloud.elasticbeanstalk.com/IpFreely/countTotal");
		
		ClientResponse resp = web.get(ClientResponse.class);
		
		totalCount = resp.getEntity(String.class);
		
		} catch(Exception e){
			totalCount = "Tell Theo you are getting this message";
		}
		return totalCount;
	}

}
