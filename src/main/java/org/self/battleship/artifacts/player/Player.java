package org.self.battleship.artifacts.player;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

import org.apache.commons.collections.CollectionUtils;
import org.self.battleship.artifacts.BattleGrid;
import org.self.battleship.artifacts.Coordinate;
import org.self.battleship.artifacts.RequireJava8;
import org.self.battleship.artifacts.ship.WarShip;
import org.self.battleship.util.ValidationUtil;

/**
 * 
 * @author Viren
 *
 */
@RequireJava8
public abstract class Player {

	private List<WarShip> warShips;

	private String name;

	private Stack<Coordinate> missileTargets;

	private BattleGrid battleGrid;

	public Player(final String name, final List<WarShip> warShips, final BattleGrid grid) {
		this.name = name;
		this.warShips = warShips;
		this.battleGrid = grid;
	}

	public boolean shipsLeftWithPlayer() {
		return CollectionUtils.isNotEmpty(getWarShips()) ? getWarShips().stream().filter(Objects::nonNull)
				.anyMatch(ship -> !ship.getLocationAndDestroyAttempts().isEmpty()) : false;
	}

	/**
	 * Evaluates per missile attack on another player's battle area.
	 * 
	 * @param otherPlayer
	 * @return message
	 */
	public abstract String fireStrikeAt(Player otherPlayer);

	/**
	 * Evaluates if missile was hit to the target. In addition to this , it
	 * makes adjustment to target location and attempts left.
	 * 
	 * @param targetPlayer
	 * @param location
	 * @return if missile was hit to the target.
	 */
	protected boolean hitOrMiss(Player targetPlayer, Coordinate location) {
		ValidationUtil.validateLocation(getBattleGrid(), location);
		List<WarShip> ships = targetPlayer.getWarShips();
		for (WarShip ship : ships) {
			if (ship.getLocationAndDestroyAttempts().containsKey(location)) {
				int value = ship.getLocationAndDestroyAttempts().get(location);
				value--;
				if (value == 0) {
					ship.getLocationAndDestroyAttempts().remove(location);
				} else {
					ship.getLocationAndDestroyAttempts().put(location, value);
				}
				return true;
			}
		}
		return false;
	}


	public List<WarShip> getWarShips() {
		return Objects.isNull(warShips) ? Collections.emptyList() : warShips;
	}

	public void setWarShips(List<WarShip> warShips) {
		this.warShips = warShips;
	}

	public Stack<Coordinate> getMissileTargets() {
		return missileTargets;
	}

	public void setMissileTargets(Stack<Coordinate> missileTargets) {
		this.missileTargets = missileTargets;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BattleGrid getBattleGrid() {
		return battleGrid;
	}

	public void setBattleGrid(BattleGrid battleGrid) {
		this.battleGrid = battleGrid;
	}

}
