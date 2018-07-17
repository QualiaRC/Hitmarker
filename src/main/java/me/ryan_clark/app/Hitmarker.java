package me.ryan_clark.app;

import java.awt.MouseInfo;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import me.ryan_clark.listeners.GlobalKeyListener;
import me.ryan_clark.listeners.GlobalMouseListener;


public class Hitmarker extends Application {

	private ImageView hm;
	private MediaPlayer mediaPlayer;
	private Stage window;
	private Timer timer;
	private Media sound;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		// Creating a group
		Group root = new Group();
		
		// Adding image to group
		hm = new ImageView(new Image("hitmarker.png"));
		hm.setVisible(false);
		root.getChildren().add(hm);

		// Create a scene using the root group
		Scene scene = new Scene(root, 20, 20);
		scene.setFill(Color.TRANSPARENT);

		// Setup the stage, and display it
		stage.setScene(scene);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setAlwaysOnTop(true);
		stage.show();
		
		// Important
		stage.setOnCloseRequest(event -> {
			System.exit(0);
		});
		
		// An attempt to not spam the console
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.WARNING);
		logger.setUseParentHandlers(false);
		
		// NativeHook + Listeners
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			System.exit(1);
		}
		GlobalScreen.addNativeKeyListener(new GlobalKeyListener(this));
		GlobalScreen.addNativeMouseListener(new GlobalMouseListener(this));
		
		window = stage;
		timer = new Timer(hm);
		timer.start();
		sound = new Media(getClass().getClassLoader().getResource("sound.mp3").toString());
	}
	
	// Called in key/mouse listeners
	// Plays sound and resets display timer in timer thread
	// for showing/hiding the image.
	public void hit() {
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
		window.setX(MouseInfo.getPointerInfo().getLocation().getX() - 10);
		window.setY(MouseInfo.getPointerInfo().getLocation().getY() - 10);
		timer.resetTime();
	}
}
