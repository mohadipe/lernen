package ext.besipiel;

import java.awt.Graphics2D;

public interface Entity {

	public abstract void draw(Graphics2D g);

	public abstract void move(long delta);

	public abstract boolean collidesWith(Entity him);

	public abstract void doLogic();

	public abstract void setHorizontalMovement(double moveSpeed);

	public abstract double getHorizontalMovement();

	public abstract int getX();

	public abstract int getY();

}