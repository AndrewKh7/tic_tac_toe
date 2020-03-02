package engine;

public interface IEngine {
    enum status{
        success,
        failed,
        winPlayer1,
        winPlayer2,
        draw,
        notInitialized
    }

    void initGame();
    status userMove(int x, int y);
    status userMove(XYPair xyPair);
    char[][] getField();
    XYPair[] lastMoves();
    void update(int x, int y);
}
