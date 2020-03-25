package engine;

import java.util.Random;

public class AI implements AIInterface{
    Random rand;
    IField field;

    XYPair aiWinPoint;
    XYPair aiBlockPoint;

    public AI(IField field){
        this.rand = new Random();
        this.field = field;
        this.aiBlockPoint = new XYPair(0,0);
        this.aiWinPoint = new XYPair(0,0);
    }
    @Override
    public XYPair nextTurn() {
        int x = rand.nextInt(this.field.getFieldSize());;
        int y = rand.nextInt(this.field.getFieldSize());;
        XYPair[] lastMove = field.getHistory();

        XYPair lastUserPoint = lastMove[lastMove.length - 1];
        XYPair lastAIPont;

        if(lastMove.length < 2) {
             lastAIPont = lastMove[lastMove.length - 1];
        }
        else {
             lastAIPont = lastMove[lastMove.length - 2];
        }

        int checkWin = prewinDot(lastUserPoint.x, lastUserPoint.y, this.field.getPlayer1());
        int aiCheckWin = prewinDot(lastAIPont.x, lastAIPont.y,this.field.getPlayer2());
        if (aiCheckWin != 0){
            if( aiCheckWin == 1 && checkWin == 1 ) {
                return new XYPair(x, y);
            }
            else{
                if( checkWin == 1)
                    return new XYPair(aiWinPoint.x, aiWinPoint.y );
                else if ( aiCheckWin == 1)
                    return new XYPair(aiBlockPoint.x, aiBlockPoint.y );
                else if ( aiCheckWin > checkWin )
                    return new XYPair(aiBlockPoint.x, aiBlockPoint.y );
                else
                    return new XYPair(aiWinPoint.x, aiWinPoint.y);
            }
        }
        return new XYPair(x, y);
    }

    private  int countingDot(int x, int y, int dir, char dot){
        int cnt = 0;
        while ( x >= 0 && y >= 0 && x < this.field.getFieldSize() && y <  this.field.getFieldSize()
                && (this.field.getFieldValue(x,y) == dot) ) {
            cnt += 1;
            switch (dir) {
                case 0:
                    --x;
                    break;
                case 1:
                    --x;
                    --y;
                    break;
                case 2:
                    --y;
                    break;
                case 3:
                    --y;
                    ++x;
                    break;
                case 4:
                    ++x;
                    break;
                case 5:
                    ++x;
                    ++y;
                    break;
                case 6:
                    ++y;
                    break;
                case 7:
                    ++y;
                    --x;
                    break;
                default:
                    return -1;
            }
        }
        return cnt;
    }

    private  int countingDotAndEpmty(int x, int y, int dir, char dot){
        int cnt = 0;
        while ( x >= 0 && y >= 0 && x < this.field.getFieldSize() && y <  this.field.getFieldSize() && (this.field.getFieldValue(x,y) == dot || this.field.getFieldValue(x,y) == '.') ) {
            cnt += 1;
            switch (dir) {
                case 0:
                    --x;
                    break;
                case 1:
                    --x;
                    --y;
                    break;
                case 2:
                    --y;
                    break;
                case 3:
                    --y;
                    ++x;
                    break;
                case 4:
                    ++x;
                    break;
                case 5:
                    ++x;
                    ++y;
                    break;
                case 6:
                    ++y;
                    break;
                case 7:
                    ++y;
                    --x;
                    break;
                default:
                    return -1;
            }
        }
        return cnt;
    }

    private  int[] shortViewOfDotArray(int x, int y, int dir, int num, char dot){
        char prevDot = this.field.getFieldValue(x,y);
        int cnt = 0;
        int index = 0;
        int[] shortArr = new int[num];
        for (int i = 0; i < num; i++) {
            if ( this.field.getFieldValue(x,y) == prevDot )
                ++cnt;
            else{
                if (prevDot == '.')
                    shortArr[index] = -cnt;
                else
                    shortArr[index] = cnt;
                cnt = 1;
                ++index;
                prevDot = this.field.getFieldValue(x,y);
            }
            switch (dir) {
                case 0:
                    ++x;
                    break;
                case 1:
                    ++x;
                    ++y;
                    break;
                case 2:
                    ++y;
                    break;
                case 3:
                    ++y;
                    --x;
                    break;
                default:
                    break;
            }
        }
        if (prevDot == '.')
            shortArr[index] = -cnt;
        else
            shortArr[index] = cnt;


        return shortArr;
    }

