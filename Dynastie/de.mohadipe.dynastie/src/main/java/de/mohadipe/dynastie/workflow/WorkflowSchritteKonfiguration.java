package de.mohadipe.dynastie.workflow;

import java.util.SortedMap;
import java.util.TreeMap;

public class WorkflowSchritteKonfiguration implements WorkflowKonfiguration {
	private SortedMap<Integer, Schritt> konfiguration = new TreeMap<Integer, Schritt>();
	private int aktuellerSchritt = 1;

	public void addSchritt(Integer reihenfolge, Schritt schritt) {
		konfiguration.put(reihenfolge, schritt);
	}

	@Override
	public Schritt getSchritt() {
		return konfiguration.get(aktuellerSchritt);
	}

	@Override
	public Schritt getEnde() {
		return konfiguration.get(999);
	}

	@Override
	public void setAktuellerSchritt(int aktuellerSchritt) {
		this.aktuellerSchritt = aktuellerSchritt;
	}
}
