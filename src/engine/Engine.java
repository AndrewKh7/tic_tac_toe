package engine;

public class Engine implements IEngine {
    @Override
    public void update(int x, int y) {
        System.out.println("x: " + x + " y: " + y);
    }

}
