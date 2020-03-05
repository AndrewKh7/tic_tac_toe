package engine;

public interface IField {
    ActionStatus tryMove(XYPair xyPair, char player);
    ActionStatus tryMove(int x, int y,char player);
    int getFieldSize();
    char[][] getfield();
}
