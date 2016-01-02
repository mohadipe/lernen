package de.mohadipe.ui.test.robot;

import java.awt.Color;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.google.gson.Gson;

public class FarbBlock {

	private SortedMap<Koordinaten2D, Color> farbPixel = new TreeMap<Koordinaten2D, Color>(
			new KoordinatenSortierer());
	private Koordinaten2D maxXKoordinate = null;
	private Koordinaten2D maxYKoordinate = null;

	public void addFarbe(Koordinaten2D koordinaten2d, Color farbe) {
		farbPixel.put(koordinaten2d, farbe);
		if (maxXKoordinate == null) {
			maxXKoordinate = koordinaten2d;
		} else {
			if (maxXKoordinate.x < koordinaten2d.x) {
				maxXKoordinate = koordinaten2d;
			}
		}
		if (maxYKoordinate == null) {
			maxYKoordinate = koordinaten2d;
		} else {
			if (maxYKoordinate.y < koordinaten2d.y) {
				maxYKoordinate = koordinaten2d;
			}
		}
	}

	public int getMaxX() {
		return maxXKoordinate.x;
	}

	public int getMaxY() {
		return maxYKoordinate.y;
	}

	public Color getFarbe(int x, int y) {
		return farbPixel.get(new Koordinaten2D(x, y));
	}

	public int getAnzahlFarbPixel() {
		return farbPixel.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((farbPixel == null) ? 0 : farbPixel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FarbBlock other = (FarbBlock) obj;
		if (farbPixel == null) {
			if (other.farbPixel != null)
				return false;
		} else if (!farbPixel.equals(other.farbPixel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	private class KoordinatenSortierer implements Comparator<Koordinaten2D> {
		@Override
		public int compare(Koordinaten2D o1, Koordinaten2D o2) {
			if (o1.x < o2.x) {
				return 1;
			}
			if (o1.x > o2.x) {
				return -1;
			}
			if (o1.y < o2.y) {
				return 1;
			}
			if (o1.y > o2.y) {
				return -1;
			}
			return 0;
		}
	}

	public boolean isAehnlich(FarbBlock other) {
		if (this.equals(other)) {
			return true;
		}
		Set<Entry<Koordinaten2D, Color>> entrySet = this.farbPixel.entrySet();
		for (Entry<Koordinaten2D, Color> entry : entrySet) {
			Color otherColor = other.getFarbe(entry.getKey().x,
					entry.getKey().y);
			Color thisColor = entry.getValue();
			// muenzen
			if (thisColor.getRed() == 255) {
				if (thisColor.getRed() != otherColor.getRed()) {
					return false;
				}
				if (200 > thisColor.getGreen() || 200 > otherColor.getGreen()) {
					return false;
				}
				if (100 > thisColor.getBlue() || 100 > otherColor.getBlue()) {
					return false;
				}
				return true;
			}
			// werkzeug
			if (thisColor.getBlue() < 5) {
				if (100 > thisColor.getRed() || 100 > otherColor.getRed()) {
					return false;
				}
				if (120 < thisColor.getGreen() || 120 < otherColor.getGreen()) {
					return false;
				}
				return true;
			}
			// schlafen
			if (thisColor.getGreen() < 180 && 150 < thisColor.getGreen()) {
				if ((170 > thisColor.getRed() && thisColor.getRed() < 210)
						|| (170 > otherColor.getRed() && otherColor.getRed() < 210)) {
					return false;
				}
				if ((80 > thisColor.getBlue() && thisColor.getBlue() < 120)
						|| (80 > otherColor.getBlue() && otherColor.getBlue() < 120)) {
					return false;
				}
				return true;
			}
			// popup
			if (thisColor.getRed() < 100) {
				if ((90 > thisColor.getRed() && thisColor.getRed() < 50)
						|| (90 > otherColor.getRed() && otherColor.getRed() < 50)) {
					return false;
				}
				if ((60 > thisColor.getGreen() && thisColor.getGreen() < 100)
						|| (60 > otherColor.getGreen() && otherColor.getGreen() < 100)) {
					return false;
				}
				if ((80 > thisColor.getBlue() && thisColor.getBlue() < 120)
						|| (80 > otherColor.getBlue() && otherColor.getBlue() < 120)) {
					return false;
				}
				return true;
			}
		}
		return false;
	}
}
