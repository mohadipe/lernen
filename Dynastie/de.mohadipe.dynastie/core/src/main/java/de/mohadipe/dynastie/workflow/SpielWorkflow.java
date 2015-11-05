package de.mohadipe.dynastie.workflow;

import javax.inject.Inject;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.RandomService;

public class SpielWorkflow implements Workflow {
	private int aktuellerSchritt = 1;
	private WorkflowKonfiguration workflowSchritteKonfiguration;
	private Konfiguration konfiguration;
	private RandomService randomService;

	@Inject
	public SpielWorkflow(Konfiguration konfiguration, RandomService randomService, WorkflowKonfiguration workflowSchritteKonfiguration) {
		this.konfiguration = konfiguration;
		this.randomService = randomService;
		this.workflowSchritteKonfiguration = workflowSchritteKonfiguration;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.mohadipe.dynastie.workflow.Workflow#anzeigen()
	 */
	@Override
	public int anzeigen() {
		Schritt schritt = workflowSchritteKonfiguration.getSchritt();
		if (schritt == null) {
			schritt = workflowSchritteKonfiguration.getEnde();
		}
		schritt.anzeigen();
		aktuellerSchritt = schritt.naechsterSchritt();
		workflowSchritteKonfiguration.setAktuellerSchritt(aktuellerSchritt);
		return aktuellerSchritt;
	}

	@Override
	public void addSchritt(int reihenfolge, Schritt schritt) {
		workflowSchritteKonfiguration.addSchritt(reihenfolge, schritt);
	}

	@Override
	public Konfiguration getKonfiguration() {
		return this.konfiguration;
	}

	@Override
	public RandomService getRandomService() {
		return this.randomService;
	}
}
