package engine;

public class Field implements IField{
    char[][] field;
    int windLength;
    char player1 = 'x';
    char player2 = 'o';
    XYPair[] history;


    public Field(int width, int height, int windLength){
        this.field = new char[width][height];
        this.windLength = windLength;
    }
    public Field(int fieldSize, int windLength, char pl1, char pl2){
        this.field = new char[fieldSize][fieldSize];
        this.windLength = windLength;
        this.player1 = pl1;
        this.player2 = pl2;
    }


    @Override
    public boolean tryMove(XYPair xyPair) {
        return false;
    }

    @Override
    public boolean tryMove(int x, int y) {
        return false;
    }

    @Override
    public boolean checkWin(char player1) {
        return false;
    }
}
