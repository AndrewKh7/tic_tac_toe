package factory;

import windows.SettingsWindowCloseHandler;

public interface IRouter {
    void createFirstWindow();
    void createSettingsWindow();
    void createSettingsWindow(SettingsWindowCloseHandler handler);
    void echo(String text);
}
