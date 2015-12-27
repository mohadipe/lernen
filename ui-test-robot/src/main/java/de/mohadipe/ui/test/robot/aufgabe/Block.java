package de.mohadipe.ui.test.robot.aufgabe;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import de.mohadipe.ui.test.robot.GehoertNichtZuBlockException;
import de.mohadipe.ui.test.robot.Koordinaten2D;

public class Block {

	private int anzahlFelder;
	private List<Koordinaten2D> koordinaten = new ArrayList<Koordinaten2D>();
	
	
	public Block(int quadrat) {
		this.anzahlFelder = quadrat * quadrat;
	}

	public Block(int quadrat, int x, int y) {
		this(quadrat);
		koordinaten.add(new Koordinaten2D(x, y));
	}

	public void addKoordinate(int x, int y) throws GehoertNichtZuBlockException {
		Koordinaten2D koordinaten2d = new Koordinaten2D();
		koordinaten2d.x = x;
		koordinaten2d.y = y;
		if ((isNachbar(koordinaten2d) || koordinaten.isEmpty())
				&& !koordinaten.contains(koordinaten2d)) {
			koordinaten.add(koordinaten2d);
		} else {
			throw new GehoertNichtZuBlockException();
		}
	}
	
	public boolean isBlockVoll() {
		return koordinaten.size() == anzahlFelder;
	}

	private boolean isNachbar(Koordinaten2D kandidat) {
		for (Koordinaten2D koordinaten2d : koordinaten) {
			if (koordinaten2d.isNachbarVon(kandidat)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anzahlFelder;
		result = prime * result
				+ ((koordinaten == null) ? 0 : koordinaten.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Block other = (Block) obj;
		if (anzahlFelder != other.anzahlFelder)
			return false;
		if (koordinaten == null) {
			if (other.koordinaten != null)
				return false;
		} else if (!koordinaten.equals(other.koordinaten))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	public int getKantenLaenge() {
		return (int) Math.sqrt(anzahlFelder);
	}

	public void addBlock(Block block) throws GehoertNichtZuBlockException {
		for (Koordinaten2D koordinaten2d : block.koordinaten) {
			this.addKoordinate(koordinaten2d.x, koordinaten2d.y);
		}
		if (anzahlFelder < this.koordinaten.size()) {
			anzahlFelder = this.koordinaten.size();
		}
	}

	public int getAnzahlKoordinaten() {
		return this.koordinaten.size();
	}

	public Koordinaten2D getKoordinaten() {
		return this.koordinaten.get(0);
	}

}
