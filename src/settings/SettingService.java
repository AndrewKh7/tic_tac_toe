package settings;

import java.awt.*;

public class SettingService implements ISettingsService, IGetSettings {

    private GameSettings gameSettings;
    public SettingService() {
        this.gameSettings = new GameSettings(0,0);
    }

    @Override
    public void setFieldSize(int size) {
        gameSettings.setFieldSize(size);
    }

    @Override
    public void setWinningLength(int len) {
        gameSettings.setWinningLength(len);
    }

    @Override
    public void setHumanVsAiMode() {
        gameSettings.setMode( Mode.humanVsAI);
    }

    @Override
    public void setHumanVsHumanMode() {
        gameSettings.setMode(Mode.humanVsHuman);
    }

    @Override
    public void setPlayer1Sym(char sym) {
        gameSettings.setPlayer1(sym);
    }

    @Override
    public void setPlayer2Sym(char sym) {
        gameSettings.setPlayer2(sym);
    }

    @Override
    public char getPlayer1Sym() {
        return gameSettings.getPlayer1();
    }

    @Override
    public char getPlayer2Sym() {
        return gameSettings.getPlayer2();
    }

    @Override
    public int getFieldSize() {
        return gameSettings.getFieldSize();
    }

    @Override
    public int getWinningLength() {
        return gameSettings.getWinningLength();
    }

    @Override
    public boolean isHumanVsHumanMode() {
        if (gameSettings.isHumanVsHumanMode() == Mode.humanVsHuman)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "SettingService{" +
                "gameSettings=" + gameSettings +
                '}';
    }
}
