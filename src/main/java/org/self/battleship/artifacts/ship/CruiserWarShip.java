package org.self.battleship.artifacts.ship;

import org.self.battleship.artifacts.ShipEndurance;

public class CruiserWarShip extends WarShip {

	public CruiserWarShip(final ShipEndurance shipType) {
		this.setShipType(shipType);
	}

	@Override
	public String somePeculiarBehavior() {
		return "I am Cruiser type of warship. I demonstrate some peculiar behaviors which others don't";
	}

}
