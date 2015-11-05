package de.mohadipe.dynastie.workflow;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.RandomService;

public interface Workflow {

	public abstract int anzeigen();

	public abstract void addSchritt(int reihenfolge, Schritt schritt);

	public abstract Konfiguration getKonfiguration();

	public abstract RandomService getRandomService();
}