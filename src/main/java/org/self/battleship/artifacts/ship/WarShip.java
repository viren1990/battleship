package org.self.battleship.artifacts.ship;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import org.self.battleship.artifacts.BattleGrid;
import org.self.battleship.artifacts.Coordinate;
import org.self.battleship.artifacts.RequireJava8;
import org.self.battleship.artifacts.ShipEndurance;
import org.self.battleship.util.ValidationUtil;

@RequireJava8
public abstract class WarShip {

	private ShipEndurance shipType;

	private boolean isAlive;

	private final Map<Coordinate, Integer> locationAndDestroyAttempts = new HashMap<>();

	public ShipEndurance getShipType() {
		return shipType;
	}

	public void setShipType(ShipEndurance shipType) {
		this.shipType = shipType;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public void addLocationAndDestroyAttempt(final BattleGrid battleGrid, final Coordinate position, final int width,
			final int height) {
		if (Objects.isNull(getShipType())) {
			throw new UnsupportedOperationException(
					"Location and destroy attempts can not be set unless ship type is set");
		}
		ValidationUtil.validateLocation(battleGrid, position);
		IntStream.range(0, width).forEach(widthPos -> IntStream.range(0, height).forEach(heightPos -> {
			locationAndDestroyAttempts.put(
					new Coordinate(position.getX() + widthPos, (char) (position.getY() + heightPos)),
					getShipType().getDestroyAttempt());
		}));

	}


	public Map<Coordinate, Integer> getLocationAndDestroyAttempts() {
		return locationAndDestroyAttempts;
	}

	/**
	 * 
	 * @return some specific behavior , depicted by its implementations.
	 */
	public abstract String somePeculiarBehavior();

}