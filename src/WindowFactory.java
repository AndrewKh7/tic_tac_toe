import engine.IEngine;
import settings.SettingService;
import windows.MainWindow;
import windows.SettingsWindow;

public class WindowFactory {
    private IEngine engine;
    private SettingService settingsService;

    public WindowFactory(IEngine engine, SettingService settingsService) {
        this.engine = engine;
        this.settingsService = settingsService;
    }

    public void createtMainWindow() {
        MainWindow mainWin = new MainWindow(engine,settingsService);
    }

    public void createSettingsWindow(){
        SettingsWindow settingsWin = new SettingsWindow(settingsService);
    }
}
