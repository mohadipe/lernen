package de.mohadipe.dynastie.karte;

import de.mohadipe.dynastie.RandomService;

public class GelaendeAdapter {
	public static GelaendeBeschaffenheit getGelaende(RandomService randomService) {
		return GelaendeBeschaffenheit.getEnum(randomService.getRandomNummerEinsBis(GelaendeBeschaffenheit.values().length));
	}
}
