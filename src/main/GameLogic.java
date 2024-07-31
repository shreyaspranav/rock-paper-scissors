package main;

public class GameLogic {

	// Returns Outcome for 'one'
	public static RoundOutcome getOutcome(PlayerChoice one, PlayerChoice another) {

		if (one == another)
			return RoundOutcome.Draw;

		int indexOne = getIndex(one);
		int indexAnother = getIndex(another);

		if (indexOne - indexAnother == 1 || indexOne - indexAnother == -2)
			return RoundOutcome.Win;
		else
			return RoundOutcome.Lose;
	}

	private static int getIndex(PlayerChoice choice) {
		switch (choice) {
		case Paper:
			return 1;
		case Rock:
			return 2;
		case Scissor:
			return 3;

		default:
			return -1;
		}
	}
}
