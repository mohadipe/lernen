package de.mohadipe.ui.test.robot.aufgabe;

public class Warten extends AbstractAufgabe {
	private final long dauer;
	private boolean isErfolgreich;
	public Warten(final long dauer) {
		this.dauer = dauer;
	}
	
	private boolean doAufgabe() {
		try {
			System.out.println("Sleep: " + dauer);
			Thread.sleep(dauer);
			System.out.println("Wake Up");
			return true;
		} catch (InterruptedException e) {
			System.out.println("Interrupt " + e);
			return false;
		}
	}
	
	public Ergebnis getErgebnis() {
		return null;
	}

	@Override
	public void ausfuehren() {
		isErfolgreich = doAufgabe();
	}

	@Override
	public boolean isErfolgreich() {
		return isErfolgreich;
	}

	@Override
	public AufgabenArten getArt() {
		return AufgabenArten.WARTEN;
	}
}
