package de.mohadipe.ui.test.robot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;


public class IconDateiTest {

	@Test
	public void oeffneDatei() throws IOException {
		String fileName = "../../../icons/BlueStacks.bmp";
		ImageIO.read(getClass().getResource(fileName));
	}
	
	@Test
	public void rgbBlueStacksIcon() throws IOException {
		String fileName = "../../../icons/rot.bmp";
		BufferedImage icon = ImageIO.read(getClass().getResource(fileName));
		System.out.println("Icon Width: " + icon.getWidth() + " Icon Height: " + icon.getHeight());
		// width: 400 height: 353
	}
	
//	@Test
//	public void rgbScreenshot() throws AWTException {
//		GoWRobot goWRobot = new GoWRobot();
//		BufferedImage currentScreen = goWRobot.createScreenCapture(new Rectangle(
//				Toolkit.getDefaultToolkit().getScreenSize()));
//		System.out.println("Icon Width: " + currentScreen.getWidth() + " Icon Height: " + currentScreen.getHeight());
//		// width: 1920 height: 1080
//	}
	
	@Test
	public void findeGowInBluestacks() throws IOException {
		String fileName = "../../../icons/gow.bmp";
		BufferedImage icon = ImageIO.read(getClass().getResource(fileName));
	}
}
