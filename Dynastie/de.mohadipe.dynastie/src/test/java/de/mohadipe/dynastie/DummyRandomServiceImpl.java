package de.mohadipe.dynastie;

public class DummyRandomServiceImpl implements RandomService {

	private Integer randomValue = null;

	@Override
	public int getRandomNummerEinsBis(int bis) {
		if (randomValue != null) {
			return randomValue.intValue();
		}
		return bis;
	}

	public void setRandomValue(Integer value) {
		this.randomValue = value;
	}

}
