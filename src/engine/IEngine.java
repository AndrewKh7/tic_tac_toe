package engine;

public interface IEngine {
    void initGame();
    ActionStatus playerMove(int x, int y);
    ActionStatus playerMove(XYPair xyPair);
    char[][] getField();
    XYPair[] lastMoves();
    ActionStatus nextTurn(int x, int y);

    }
