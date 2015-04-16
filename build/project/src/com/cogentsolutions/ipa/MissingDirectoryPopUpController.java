package com.cogentsolutions.ipa;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MissingDirectoryPopUpController implements Initializable {
	
	@FXML private static Label notSetDirectory;
	
	public static void imageDirectoryNotSet(){
		notSetDirectory.setText("Image Directory Not Set");
	}
	public static void combinedImageDirectoryNotSet() {
		notSetDirectory.setText("Combined Image Directory Not Set");
	}
	public static void garmDirectoryNotSet() {
		notSetDirectory.setText("Garm Directory Not Set");
	}
	public static void userNameNotSet(){
		notSetDirectory.setText("Give me your damn name");
	}
	public static void fuckedUp(){
		notSetDirectory.setText("If you see this something is really fucked up");
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
