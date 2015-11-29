package de.mohadipe.dynastie.ui.map;

import com.google.gson.Gson;

import java.math.BigDecimal;
import java.math.MathContext;

public class FeldImpl implements Feld {
    private final int x;
    private final int y;

    public FeldImpl(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public FeldImpl(float x, float y) {
        this.x = new BigDecimal(x).setScale(0, BigDecimal.ROUND_UP).intValue();
        this.y = new BigDecimal(y).setScale(0, BigDecimal.ROUND_UP).intValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeldImpl feld = (FeldImpl) o;

        if (x != feld.x) return false;
        return y == feld.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
