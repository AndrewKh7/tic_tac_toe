import engine.IEngine;
import settings.SettingService;

public class WindowFactory {
    private IEngine engine;
    private SettingService settingsService;

    public WindowFactory(IEngine engine, SettingService settingsService) {
        this.engine = engine;
        this.settingsService = settingsService;
    }

}
