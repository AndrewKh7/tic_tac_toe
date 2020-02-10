public class Router {
    private WindowFactory windowFactory;

    public Router(WindowFactory windowFactory) {
        this.windowFactory = windowFactory;
    }

    public void createFirstWindow(){
        windowFactory.MainWindow();
    }
}