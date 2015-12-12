package dynastie;

import org.hamcrest.Matchers;
import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.mohadipe.dynastie.einheiten.EinheitImpl;
import de.mohadipe.dynastie.karte.Karte;
import de.mohadipe.dynastie.karte.KarteImpl;
import de.mohadipe.dynastie.karte.ZweiDimensionaleKoordinate;
import de.mohadipe.dynastie.logik.model.Einheit;
import de.mohadipe.dynastie.logik.model.Koordinate;

public class StepDefDynastie {
	Einheit einheit;
	Karte karte;
	Koordinate standort;
	
	@Given("^Eine Karte mit \"([^\"]*)\" mal \"([^\"]*)\" Feldern\\.$")
	public void eineKarteMitMengeAnFeldern(Integer x, Integer y) {
		karte = new KarteImpl(x, y);
	}
	
	@Given("^Eine Einheit mit Reichweite \"([^\"]*)\"\\.$")
	public void eineEinheitMitReichweite(Integer reichweite) {
		einheit = new EinheitImpl(reichweite);
	}
	
	@Given("^Die Einheit befindet sich auf Feld \"([^\"]*)\" \"([^\"]*)\"\\.$")
	public void dieEinheitBefindetSichAufFeld(Integer feldX, Integer feldY) {
		standort = new ZweiDimensionaleKoordinate(feldX, feldY);
		karte.fuegeEinheitVonSpielerHinzu(standort, einheit);
	}
	
	@When("^Die Einheit wird \"([^\"]*)\" Felder vertikal nach Norden bewegt\\.$") 
	public void dieEinheitWirdVertikalNachNodrdenBewegt(Integer anzahlFelder) {
		ZweiDimensionaleKoordinate ziel = new ZweiDimensionaleKoordinate(standort.getX(), standort.getY() + anzahlFelder);
		karte.bewegeEinheitVonNach(einheit, standort, ziel);
	}
	
	@Then("^Befindet sich die Einheit auf Feld \"([^\"]*)\" \"([^\"]*)\"\\.$")
	public void befindetSichDieEinheitAufFeld(Integer feldX, Integer feldY) {
		Koordinate actualStandort = karte.getStandortVonEinheit(einheit);
		ZweiDimensionaleKoordinate expectedStandort = new ZweiDimensionaleKoordinate(feldX, feldY);
		Assert.assertThat(actualStandort, Matchers.equalTo(expectedStandort));
	}
}
