import java.io.Serializable;

public class MorraInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    public int playerID;
    public int myMove;
    public int p2Move;
    public int p2Guess;
    public int myGuess;
    public int myPoints;
    public int p2Points;
    public boolean playAgain;
    public boolean isGameOver;
    public MorraInfo() {
    }

    public MorraInfo(int id) {
        playerID = id;
    }



//    public int p1Move;
//    public int p2Move;
//    public int p1Guess;
//    public int p2Guess;
//    public int p1Points;
//    public int p2Points;
//    public boolean p1PlayAgain;
//    public boolean p2PlayAgain;
//    public boolean gameOver;

//    /* Constructor */
//    public MorraInfo(int p1Move, int p2Move, int p1Guess, int p2Guess, int p1Points, int p2Points,
//                     boolean p1PlayAgain, boolean p2PlayAgain, boolean gameOver) {
//
//        this.p1Move = p1Move;
//        this.p2Move = p2Move;
//        this.p1Guess = p1Guess;
//        this.p2Guess = p2Guess;
//        this.p1Points = p1Points;
//        this.p2Points = p2Points;
//        this.p1PlayAgain = p1PlayAgain;
//        this.p2PlayAgain = p2PlayAgain;
//        this.gameOver = gameOver;
//    }



}