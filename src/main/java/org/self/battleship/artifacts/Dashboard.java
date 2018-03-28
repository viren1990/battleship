package org.self.battleship.artifacts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.self.battleship.artifacts.player.AIPlayer;
import org.self.battleship.artifacts.player.Player;
import org.self.battleship.artifacts.ship.WarShip;
import org.self.battleship.artifacts.ship.WarShipFactory;

@RequireJava8
public class Dashboard {

	private final Scanner sc;

	public Dashboard(final Scanner scanner) {
		this.sc = scanner;
	}

	/**
	 * 
	 * @return {@code List} of {@code Player}.
	 */
	public List<Player> prepare2Players() {
		final int x = sc.nextInt();
		final char y = Character.toUpperCase(sc.next().charAt(0));

		final BattleGrid grid = BattleGrid.getBuilder().buildBoard(new Coordinate(x, y)).createBattleGrid();

		final int noOfShips = sc.nextInt();
		final List<WarShip> shipListForPlayerA = new ArrayList<>();
		final List<WarShip> shipListForPlayerB = new ArrayList<>();

		for (int i = 0; i < noOfShips; i++) {
			populateShipListForPlayers(grid, shipListForPlayerA, shipListForPlayerB);
		}
		final Player playerA = new AIPlayer("Player-1", shipListForPlayerA,grid);
		final Player playerB = new AIPlayer("Player-2", shipListForPlayerB,grid);
		sc.nextLine();

		setTargetMissileTargets(playerA, playerB);
		return Arrays.asList(playerA, playerB);
	}

	private void populateShipListForPlayers(final BattleGrid grid, final List<WarShip> shipListForPlayerA,
			final List<WarShip> shipListForPlayerB) {
		final String shipType = sc.next().toUpperCase();
		final WarShip shipForPlayerA = WarShipFactory.createWarShip(ShipEndurance.fromString(shipType));
		final WarShip shipForPlayerB = WarShipFactory.createWarShip(ShipEndurance.fromString(shipType));

		int sizeX = sc.nextInt();
		int sizeY = sc.nextInt();

		final String startPosForPlayerA = sc.next();
		final String startPosForPlayerB = sc.next();
		shipForPlayerA.addLocationAndDestroyAttempt(grid,
				new Coordinate(Integer.parseInt(startPosForPlayerA.substring(1)), startPosForPlayerA.charAt(0)), sizeX,
				sizeY);
		shipForPlayerB.addLocationAndDestroyAttempt(grid,
				new Coordinate(Integer.parseInt(startPosForPlayerB.substring(1)), startPosForPlayerB.charAt(0)), sizeX,
				sizeY);
		shipListForPlayerA.add(shipForPlayerA);
		shipListForPlayerB.add(shipForPlayerB);
	}

	private void setTargetMissileTargets(final Player playerA, final Player playerB) {
		// Missile targets for player A
		String[] inputs = sc.nextLine().split("\\s");
		final Stack<Coordinate> targets = new Stack<>();
		final List<Coordinate> listOfCoordinates = createMissileTargets(inputs);
		Collections.reverse(listOfCoordinates);
		targets.addAll(listOfCoordinates);
		playerA.setMissileTargets(targets);
		// Missile targets for player B
		inputs = sc.nextLine().split("\\s");
		final Stack<Coordinate> targetsForPlayerB = new Stack<>();

		final List<Coordinate> listOfCoordinatesForPlayerB = createMissileTargets(inputs);
		Collections.reverse(listOfCoordinatesForPlayerB);

		targetsForPlayerB.addAll(listOfCoordinatesForPlayerB);

		playerB.setMissileTargets(targetsForPlayerB);
	}

	private Coordinate createCoordinate(final String target) {
		return new Coordinate(Integer.parseInt(target.substring(1)), target.charAt(0));
	}

	private List<Coordinate> createMissileTargets(final String[] inputs) {
		return Stream.of(inputs).map(this::createCoordinate).collect(Collectors.toList());
	}

	/**
	 * This is where magic happens.
	 * 
	 * @param playerA
	 * @param playerB
	 */
	public Player startMatch(Player playerA, Player playerB) {
		boolean matchOver = false;
		Player winnerPlayer = null;
		while (!matchOver) {
			String msg = playerA.fireStrikeAt(playerB);
			displayMessage(msg);
			if (!playerB.shipsLeftWithPlayer()) {
				displayMessage(playerA.getName() + " won the battle");
				matchOver = true;
				winnerPlayer = playerA;
				break;
			}
			msg = playerB.fireStrikeAt(playerA);
			displayMessage(msg);

			if (!playerA.shipsLeftWithPlayer()) {
				displayMessage(playerB.getName() + " won the battle");
				winnerPlayer = playerB;
				matchOver = true;
				break;
			}
		}
		return winnerPlayer;
	}

	private void displayMessage(String str) {
		System.out.println(str);
	}
}
