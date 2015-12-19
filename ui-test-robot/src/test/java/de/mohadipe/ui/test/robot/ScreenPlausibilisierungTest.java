package de.mohadipe.ui.test.robot;

import java.awt.image.BufferedImage;

import org.junit.Assert;
import org.junit.Test;

import de.mohadipe.ui.test.robot.util.BilderLaden;

public class ScreenPlausibilisierungTest {
	@Test
	public void koordinateXAusserhalbDesScreen() {
		Koordinaten2D koordinaten2d = new Koordinaten2D();
		koordinaten2d.x = 1921;
		koordinaten2d.y = 1080;
		BufferedImage screen = new BilderLaden(null)
				.ladeScreenShotFoEVerknuepfung();
		ScreenPlausibilisierung screenPlausibilisierung = new ScreenPlausibilisierung(
				screen);
		Assert.assertFalse(screenPlausibilisierung
				.isKoordinateAufScreen(koordinaten2d));
	}
	
	@Test
	public void koordinateYAusserhalbDesScreen() {
		Koordinaten2D koordinaten2d = new Koordinaten2D();
		koordinaten2d.x = 1920;
		koordinaten2d.y = 1081;
		BufferedImage screen = new BilderLaden(null)
				.ladeScreenShotFoEVerknuepfung();
		ScreenPlausibilisierung screenPlausibilisierung = new ScreenPlausibilisierung(
				screen);
		Assert.assertFalse(screenPlausibilisierung
				.isKoordinateAufScreen(koordinaten2d));
	}
	
	@Test
	public void koordinateXYAusserhalbDesScreen() {
		Koordinaten2D koordinaten2d = new Koordinaten2D();
		koordinaten2d.x = 1921;
		koordinaten2d.y = 1081;
		BufferedImage screen = new BilderLaden(null)
				.ladeScreenShotFoEVerknuepfung();
		ScreenPlausibilisierung screenPlausibilisierung = new ScreenPlausibilisierung(
				screen);
		Assert.assertFalse(screenPlausibilisierung
				.isKoordinateAufScreen(koordinaten2d));
	}
	
	@Test
	public void koordinateYAusserhalbKleinerNullDesScreen() {
		Koordinaten2D koordinaten2d = new Koordinaten2D();
		koordinaten2d.x = 1920;
		koordinaten2d.y = -1;
		BufferedImage screen = new BilderLaden(null)
				.ladeScreenShotFoEVerknuepfung();
		ScreenPlausibilisierung screenPlausibilisierung = new ScreenPlausibilisierung(
				screen);
		Assert.assertFalse(screenPlausibilisierung
				.isKoordinateAufScreen(koordinaten2d));
	}
	
	@Test
	public void koordinateXAusserhalbKleinerNullDesScreen() {
		Koordinaten2D koordinaten2d = new Koordinaten2D();
		koordinaten2d.x = -1;
		koordinaten2d.y = 500;
		BufferedImage screen = new BilderLaden(null)
				.ladeScreenShotFoEVerknuepfung();
		ScreenPlausibilisierung screenPlausibilisierung = new ScreenPlausibilisierung(
				screen);
		Assert.assertFalse(screenPlausibilisierung
				.isKoordinateAufScreen(koordinaten2d));
	}
	
	@Test
	public void koordinateInnerhalbDesScreenMax() {
		Koordinaten2D koordinaten2d = new Koordinaten2D();
		koordinaten2d.x = 1920;
		koordinaten2d.y = 1080;
		BufferedImage screen = new BilderLaden(null)
				.ladeScreenShotFoEVerknuepfung();
		ScreenPlausibilisierung screenPlausibilisierung = new ScreenPlausibilisierung(
				screen);
		Assert.assertTrue(screenPlausibilisierung
				.isKoordinateAufScreen(koordinaten2d));
	}
	
	@Test
	public void koordinateInnerhalbDesScreenMin() {
		Koordinaten2D koordinaten2d = new Koordinaten2D();
		koordinaten2d.x = 0;
		koordinaten2d.y = 0;
		BufferedImage screen = new BilderLaden(null)
				.ladeScreenShotFoEVerknuepfung();
		ScreenPlausibilisierung screenPlausibilisierung = new ScreenPlausibilisierung(
				screen);
		Assert.assertTrue(screenPlausibilisierung
				.isKoordinateAufScreen(koordinaten2d));
	}
}
