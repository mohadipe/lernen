package de.mohadipe.dynastie.karte;

public enum GelaendeBeschaffenheit {
	EBENE,WALD,HUEGEL;
	
	public static GelaendeBeschaffenheit getEnum(int i) {
		switch (i) {
		case 1:
			return EBENE;
		case 2:
			return WALD;
		case 3:
			return HUEGEL;
		default:
			throw new RuntimeException("Für: " + i + " ist keine GelaendeBeschaffenheit definiert.");
		}
	}
	
	@Override
	public String toString() {
		switch (this) {
		case EBENE:
			return "E";
		case WALD:
			return "W";
		case HUEGEL:
			return "H";
		default:
			throw new RuntimeException("Für: " + this + " ist kein SysO-Kuerzel definiert.");
		}
	}
}
