import engine.*;
import settings.SettingService;

public class MainClass {
        private IEngine engine = new Engine();
        private SettingService settingsService = new SettingService();
        private WindowFactory  winFactory = new WindowFactory(engine, settingsService);
        private Router router = new Router(winFactory);

    public static void main(String[] args)  {

    }
}
