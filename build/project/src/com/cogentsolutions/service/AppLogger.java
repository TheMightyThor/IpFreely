package com.cogentsolutions.service;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class AppLogger {
	
	private static Logger appLog = Logger.getLogger(AppLogger.class);
	
	public static Logger getLogger(){
		return appLog;
	}
		
	public static Logger initialize(){
		
		FileAppender fa = new FileAppender();
		  
		fa.setFile(System.getProperty("user.dir") +"/logs/log.out");
		
		fa.setLayout(new  PatternLayout("%C{1} %L [%t] %d{dd MMM,yyyy HH:mm:ss.SSS} %-5p - %m%n"));
		
		fa.setThreshold(Level.ALL);
				
		fa.setAppend(true);
				
		fa.activateOptions();
		
		Appender console = new ConsoleAppender(new PatternLayout("%C{1} %L [%t] %d{dd MMM,yyyy HH:mm:ss.SSS} %-5p - %m%n"));
		
		appLog.addAppender(fa);
		appLog.addAppender(console);
		
		appLog.info("Logger Initialized");
		
		return appLog;	
	}
}
