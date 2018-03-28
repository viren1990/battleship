package org.self.battleship.exception;

/**
 * 
 * @author Viren
 *
 */
public class BattleShipException extends RuntimeException {

	private static final long serialVersionUID = 8269941078960660283L;

	public BattleShipException(final String message) {
		super(message);
	}

	public BattleShipException(final Throwable exception) {
		super(exception);
	}

	public BattleShipException(final String message, final Throwable exception) {
		super(message, exception);
	}

}
