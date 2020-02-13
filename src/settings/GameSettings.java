package settings;

enum Mode{
    humanVsHuman,
    humanVsAI
}

public class GameSettings implements Cloneable{
    //min and max value of field size and length size;
    public static final int MIN_SIZE = 3;
    public static final int MAX_SIZE = 10;

    private int fieldSize = MIN_SIZE;
    private int winningLength = MIN_SIZE;
    private Mode mode = Mode.humanVsAI;

    public GameSettings(int fieldSize, int winningLength){
        this.fieldSize = fieldSize;
        this.winningLength = winningLength;
    }

    public  GameSettings(int fieldSize, int winningLength, Mode mode){
        this.fieldSize = fieldSize;
        this.winningLength = winningLength;
        this.mode = mode;
    }

    public boolean initialized(){
        if( fieldSize > MIN_SIZE &&  winningLength < fieldSize && winningLength > MIN_SIZE)
            return true;
        else
            return false;

    }

    public int getFieldSize() {
        return fieldSize;
    }

    public int getWinningLength() {
        return winningLength;
    }

    public Mode isHumanVsHumanMode() {
        return mode;
    }

    public void setFieldSize(int fieldSize) {
        if(fieldSize > MAX_SIZE)
            this.fieldSize = MAX_SIZE;
        else if(fieldSize < MIN_SIZE)
            this.fieldSize = MIN_SIZE;
        else
            this.fieldSize = fieldSize;
    }

    public void setWinningLength(int winningLength) {
        if(winningLength > this.fieldSize)
            this.winningLength = this.fieldSize;
        else if(winningLength < MIN_SIZE)
            this.winningLength = MIN_SIZE;
        else
            this.winningLength = winningLength;
    }

    public void setMode(Mode humanVsHumanMode) {
        mode = humanVsHumanMode;
    }

    @Override
    public String toString() {
        return "GameSettings{" +
                "fieldSize=" + fieldSize +
                ", winningLength=" + winningLength +
                ", mode=" + mode +
                '}';
    }
}
