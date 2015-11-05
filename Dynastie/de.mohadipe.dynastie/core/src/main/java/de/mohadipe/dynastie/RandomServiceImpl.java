package de.mohadipe.dynastie;

import java.util.Random;

public class RandomServiceImpl implements RandomService {
	private Random rn = new Random();
	
	@Override
	public int getRandomNummerEinsBis(int bis) {
		return rn.nextInt(bis) + 1;
	}
}
