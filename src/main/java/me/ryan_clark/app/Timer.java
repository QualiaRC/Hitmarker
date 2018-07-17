package me.ryan_clark.app;

import javafx.scene.image.ImageView;

public class Timer extends Thread {	
	ImageView image;
	
	private int time;
	
	public Timer(ImageView hm) {
		image = hm;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(time <= 0) {
				image.setVisible(false);
			} else {
				time--;
			}
		}

	}
	public void resetTime() {
		image.setVisible(true);
		time = 100;
	}
}
