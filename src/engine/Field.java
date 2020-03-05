package engine;

public class Field implements IField{
    char[][] field;
    int windLength;
    char player1 = 'X';
    char player2 = 'O';
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
    public ActionStatus tryMove(XYPair xyPair, char player) {
        return tryMove(xyPair.x,xyPair.y, player);
    }

    @Override
    public ActionStatus tryMove(int x, int y, char player) {
        this.checkWin(player);
        this.field[x][y] = player;
        System.out.println("Plyaer: " + player + " x:" + x + " y:" + y);
        return ActionStatus.success;
    }

    private boolean checkWin(char player) {
        return false;
    }

    @Override
    public int getFieldSize() {
        return field.length;
    }

    @Override
    public char[][] getfield() {
        char[][] cloneField = new char[getFieldSize()][getFieldSize()];
        for(int i = 0; i < getFieldSize(); i++ ) cloneField[i] = this.field[i].clone();
        return cloneField;
    }

}
