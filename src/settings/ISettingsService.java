package settings;

/**
 * This inteface extends IGetSettings with some new "Set" Methods.
 * */
public interface ISettingsService extends IGetSettings {
     void setFieldSize(int size);
     void setWinningLength(int len);
     void setHumanVsAiMode();
     void setHumanVsHumanMode();
}
