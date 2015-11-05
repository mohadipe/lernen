package dynastie;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefDynastie {
	@Given("^Ein Spielfeld mit (\\d+) mal (\\d+) Feldern$")
	public void einSpielfeldMitXmalYFeldern(Integer arg1, Integer arg2) {
	    System.out.println("Given");
	}	

	@Given("^Eine Infanterie Einheit des Computers an x (\\d+) y (\\d+)\\.$")
	public void eineInfanterieEinheitDesComputersAnXundY(Integer arg1, Integer arg2) {
		System.out.println("Given");
	}

	@When("^Der Computer einen Zug macht\\.$") 
	public void derComputerEinenZugMacht() {
		System.out.println("When");
	}

	@Then("^Befindet sich die Einheit an x (\\d+) y (\\d+)\\.$")
	public void befindetSichDieEinheitAnXundY(Integer arg1, Integer arg2) {
		System.out.println("Then");
	}
}
