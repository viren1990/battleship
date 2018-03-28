package org.self.battleship.artifacts;

/**
 * 
 * @author Viren
 * 
 *         <p>
 *         A pojo depicting position on battle grid.
 *         </p>
 *
 */
public class Coordinate implements Comparable<Coordinate>{

	private int x;

	private char y;

	public Coordinate(int x, char y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public char getY() {
		return y;
	}

	public void setY(char y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(getY()).append(getX()).toString();
	}

	@Override
	public int compareTo(Coordinate another) {
		return this.toString().compareTo(another.toString());
	}

}
