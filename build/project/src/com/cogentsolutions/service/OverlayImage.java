package com.cogentsolutions.service;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;

import com.cogentsolutions.ipa.MainController;
import com.cogentsolutions.model.IPAimage;



public class OverlayImage {
	public static final File TEMP_IMAGE_PATH = new File(System.getProperty("user.dir") + "/temp/");
	
	

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	public static double start(int xAxis, int yAxis, int xSize, int ySize, String outPutPath, String garmInputPath, 
			String imageInputPath) throws IOException{
		int adjustedY = 0;
		int adjustedX = 0;
		int adjustedXSize = 0;
		int adjustedYSize = 0;
		double numberOfImagesProcessed = 0;
		// TODO Auto-generated method stub
		File imInputPath = new File(imageInputPath); // base path of the images
		File gInputPath = new File(garmInputPath);//base path for garment images
		File combinedPath = new File(outPutPath);  //output path for combined images
		ArrayList<IPAimage> garms = FileUtils.getAllImages(gInputPath, false);
		ArrayList<IPAimage> images = FileUtils.getAllImages(imInputPath, false);
		double totalNumberOfImages = garms.size() * images.size();
		System.out.println(totalNumberOfImages);

		
        // load source images

		Iterator<IPAimage> imIter = images.iterator();
		

		while(imIter.hasNext()){
			IPAimage imageUltraImage = new IPAimage();
			imageUltraImage = imIter.next();
			BufferedImage image=null;
			BufferedImage overlay =null;
			overlay= ImageIO.read(new File(imageUltraImage.getImageDirectory()));
    
        	Iterator<IPAimage> garmIter = garms.iterator();
       
        	while(garmIter.hasNext()){
        		IPAimage garmUltraImage = garmIter.next();
        		String imageName = garmUltraImage.getImageName();
	            image = ImageIO.read(new File(garmUltraImage.getImageDirectory()));
	        
	            int w = image.getWidth();
	            int h = image.getHeight();
	    		if(imageName.equals("Hoodie.jpg")){
					adjustedY = yAxis + 150;
					adjustedX = xAxis + 25;
					adjustedXSize = xSize + 20;
					adjustedYSize = ySize + 20;
				} else if(imageName.equals("Juniors.jpg")){
					adjustedY = yAxis + 70;
					adjustedX = xAxis + 60;
					adjustedXSize = xSize + 30;
					adjustedYSize = ySize + 30;
				}else if(imageName.equals("V-Neck.jpg")){
					adjustedY = yAxis + 110;
					adjustedX = xAxis + 110;
					adjustedXSize = xSize + 20;
					adjustedYSize = ySize + 20;
				}else if(imageName.equals("MensFront.jpg")){
					adjustedY = yAxis + 100;
					adjustedX = xAxis + 185;
					adjustedXSize = xSize + 100;
					adjustedYSize = ySize + 100;
				}else if(imageName.equals("RetroShirt.jpg")){
					adjustedY = yAxis + 110;
					adjustedX = xAxis + 90;
					adjustedXSize = xSize + 60;
					adjustedYSize = ySize + 60;
				} else if(imageName.equals("Tank.jpg")){
					adjustedY = yAxis + 100;
					adjustedX = xAxis;
					adjustedXSize = xSize;
					adjustedYSize = ySize;
				}else if(imageName.equals("Polo.jpg")){
					adjustedY = yAxis + 150;
					adjustedX = xAxis + 160;
					adjustedXSize = xSize;
					adjustedYSize = ySize;
				} else if(imageName.equals("MensVNeck.jpg")){
					adjustedY = yAxis + 75;
					adjustedX = xAxis + 75;
					adjustedXSize = xSize;
					adjustedYSize = ySize;
				} else if(imageName.equals("Thermal.jpg")){
					adjustedY = yAxis + 75;
					adjustedX = xAxis + 75;
					adjustedXSize = xSize + 30;
					adjustedYSize = ySize + 30;
				}
	    		
	    		else {
					adjustedX = xAxis;
					adjustedY = yAxis;
					adjustedYSize = ySize;
					adjustedXSize = xSize;
				}
	            BufferedImage scaledImg = Scalr.resize(overlay, Method.QUALITY, adjustedXSize, adjustedYSize);
	            BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

				// paint both images, preserving the alpha channels
				Graphics g = combined.getGraphics();
				g.drawImage(image, 0, 0, null);
		
				
				g.drawImage(scaledImg, adjustedX, adjustedY, null);

				//TODO make dynamic SKU
				String sku = SkuParser.skuCreator(imageUltraImage.getImageName(), garmUltraImage.getImageName());
				ImageIO.write(combined, "PNG", new File(combinedPath,   sku));
				numberOfImagesProcessed += 1;
				MainController.updateProgressBar(numberOfImagesProcessed, totalNumberOfImages);
				
        }		
        }
		return numberOfImagesProcessed;
	}
	
	public static void setImage(int xAxis, int yAxis, int xSize, int ySize, String garmImageDirectory, 
			String imageImageDirectory) throws IOException{
		
		
			BufferedImage image=null;
			BufferedImage overlay =null;
			overlay= ImageIO.read(new File(imageImageDirectory));
            image = ImageIO.read(new File(garmImageDirectory));
	        
            int w = image.getWidth();
            int h = image.getHeight();
            BufferedImage scaledImg = Scalr.resize(overlay, Method.QUALITY, xSize, ySize);
            BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

			// paint both images, preserving the alpha channels
			Graphics g = combined.getGraphics();
			g.drawImage(image, 0, 0, null);
			g.drawImage(scaledImg, xAxis, yAxis, null);
			ImageIO.write(combined, "PNG", new File(TEMP_IMAGE_PATH,   "/tempimage.jpg"));
			
               
	}
}