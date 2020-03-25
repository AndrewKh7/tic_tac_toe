package engine;

public class XYPair {
    public int x = 0;
    public int y = 0;

    public XYPair(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public XYPair(XYPair p){
        this.x = p.x;
        this.y = p.y;
    }

    @Override
    public String toString() {
        return "XYPair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
