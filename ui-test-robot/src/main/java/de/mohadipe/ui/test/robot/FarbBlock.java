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

	public boolean isAehnlich(FarbBlock other, int tolleranz) {
		if (this.equals(other)) {
			return true;
		}
		Set<Entry<Koordinaten2D, Color>> entrySet = this.farbPixel.entrySet();
		for (Entry<Koordinaten2D, Color> entry : entrySet) {
			Color otherColor = other.getFarbe(entry.getKey().x,
					entry.getKey().y);
			Color thisColor = entry.getValue();
			if (!isAehnlich(thisColor, otherColor, tolleranz)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isAehnlich(Color referenz, Color other, int tolleranz) {
		if (isNotIn(other.getRed(), referenz.getRed(), tolleranz)) {
			return false;
		}
		if (isNotIn(other.getGreen(), referenz.getGreen(), tolleranz)) {
			return false;
		}
		if (isNotIn(other.getBlue(), referenz.getBlue(), tolleranz)) {
			return false;
		}
		return true;
	}

	private boolean isNotIn(int other, int referenz, int tolleranz) {
		int min = referenz - tolleranz;
		int max = referenz + tolleranz;
		if (other < min) {
			return true;
		}
		if (other > max) {
			return true;
		}
		return false;
	}
}
