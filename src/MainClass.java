/**
 * @author Andrey Khrapkov
 * */

import engine.*;
import settings.SettingService;

public class MainClass {

    // TODO: Maybe make different window for main menus and game? (optional)
    // TODO: Create a "Continue" button. This will load the last game.
    //      You can also release save and load from the file during the game.  (optional)
    // TODO: Create victory count. (oprional)

    public static void main(String[] args)  {
        IEngine engine = new Engine();
        SettingService settingsService = new SettingService();
        WindowFactory  winFactory = new WindowFactory(engine, settingsService);
        Router router = new Router(winFactory);

        router.createFirstWindow();
        router.createSettingsWindow();
    }
}
