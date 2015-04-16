package com.cogentsolutions.ipa;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class ExpirePopupController implements Initializable{
	

	@FXML private Label daysLeftDynamic;

	
	
	public static long calculateDaysLeft() {
		Calendar currentDate = Calendar.getInstance();
	 	Calendar expireDate =  Calendar.getInstance();
	 	expireDate.set(2015, 10, 17);
	 	
	 	long daysUntilExpire = (expireDate.getTimeInMillis() - currentDate.getTimeInMillis())/(60 * 60 * 24 * 1000);
	
	 	return daysUntilExpire;
		
	}
	
	public void initialize(URL location, ResourceBundle resources) {	
	   
		Calendar currentDate = Calendar.getInstance();
	 	Calendar expireDate =  Calendar.getInstance();
	 	expireDate.set(2015, 10, 17);
	 	
	 	long daysUntilExpire = (expireDate.getTimeInMillis() - currentDate.getTimeInMillis())/(60 * 60 * 24 * 1000);

	 	
	 	if (currentDate.before(expireDate)) {
	 		daysLeftDynamic.setText(""+daysUntilExpire);
	 	} else {
	 		daysLeftDynamic.setText("Expired");
	 	}
	 	
	 	
	}

}
