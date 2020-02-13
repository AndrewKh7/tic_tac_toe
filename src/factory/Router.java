package factory;

import windows.SettingsWindowCloseHandler;

public class Router implements  IRouter {
    private WindowFactory windowFactory;

    public Router(WindowFactory windowFactory) {
        this.windowFactory = windowFactory;
        windowFactory.setRouter(this);
    }

    public void createFirstWindow(){
        windowFactory.createtMainWindow();
    }

    public void createSettingsWindow(){
        windowFactory.createSettingsWindow();
    }

    public void createSettingsWindow(SettingsWindowCloseHandler handler){
        windowFactory.createSettingsWindow(handler);
    }
}
