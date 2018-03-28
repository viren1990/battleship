package org.self.battleship.artifacts.ship;

import org.self.battleship.artifacts.ShipEndurance;

public class CorvettesWarShip extends WarShip {

	public  CorvettesWarShip(final ShipEndurance shipType) {
		this.setShipType(shipType);
	}
		
	@Override
	public String somePeculiarBehavior() {
		return "I am Corvettes type of warship. I demonstrate some peculiar behaviors which others don't";
	}

}
