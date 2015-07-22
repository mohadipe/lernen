package de.mohadipe.dynastie.karte;

public class ZweiDimensionaleKoordinate implements Koordinate {
	public int x;
	public int y;

	public ZweiDimensionaleKoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("X:").append(x).append("Y:").append(y);
		return stringBuilder.toString();
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		ZweiDimensionaleKoordinate other = (ZweiDimensionaleKoordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
