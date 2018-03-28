package org.self.battleship.artifacts.player;

import java.util.List;
import java.util.Objects;

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
 *         <p>
 *         An intelligent extension of {@link Player}.
 *         </p>
 *
 */
@RequireJava8
public class AIPlayer extends Player {

	public AIPlayer(String name, List<WarShip> warShips , final BattleGrid battleGrid) {
		super(name, warShips,battleGrid);
		ValidationUtil.validateShipsForPositionOverlapping(warShips);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String fireStrikeAt(Player otherPlayer) {
		Objects.requireNonNull(otherPlayer, "Argument player can not be null");

		if (otherPlayer.shipsLeftWithPlayer()) {
			final StringBuilder message = new StringBuilder();
			message.append(getName());

			if (CollectionUtils.isNotEmpty(getMissileTargets())) {
				final Coordinate position = getMissileTargets().pop();
				message.append(" fires a missile with target ").append(position.toString()).append(" which got ");
				if (!hitOrMiss(otherPlayer, position)) {
					message.append("miss");
				} else {
					message.append("hit");
					message.append(System.lineSeparator()).append(fireStrikeAt(otherPlayer));
				}
			} else {
				message.append(" has no more missiles to launch");
			}
			return message.toString();
		}
		return "";
	}

}
