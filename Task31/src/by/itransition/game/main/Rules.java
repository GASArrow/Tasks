package by.itransition.game.main;

public class Rules {

    static String gettingWinner(int numberOfMoves, int playersMove, int computersMove) {
        if (playersMove == computersMove) {
            return "Draw";
        } else {
            return (playersMove - computersMove) > 0
                    ? (Math.abs(playersMove - computersMove) < Math
                            .abs(Math.abs(playersMove - computersMove) - numberOfMoves) ? "Win" : "Lose")
                    : (Math.abs(playersMove - computersMove) < Math
                            .abs(Math.abs(playersMove - computersMove) - numberOfMoves) ? "Lose" : "Win");
        }
    }
}
