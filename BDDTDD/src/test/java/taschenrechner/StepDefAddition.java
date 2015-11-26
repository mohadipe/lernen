package taschenrechner;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;

import com.google.inject.Guice;
import com.google.inject.Injector;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefAddition {

	private List<BigInteger> summanden;
	private ITaschenrechner taschenrechner;
	private BigInteger summe;
	private List<Bruch> brueche;
	private Bruch summenBruch;

	public StepDefAddition() {
		Injector injector = Guice
				.createInjector(new TaschenrechnerGuiceModul());
		taschenrechner = injector.getInstance(ITaschenrechner.class);
	}

	@Given("^Der Summand \"([^\"]*)\"\\.$")
	public void derSummand(String summand) throws Throwable {
		if (summanden == null) {
			summanden = new ArrayList<BigInteger>();
		}
		summanden.add(new BigInteger(summand));
	}

	@Given("^Der Bruch \"([^\"]*)\"\\.$")
	public void derBruch(String stringBruch) {
		Bruch bruch = new HelperStepDef().wandleInBruch(stringBruch);
		if (brueche == null) {
			brueche = new ArrayList<Bruch>();
		}
		brueche.add(bruch);
	}

	@When("^Die Brüche addiert werden\\.$")
	public void dieBruecheAddiertWerden() {
		summenBruch = taschenrechner.addiereBrueche(brueche);
	}

	@When("^Die Summanden addiert werden\\.$")
	public void dieSummandenAddiertWerden() throws Throwable {
		summe = taschenrechner.addiere(summanden);
	}

	@Then("^Ist die Summe der Brüche \"([^\"]*)\"\\.$")
	public void isDieSummeDerBrueche(String erwarteterBruch) {
		Bruch bruch = new HelperStepDef().wandleInBruch(erwarteterBruch);
		Assert.assertThat(summenBruch, Matchers.is(bruch));
	}
	
	@Then("^Die Summe beträgt \"([^\"]*)\"\\.$")
	public void dieSummeBeträgt(BigInteger erwarteteSumme) throws Throwable {
		Assert.assertThat(summe, Matchers.is(erwarteteSumme));
	}
}
