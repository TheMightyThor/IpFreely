package com.cogentsolutions.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.cogentsolutions.model.IPAimage;



		public class FileUtils {
		 
		
			
		    public static ArrayList<IPAimage> getAllImages(File direct, boolean descendIntoSubDirectories) throws IOException {

		    	ArrayList<IPAimage> resultList = new ArrayList<IPAimage>();
		        File[] f = direct.listFiles();
		        
		        for (File file : f) {
		            if (file != null && file.getName().endsWith(".jpg") || file.getName().endsWith(".png")) {
		              
		            	IPAimage ultraImage = new IPAimage();
		            	
		            	ultraImage.setImageDirectory(file.getCanonicalPath());
		            	ultraImage.setImageName(file.getName());
		            	resultList.add(ultraImage);
		            
		            }
		            /* TODO if needed
		            if (descendIntoSubDirectories && file.isDirectory()) {
		                ArrayList<String> tmp = getAllImages(direct, true);
		                if (tmp != null) {
		                    resultList.addAll(tmp);
		                }
		            } */
		        }
		        if (resultList.size() > 0)
		            return resultList;
		        else
		            return null;
		    
		    }

		
		}