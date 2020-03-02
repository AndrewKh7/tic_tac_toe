/**
 * @author Andrey Khrapkov
 * */

import engine.IEngine;
import engine.Engine;
import factory.*;
import settings.SettingService;

public class MainClass {

    // TODO: Maybe make different window for main menus and game? (optional)
    //  TODO: Create a "Continue" button. This will load the last game.
    //      You can also release save and load from the file during the game.  (optional)
    // TODO: Create victory count. (oprional)
    // TODO: Maybe create all the windows together and set visible = false? Factory just will make them visible.

    public static void main(String[] args)  {
        SettingService settingsService = new SettingService();
        IEngine engine = new Engine(settingsService);
        WindowFactory winFactory = new WindowFactory(engine, settingsService);
        Router router = new Router(winFactory);

        router.createFirstWindow();
//        router.createSettingsWindow();
    }
}
