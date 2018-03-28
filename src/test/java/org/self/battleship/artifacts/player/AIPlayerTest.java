package org.self.battleship.artifacts.player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.self.battleship.artifacts.BattleGrid;
import org.self.battleship.artifacts.Coordinate;
import org.self.battleship.artifacts.ship.WarShip;
import org.self.battleship.exception.BattleShipException;

/**
 * 
 * Test suite for {@link AIPlayer} class.
 *
 */
public class AIPlayerTest {

	@Mock
	private WarShip warShip;

	@Mock
	private BattleGrid battleGrid;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void test_shipsLeftWithPlayer() {
		Map<Coordinate, Integer> locAndAttemptAssoc = new HashMap<>();
		locAndAttemptAssoc.put(new Coordinate(1, 'A'), 2);

		Mockito.when(warShip.getLocationAndDestroyAttempts()).thenReturn(locAndAttemptAssoc);
		AIPlayer player = new AIPlayer("test", Arrays.asList(warShip), battleGrid);
		Assert.assertTrue(player.shipsLeftWithPlayer());
	}

	@Test(expected = BattleShipException.class)
	public void test_validateShipsForPositionOverlapping() {

		final WarShip warShip1 = Mockito.mock(WarShip.class);
		final WarShip warShip2 = Mockito.mock(WarShip.class);

		Map<Coordinate, Integer> locAndAttemptAssoc = new HashMap<>();
		locAndAttemptAssoc.put(new Coordinate(1, 'A'), 2);
		Map<Coordinate, Integer> locAndAttemptAssoc2 = new HashMap<>();
		locAndAttemptAssoc2.put(new Coordinate(1, 'A'), 2);

		Mockito.when(warShip1.getLocationAndDestroyAttempts()).thenReturn(locAndAttemptAssoc);
		Mockito.when(warShip2.getLocationAndDestroyAttempts()).thenReturn(locAndAttemptAssoc2);
		AIPlayer player = new AIPlayer("test", Arrays.asList(warShip1, warShip2), battleGrid);
		Assert.assertNotNull(player);
	}

}