    private  int prewinDot(int x, int y, char dot) {
        if(this.field.getFieldValue(x,y) != dot) return 1;
        int retVal = 1;
        int up = 0;
        int down = 0;
        int dir;
        int[] arr = null;
        int[] point = new int[2];
        for ( dir = 0 ; dir < 4; dir++) {
            up = countingDotAndEpmty(x, y, dir, dot);
            down = countingDotAndEpmty(x, y, dir + 4 , dot);

            switch (dir) {
                case 0:
                    arr = shortViewOfDotArray(x - up + 1, y, dir, up + down - 1, dot);
                    break;
                case 1:
                    arr = shortViewOfDotArray(x - up + 1, y - up + 1, dir, up + down - 1, dot);
                    break;
                case 2:
                    arr = shortViewOfDotArray(x, y - up + 1, dir, up + down - 1, dot);
                    break;
                case 3:
                    arr = shortViewOfDotArray(x + up - 1, y - up + 1, dir, up + down - 1, dot);
                    break;
                default:
                    arr = new int[1];
                    break;
            }
            point = checkWinLine(arr);
            if (point[1] == -2){
                retVal = 0; // user or ai win
            }else if (point[1] >= 0){
                retVal = point[0] + 1;
                break;
            }

        }
        int addVal = 0;
        if (arr != null && point[1] >= 0) {
            if (dot == 'X')
                switch (dir) {
                    case 0:
                        aiBlockPoint.x = x - up + 1 + point[1];
                        aiBlockPoint.y = y;
                        break;
                    case 1:
                        aiBlockPoint.y = y - up + 1 + point[1];
                        aiBlockPoint.x = x - up + 1 + point[1];
                        break;
                    case 2:
                        aiBlockPoint.x = x;
                        aiBlockPoint.y = y - up + 1 + point[1];
                        break;
                    case 3:
                        aiBlockPoint.x = x + up - 1 - point[1];
                        aiBlockPoint.y = y - up + 1 + point[1];
                        break;
                    default:
                        break;
                }
            else
                switch (dir) {
                    case 0:
                        aiWinPoint.x = x - up + 1 + point[1];
                        aiWinPoint.y = y;
                        break;
                    case 1:
                        aiWinPoint.y = y - up + 1 + point[1];
                        aiWinPoint.x = x - up + 1 + point[1];
                        break;
                    case 2:
                        aiWinPoint.x = x;
                        aiWinPoint.y = y - up + 1 + point[1];
                        break;
                    case 3:
                        aiWinPoint.x = x + up - 1 - point[1];
                        aiWinPoint.y = y - up + 1 + point[1];
                        break;
                    default:
                        break;
                }
        }
        return retVal;
    }


    /*
    res[0] - how many step to win
    res[1] - point to block (or win)
     */
    private  int[] checkWinLine(int[] line) {
        int[] res = {0, -1};

        if(line.length == 1 || line[1] == 0) {
            if (line[0] >= this.field.getWinLength()) {
                res[0] = 0;
                res[1] = -2;
            }
        } else {
            for (int i = 0; i < line.length && line[i] != 0; ++i) {
                if (line[i] >= this.field.getWinLength()) {
                    res[0] = 0;
                    res[1] = -2;
                    break;
                }
                if (line[i] == this.field.getWinLength() - 1) {
                    if (i == line.length - 1 || line[i + 1] == 0) {
                        res[0] = 1;
                        res[1] = absSumArr(i - 1, line) - 1;
                    }else{
                        res[0] = 1;
                        res[1] = absSumArr(i, line);
                    }
                    break;
                }
                if (line[i] == -1) {
                    if (i == 0) {
                        if (line[i + 1] == this.field.getWinLength() - 1) {
                            res[0] = 1;
                            res[1] = 0;
                            break;
                        }
                    } else if (i == line.length - 1 || line[i + 1] == 0) {
                        if (line[i - 1] >= this.field.getWinLength() - 1) {
                            res[0] = 1;
                            res[1] = absSumArr(i-1, line);
                            break;
                        }
                    } else if (line[i - 1] + line[i + 1] >= this.field.getWinLength() - 1) {
                        res[0] = 1;
                        res[1] = absSumArr(i-1, line);
                        break;
                    }else if ( i > 1 && (i+2< line.length && line[i+2] != 0) && line[i-1] + line[i+1] == this.field.getWinLength() -2){
                        res[0] = 2;
                        res[1] = absSumArr(i-1,line);
                        break;
                    }
                }
                if (line[i] == this.field.getWinLength() - 2 && i != 0 && i != line.length - 1 && line[i+1] != 0) {
                    if (line[i - 1] + line[i + 1] <= -3) {
                        if ( line[i - 1] < line[ i + 1]){
                            res[0] = 2;
                            res[1] = absSumArr(i-1, line) - 1;
                        }else{
                            res[0] = 2;
                            res[1] = absSumArr(i, line);
                        }
                        break;
                    }
                }

            }
        }

        return res;
    }

    private  int absSumArr(int i, int[] line){
        int res = 0 ;
        for (int j = 0; j <= i; j++) {
            res += line[j] > 0 ? line[j] : -line[j];
        }
        return res;
    }



}
