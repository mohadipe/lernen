package de.mohadipe.dynastie.angreifen;


public abstract class GegnerAngreifen {

	public static Angreifen createInstance() {
		return new DefaultAngriff();
	}

}
