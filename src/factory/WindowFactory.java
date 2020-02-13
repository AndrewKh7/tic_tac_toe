package factory;

import engine.IEngine;
import settings.SettingService;
import windows.MainWindow;
import windows.SettingsWindow;
import windows.SettingsWindowCloseHandler;

public class WindowFactory {
    private IEngine engine;
    private SettingService settingsService;
    private IRouter router;

    public WindowFactory(IEngine engine, SettingService settingsService) {
        this.engine = engine;
        this.settingsService = settingsService;
    }

    public void setRouter(IRouter router){
       this.router = router;
    }

    public void createtMainWindow() {
        MainWindow mainWin = new MainWindow(engine, settingsService, router);
    }

    public void createSettingsWindow(){
        SettingsWindow settingsWin = new SettingsWindow(settingsService);
    }

    public void  createSettingsWindow(SettingsWindowCloseHandler handler){
        SettingsWindow settingsWin = new SettingsWindow(settingsService);
        settingsWin.setHandler(handler);
    }
}
