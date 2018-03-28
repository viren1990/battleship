package org.self.battleship.artifacts;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.self.battleship.artifacts.Dashboard;
import org.self.battleship.artifacts.player.Player;
import org.self.battleship.exception.BattleShipException;

public class DashboardTest {

	/**
	 * A success test method for {@link Dashboard#prepare2Players()} and
	 * {@link Dashboard#startMatch(Player, Player)} methods.
	 */
	@Test
	public void test_BattleShip_Game_Success() {

		try (Scanner sc = new Scanner(new File("src/test/resources/input.txt"))) {
			final Dashboard dashboard = new Dashboard(sc);
			List<Player> players = dashboard.prepare2Players();
			assertTrue(CollectionUtils.isNotEmpty(players));
			assertTrue(players.size() == 2);

			final Player playerA = players.iterator().next();
			assertEquals(2, playerA.getWarShips().size());
			assertEquals(4, playerA.getMissileTargets().size());

			final Player playerB = players.get(1);
			assertEquals(2, playerB.getWarShips().size());
			assertEquals(10, playerB.getMissileTargets().size());

			final Player winnerPlayer = dashboard.startMatch(playerA, playerB);
			assertNotNull(winnerPlayer);
			assertEquals(playerB, winnerPlayer);

		} catch (IOException exception) {
			assertTrue(exception instanceof FileNotFoundException);
		}

	}

	/**
	 * A Failure case throwing {@link BattleShipException}.
	 */
	@Test(expected = BattleShipException.class)
	public void test_BattleShip_Game_Failure_LocationOverlapping() {

		try (Scanner sc = new Scanner(new File("src/test/resources/input_overlapping.txt"))) {
			final Dashboard dashboard = new Dashboard(sc);
			List<Player> players = dashboard.prepare2Players();
			assertTrue(CollectionUtils.isNotEmpty(players));
			assertTrue(players.size() == 2);
		} catch (IOException exception) {
			assertTrue(exception instanceof FileNotFoundException);
		}

	}

	/**
	 * A Failure case throwing {@link BattleShipException} on inputting invalid
	 * location.
	 */
	@Test(expected = BattleShipException.class)
	public void test_BattleShip_Game_Failure_InvalidLocation() {

		try (Scanner sc = new Scanner(new File("src/test/resources/input_invalidLocation.txt"))) {
			final Dashboard dashboard = new Dashboard(sc);
			List<Player> players = dashboard.prepare2Players();
			assertTrue(CollectionUtils.isNotEmpty(players));
			assertTrue(players.size() == 2);
		} catch (IOException exception) {
			assertTrue(exception instanceof FileNotFoundException);
		}

	}
	
	/**
	 * A Failure case throwing {@link BattleShipException} on inputting invalid
	 * location.
	 */
	@Test(expected = BattleShipException.class)
	public void test_BattleShip_Game_Failure_InvalidMIssileTarget() {

		try (Scanner sc = new Scanner(new File("src/test/resources/input_invalidMissileTarget.txt"))) {
			final Dashboard dashboard = new Dashboard(sc);
			List<Player> players = dashboard.prepare2Players();
			assertTrue(CollectionUtils.isNotEmpty(players));
			assertTrue(players.size() == 2);
			final Player playerA = players.iterator().next();
			assertEquals(2, playerA.getWarShips().size());
			assertEquals(4, playerA.getMissileTargets().size());

			final Player playerB = players.get(1);
			assertEquals(2, playerB.getWarShips().size());
			assertEquals(10, playerB.getMissileTargets().size());

			final Player winnerPlayer = dashboard.startMatch(playerA, playerB);
			assertNotNull(winnerPlayer);
			assertEquals(playerB, winnerPlayer);

		} catch (IOException exception) {
			assertTrue(exception instanceof FileNotFoundException);
		}

	}

}
