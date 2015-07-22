package de.mohadipe.dynastie.workflow;

public interface WorkflowKonfiguration {
	public void addSchritt(Integer reihenfolge, Schritt schritt);

	public Schritt getSchritt();

	public Schritt getEnde();

	public void setAktuellerSchritt(int aktuellerSchritt);
}
