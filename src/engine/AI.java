package engine;

import java.util.Random;

public class AI implements AIInterface{
    Random rand;
    IField field;
    public AI(IField field){
        this.rand = new Random();
        this.field = field;
    }
    @Override
    public XYPair nextTurn() {
        int x = rand.nextInt(this.field.getFieldSize());
        int y = rand.nextInt(this.field.getFieldSize());
        return new XYPair(x,y);
    }
}
