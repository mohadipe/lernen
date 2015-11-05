package de.mohadipe.dynastie.ui.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.mohadipe.dynastie.ui.DynastieUI;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration appConfig = new LwjglApplicationConfiguration();
		appConfig.title = DynastieUI.TITLE;
		appConfig.vSyncEnabled = true;
		appConfig.useGL30 = true;
//		appConfig.fullscreen = true;
		appConfig.width = 1024;
		appConfig.height = 864;
		new LwjglApplication(new DynastieUI(), appConfig);
	}
}
