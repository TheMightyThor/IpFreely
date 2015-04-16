package com.cogentsolutions.ipa;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.cogentsolutions.service.AppLogger;

public class IpaLoader extends Application {
	
	private static final Logger log = AppLogger.initialize();
	
	@Override
    public void start(Stage stage) throws Exception {
		
	    log.info("IpaLoader - start");   

	    FXMLLoader.load(IpaLoader.class.getResource("ExpirePopUp.fxml"));
		Parent popup = FXMLLoader.load(getClass().getResource("ExpirePopUp.fxml"));
		stage.setTitle("Days Left");
		stage.setScene(new Scene(popup)); 
		stage.show();		
		
		long daysLeft = ExpirePopupController.calculateDaysLeft();
		log.info("Days Left = " + daysLeft );	
		if (daysLeft <=0) {
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {	
				@Override
				public void run() {
					System.exit(0);
				}
			};
			
			timer.schedule(task, 5000);
			
		} else {
				
		Stage parentStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("IPA.fxml"));
        parentStage.setFullScreen(true);
        parentStage.setTitle("IPA");
        parentStage.setScene(new Scene(root));
        parentStage.show();
        stage.toFront();
       	}	   
        
    }
    
    
   public static void main(String[] args) {
    	IpaLoader.launch();
    }


}

