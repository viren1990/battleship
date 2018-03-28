package org.self.battleship.artifacts;

/**
 * @author Viren
 * A holder class for Battle Grid attributes
 *
 */
public class BattleGrid {

	private final Coordinate board;

	private BattleGrid(final BattleGridBuilder builder) {
		this.board = builder.board;
	}
	
	public Coordinate getBoard() {
		return board;
	}

	/**
	 * 
	 * A builder class for {@linkplain BattleGrid}
	 *
	 */
	public static class BattleGridBuilder {

		private Coordinate board;

		public BattleGridBuilder buildBoard(final Coordinate board) {
			this.board = board;
			return this;
		}

		public BattleGrid createBattleGrid() {
			return new BattleGrid(this);
		}

	}

	public static BattleGridBuilder getBuilder() {
		return new BattleGridBuilder();
	}

}
