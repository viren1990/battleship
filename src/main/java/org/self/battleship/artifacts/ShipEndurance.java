package org.self.battleship.artifacts;

import java.util.Objects;

/**
 * 
 * @author Viren
 *
 */
public enum ShipEndurance {

	TOUGH("Q", 2), WEAK("P", 1);

	private final String inputString;

	private final int destroyAttempt;

	private ShipEndurance(final String inputString, final int destroyAttempt) {
		this.inputString = inputString;
		this.destroyAttempt = destroyAttempt;
	}

	public String getInputString() {
		return inputString;
	}

	public int getDestroyAttempt() {
		return destroyAttempt;
	}

	public static ShipEndurance fromString(final String str) {
		if (Objects.isNull(str) || str.equals("")) {
			throw new IllegalArgumentException("Supplied string is empty or null.");
		}
		for (ShipEndurance shipType : values()) {
			if (shipType.getInputString().equalsIgnoreCase(str)) {
				return shipType;
			}
		}
		throw new IllegalArgumentException(String.format("Supplied string %s is not legit ship type value.", str));
	}

}