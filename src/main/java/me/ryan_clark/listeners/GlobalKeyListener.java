package me.ryan_clark.listeners;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import me.ryan_clark.app.Hitmarker;

public class GlobalKeyListener implements NativeKeyListener {

	Hitmarker window;

	public GlobalKeyListener(Hitmarker hm) {
		window = hm;
	}

	public void nativeKeyPressed(NativeKeyEvent e) {
		window.hit();

		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
			try {
				GlobalScreen.unregisterNativeHook();
				System.exit(1);
			} catch (NativeHookException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
	}
}