package org.self.battleship.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.self.battleship.artifacts.BattleGrid;
import org.self.battleship.artifacts.Coordinate;
import org.self.battleship.artifacts.ship.WarShip;
import org.self.battleship.exception.BattleShipException;

public class ValidationUtil {

	public static void validateLocation(BattleGrid battleArea, Coordinate position)
			throws org.self.battleship.exception.BattleShipException {
		Coordinate area = battleArea.getBoard();
		if (area.getX() < position.getX() || area.getY() < position.getY())
			throw new BattleShipException("Invalid Location Found");
	}

	/**
	 * 
	 * @return if ship coordinates don't overlap.
	 */
	public static void validateShipsForPositionOverlapping(final List<WarShip> warships) {
		if (CollectionUtils.isEmpty(warships)) {
			return;
		}
		final List<Coordinate> shipCoordinates = warships.stream()
				.filter(ship -> MapUtils.isNotEmpty(ship.getLocationAndDestroyAttempts()))
				.flatMap(ship -> ship.getLocationAndDestroyAttempts().keySet().stream()).collect(Collectors.toList());

		final Set<Coordinate> distinctCoordinates = new HashSet<>(shipCoordinates);
		if (Integer.compare(shipCoordinates.size(), distinctCoordinates.size()) != 0) {
			throw new BattleShipException("Input coordinates are messed up , correct them !");
		}
	}

}
