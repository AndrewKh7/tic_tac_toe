package engine;

import settings.IGetSettings;

public class Engine implements IEngine {
    IGetSettings settings;
    IField field;
    char player1;
    char player2;
    boolean initialized = false;
    char whoseMove;

    public Engine(IGetSettings settings){
       this.settings = settings;
       this.player1 = 'x';
       this.player2 = 'y';
    }

    @Override
    public void initGame() {
        this.field = new Field(settings.getFieldSize(), settings.getWinningLength(),player1,player2);

        this.initialized = true;
    }

    @Override
    public status userMove(int x, int y) {
        status ret = status.failed;
        if(!this.initialized)
            return status.notInitialized;

        if( !this.field.tryMove(x,y) ) return status.failed;
        if( this.field.checkWin(player1) ) return status.winPlayer1;

        return null;
    }

    @Override
    public status userMove(XYPair xyPair) {
        return null;
    }

    @Override
    public char[][] getField() {
        return new char[0][];
    }

    @Override
    public XYPair[] lastMoves() {
        return new XYPair[0];
    }

    @Override
    public void update(int x, int y) {
        System.out.println("x: " + x + " y: " + y);
    }

}
