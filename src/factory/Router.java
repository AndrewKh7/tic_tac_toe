package factory;

import windows.SettingsWindowCloseHandler;

public class Router implements  IRouter {
    private WindowFactory windowFactory;

    public Router(WindowFactory windowFactory) {
        this.windowFactory = windowFactory;
        windowFactory.setRouter(this);
    }

    @Override
    public void createFirstWindow(){
        windowFactory.createtMainWindow();
    }

    @Override
    public void createSettingsWindow(){
        windowFactory.createSettingsWindow();
    }

    @Override
    public void createSettingsWindow(SettingsWindowCloseHandler handler){
        windowFactory.createSettingsWindow(handler);
    }

    @Override
    public void echo(String text){
        windowFactory.createEchoWindow(text);
    }
}
