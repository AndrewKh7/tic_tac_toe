/**
 * @author Andrey Khrapkov
 * */

import engine.*;
import settings.SettingService;

public class MainClass {

    public static void main(String[] args)  {
        IEngine engine = new Engine();
        SettingService settingsService = new SettingService();
        WindowFactory  winFactory = new WindowFactory(engine, settingsService);
        Router router = new Router(winFactory);

        router.createFirstWindow();
    }
}
