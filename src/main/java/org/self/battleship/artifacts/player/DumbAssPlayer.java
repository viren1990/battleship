package org.self.battleship.artifacts.player;

import java.util.List;

import org.self.battleship.artifacts.BattleGrid;
import org.self.battleship.artifacts.ship.WarShip;

public class DumbAssPlayer extends Player {

	public DumbAssPlayer(String name, List<WarShip> warShips, final BattleGrid grid) {
		super(name, warShips, grid);
	}

	@Override
	public String fireStrikeAt(Player otherPlayer) {
		return "Somebody gave me warships , I don't know what to do with them !";
	}

}
