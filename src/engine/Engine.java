package engine;

import settings.IGetSettings;

public class Engine implements IEngine {
    IGetSettings settings;
    IField field;
    char player1;
    char player2;
    boolean initialized = false;
    char whoseMove;
    AIInterface aiPlayer;

    public Engine(IGetSettings settings){
       this.settings = settings;
       this.player1 = this.settings.getPlayer1Sym();
       this.player2 = this.settings.getPlayer2Sym();
       this.whoseMove = this.player1;
    }

    @Override
    public void initGame() {
        this.field = new Field(settings.getFieldSize(), settings.getWinningLength(),player1,player2);
       if( !settings.isHumanVsHumanMode() )
           this.aiPlayer = new AI(this.field);
       else
           this .aiPlayer = null;

        this.initialized = true;
    }

    @Override
    public ActionStatus playerMove(int x, int y) {
        if(!this.initialized) return ActionStatus.notInitialized;

        ActionStatus ret = this.field.tryMove(x,y, this.whoseMove);

        if(ret == ActionStatus.failed) return ret;

        if(this.whoseMove == player1 )
            this.whoseMove = player2;
        else
            this.whoseMove = player1;

        // ret is no ActionStatus.field and ActionStatus.notInitialized
        if(ret != ActionStatus.success) return ret;

        if( this.aiPlayer != null && this.whoseMove == player2)
           do{
               ret = playerMove( aiPlayer.nextTurn() );
           }while(ret == ActionStatus.failed);

   return ret;
    }

    @Override
    public ActionStatus playerMove(XYPair xyPair) {
        return playerMove(xyPair.x,xyPair.y);
    }

    @Override
    public char[][] getField() {
        return field.getfield();
    }


    @Override
    public XYPair[] lastMoves() {
        return new XYPair[0];
    }

    @Override
    public void update(int x, int y) {
//        System.out.println("x: " + x + " y: " + y);
        System.out.println(playerMove(x,y));
    }

}
