package engine;

public class Field implements IField{
    private char[][] field;
    private int windLength;
    private char player1 = 'X';
    private char player2 = 'O';
    private int moveCnt;
    private XYPair[] history;


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
        ActionStatus return_val = ActionStatus.failed;
        if( Character.isLetter(this.field[x][y])) return return_val;
        return_val = ActionStatus.success;
        this.field[x][y] = player;
        this.moveCnt++;

        if( this.checkWin(player) )
            if( player == player1 )
                return_val = ActionStatus.winPlayer1;
            else
                return_val = ActionStatus.winPlayer2;

        if( this.moveCnt >= (field.length * field[0].length) )
            return_val = ActionStatus.draw;

        System.out.println("Plyaer: " + player + " x:" + x + " y:" + y);
        return return_val;
    }

    private boolean checkWin(char player) {
        for (int i = 0; i < field.length; i++) {
            int cnt_hor = 0;
            int cnt_ver = 0;
            for (int j = 0; j < field[i].length; j++) {
                if(field[i][j] == player) ++cnt_hor;
                if(field[j][i] == player) ++cnt_ver;
                if(cnt_hor>= this.windLength || cnt_ver >= this.windLength) return true;
            }
        }
        for (int i = 0; i <= (field.length - this.windLength); i++) {
            if( checkWinDiagоnalLine(0, i, player) || checkWinDiagоnalLine(i, 0, player))
                return true;
        }
        return false;
    }

    private boolean checkWinDiagоnalLine(int startX, int startY, char player){
        int lineLength =field.length -  (startX > startY ? startX : startY);
        int cnt = 0;
        for (int i = 0; i < lineLength ; ++i) {
            if(field[i + startX][i + startY] == player) cnt++;
            if(cnt >= this.windLength) return true;
        }
        cnt = 0;
        for (int i = 0; i < lineLength ; ++i) {
            if(field[i + startX][field.length - 1  - i - startY] == player) cnt++;
            if(cnt >= this.windLength) return true;
        }
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
