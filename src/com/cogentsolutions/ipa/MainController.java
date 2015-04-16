package com.cogentsolutions.ipa;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.cogentsolutions.model.IPAimage;
import com.cogentsolutions.service.AppLogger;
import com.cogentsolutions.service.FileUtils;
import com.cogentsolutions.service.OverlayImage;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class MainController implements Initializable{
	@FXML
	private AnchorPane pane;
	@FXML 
	private Label outPutDirectory;
	@FXML 
	private Label garmInputDirectory;
	@FXML 
	private Label imageInputDirectory;
	@FXML
	protected ImageView garmImage;
	@FXML
	protected ImageView imageImage;
	@FXML
	protected ImageView combinedImage;
	@FXML
	protected TextField user;
	@FXML
	protected static ProgressBar progressBar = new ProgressBar();

	
	protected boolean garmInputDirectorySet = false;
	protected boolean outPutDirectorySet = false;
	protected boolean imageInputDirectorySet = false;
	protected boolean usernameSet = false;
	
	protected long TIMEOUT = 2000;
	private Point2D dragAnchor;
	protected int xAxis = 200;
	protected int yAxis = 200;
	protected int xSize = 200;
	protected int ySize = 200;
    private double initX;
    private int x;
    private int y;
    private double initY;
    private double endY;
    private double endX;
	protected String currentImageDirectory;
	protected String firstGarmImage;
	private String outPutDirectoryString;
	private String garmInputDirectoryString;
	private String imageInputDirectoryString;
	
	private Logger log = AppLogger.getLogger();
	
	public static void updateProgressBar(double thusfar, double total){
		
		double progress = thusfar / total;
		
		progressBar.setProgress(progress);
		
	}
	
	@FXML protected void setUser() {
		user.setText(user.getText());
		usernameSet = true;
		log.info("User Set= "+ user.getText());
	}

    @FXML protected void setOutPutDirectory(MouseEvent event) throws IOException {
    
    	Stage stage = new Stage();
    	stage.centerOnScreen();
    	stage.setTitle("Out Put Directory");
    	DirectoryChooser directoryChooser = new DirectoryChooser();
    	outPutDirectoryString = directoryChooser.showDialog(stage).getPath();
    	outPutDirectory.setText(outPutDirectoryString);
    	log.info("Combined Directory= " + outPutDirectoryString);
    	}
       
    	
    @FXML protected void setGarmDirectory(MouseEvent event) throws IOException {
    	
    	Stage stage = new Stage();
    	stage.centerOnScreen();
    	stage.setTitle("Garment Directory Chooser");
    	DirectoryChooser directoryChooser = new DirectoryChooser();
    	garmInputDirectoryString = directoryChooser.showDialog(stage).getPath();
    	garmInputDirectory.setText(garmInputDirectoryString); 			
    	log.info("Garm Directory= " + garmInputDirectoryString);		
    	
    	ArrayList<IPAimage> garmImageList = com.cogentsolutions.service.FileUtils.getAllImages(new File(garmInputDirectoryString), false);
    	firstGarmImage = garmImageList.get(0).getImageDirectory();
    	InputStream is = new FileInputStream(garmImageList.get(0).getImageDirectory());
    	final Image firstGarmImage = new Image(is);
    	garmImage.setImage(firstGarmImage);
    	garmImage.setFitHeight(firstGarmImage.getHeight());
    	garmImage.setFitWidth(firstGarmImage.getWidth());
    	log.info("First Garm Image Set");

    	
    	}
    @FXML protected void setImageDirectory(MouseEvent event) throws IOException {
    	
    	Stage stage = new Stage();
    	stage.centerOnScreen();
    	stage.setTitle("Image Directory Chooser");
    	DirectoryChooser directoryChooser = new DirectoryChooser();
    	imageInputDirectoryString = directoryChooser.showDialog(stage).getPath();
    	imageInputDirectory.setText(imageInputDirectoryString);
    	log.info("Image Directory Set= " + imageInputDirectoryString);
    	
    	
    	ArrayList<IPAimage> imageImageList = FileUtils.getAllImages(new File(imageInputDirectoryString), false);
    	currentImageDirectory = imageImageList.get(0).getImageDirectory();
    	InputStream is = new FileInputStream(imageImageList.get(0).getImageDirectory());
    	Image firstImageImage = new Image(is);
    	imageImage.setImage(firstImageImage);
    	imageImage.setFitHeight(firstImageImage.getHeight()*.5);
    	imageImage.setFitWidth(firstImageImage.getWidth()*.5);
    	log.info("First Graphic Image Set");
    	
    	imageImage.setOnMouseDragged(new EventHandler<MouseEvent>() {

             public void handle(MouseEvent me) {

                 double dragX = me.getSceneX() - dragAnchor.getX();

                 double dragY = me.getSceneY() - dragAnchor.getY();

                 //calculate new position of the circle

                 double newXPosition = initX + dragX;

                 double newYPosition = initY + dragY;
                 imageImage.setTranslateX(newXPosition);
                 imageImage.setTranslateY(newYPosition);
             }
         });

    	imageImage.setOnMousePressed(new EventHandler<MouseEvent>() {

             public void handle(MouseEvent me) {

                  //when mouse is pressed, store initial position

                 initX = imageImage.getTranslateX();

                 initY = imageImage.getTranslateY();
                 
                 dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());  
             }

         });
       	
    	imageImage.setOnMouseReleased(new EventHandler<MouseEvent>() {
       		
       		public void handle(MouseEvent me) {
       			endX = imageImage.getTranslateX();

                endY = imageImage.getTranslateY();

       		}
       	});
       	pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
       		
       		public void handle(KeyEvent ke) {
       	    
       			if (ke.getText().equalsIgnoreCase("b")) {
       				imageImage.setFitHeight(imageImage.getFitHeight() + 5);
       				imageImage.setFitWidth(imageImage.getFitWidth() + 5 );
       			}
       			if (ke.getText().equalsIgnoreCase("s")) {
       				imageImage.setFitHeight(imageImage.getFitHeight() - 5);
       				imageImage.setFitWidth(imageImage.getFitWidth() - 5 );
       			}
       		}
       	});
    }
    @FXML protected void setImages(MouseEvent event) throws IOException {
    	x = (int) (endX);
		y = (int)(endY);
		int size = (int)(imageImage.getFitHeight());
		
    	OverlayImage.setImage(x, y, size, size, firstGarmImage, currentImageDirectory);
    	log.info("Set Images "+
    	" x= " + x +
    	" y= " + y +
    	" size= " + size);
    	InputStream is = new FileInputStream(OverlayImage.TEMP_IMAGE_PATH + "/tempimage.jpg");
    	Image combinedSetImage = new Image(is);
    	combinedImage.setImage(combinedSetImage);
    	combinedImage.setFitHeight(combinedImage.getFitHeight());
    	
    	
    }
    @FXML protected void startImageProcess(MouseEvent event) throws IOException {
    	
    	if (garmInputDirectorySet || imageInputDirectorySet || outPutDirectorySet || usernameSet ) {
    	log.info("Start Image Process");
	    	Runnable IpFreely = new Runnable(){
				public void run() {
	    	    	
					x = (int) (initX);
					y = (int)(initY);
					double noOfImages = 0;
					int size = (int)(imageImage.getFitHeight());
					
						try {
							noOfImages = OverlayImage.start(x, y, size, size, outPutDirectoryString, garmInputDirectoryString, imageInputDirectoryString);
						  	log.info("Start Processing "+
						  	    	" x= " + x +
						  	    	" y= " + y +
						  	    	" size= " + size);
						} catch (IOException e) {
							log.error(e.getLocalizedMessage() + e.getMessage());
						}
					//	WebService.updateCloudNoImages(noOfImages, user.getText());
						log.info("Number of Images Processed = " + noOfImages 
								+ " User = " + user.getText());
					}
	    		};
    	
    	ExecutorService ex = Executors.newCachedThreadPool();
    	ex.execute(IpFreely);
    	
    	} else {
    	log.info("A Required Field Not Set");
    	Stage popUpStage = new Stage();
		Parent popup = FXMLLoader.load(getClass().getResource("MissingDirectoryPopup.fxml"));
		popUpStage.setTitle("MissingDirectory");
		popUpStage.setScene(new Scene(popup));
		
		if(outPutDirectorySet != true){
			MissingDirectoryPopUpController.combinedImageDirectoryNotSet();
			log.info("Out put directory not set");
			}else if(imageInputDirectorySet != true ){
				MissingDirectoryPopUpController.imageDirectoryNotSet();
				log.info("Image directory not set");
			}else if(garmInputDirectorySet != true ){
				MissingDirectoryPopUpController.garmDirectoryNotSet();
				log.info("Garm directory not set");
			}else if (usernameSet !=true){
				MissingDirectoryPopUpController.userNameNotSet();
				log.info("User name not set");
			} else {
				MissingDirectoryPopUpController.messedUp();
				log.error("Something is not right up " + Class.class.getName());
			}
		popUpStage.show();
    	}
    }

  

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}

