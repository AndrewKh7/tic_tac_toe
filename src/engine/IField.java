package engine;

public interface IField {
    boolean tryMove(XYPair xyPair);
    boolean tryMove(int x, int y);

    boolean checkWin(char player1);
}
