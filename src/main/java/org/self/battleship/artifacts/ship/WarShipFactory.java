package org.self.battleship.artifacts.ship;

import java.util.Objects;

import org.self.battleship.artifacts.RequireJava8;
import org.self.battleship.artifacts.ShipEndurance;

@RequireJava8
public class WarShipFactory {

	public static WarShip createWarShip(ShipEndurance shipType) {
		Objects.requireNonNull(shipType);
		if (shipType == ShipEndurance.TOUGH) {
			return new CruiserWarShip(shipType);
		} else if (shipType == ShipEndurance.WEAK) {
			return new CorvettesWarShip(shipType);
		}
		throw new IllegalArgumentException(String.format("Ship Type %s supplied is not valid", shipType));
	}

}
