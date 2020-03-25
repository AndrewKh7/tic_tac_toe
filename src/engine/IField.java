package engine;

public interface IField {
    ActionStatus tryMove(XYPair xyPair, char player);
    ActionStatus tryMove(int x, int y,char player);
    int getFieldSize();
    char[][] getfield();
    XYPair[] getHistory();
    XYPair getLastInHistory();
    char getPlayer1();
    char getPlayer2();
    char getFieldValue(int x, int y);
    char getFieldValue(XYPair pair);
    int getWinLength();


}
