package taschenrechner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefAddition {

	@Given("^Der Summand \"([^\"]*)\"\\.$")
	public void derSummand(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("Given");
	}

	@When("^Die Summanden addiert werden\\.$")
	public void dieSummandenAddiertWerden() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("When");
	}

	@Then("^Die Summe beträgt \"([^\"]*)\"\\.$")
	public void dieSummeBeträgt(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("Then");
	}
}
