package com.cogentsolutions.service;

public class SkuParser {
	
	public static String skuCreator(String imageName, String garmName) {
		
		String sku = imageName.trim() + "_" + garmName.trim() + ".jpg";
		
		return sku;
	}

}
