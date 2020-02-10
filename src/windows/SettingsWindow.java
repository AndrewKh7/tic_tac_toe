package windows;

import settings.ISettingsService;

import javax.swing.*;
import java.awt.*;

public class SettingsWindow extends JFrame {
    // Window parameters
    private static final int WIN_HEIGHT = 230;
    private static final int WIN_WIDTH = 350;

    private ISettingsService settings;

    public SettingsWindow(ISettingsService settings) {
        this.settings = settings;

        setWindowSettings();

        setVisible(true);
    }


    private void setWindowSettings(){
        setTitle("Seettings");
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();    //find display center

        int x = (int) ((screenSize.getWidth() - WIN_WIDTH)/ 2);
        if (x < 0)  x = 0;

        int y = (int) ((screenSize.getHeight() - WIN_HEIGHT) / 2);
        if (y < 0)  y = 0;

        setBounds(x, y, WIN_WIDTH, WIN_HEIGHT);
        getRootPane().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setResizable(false);
    };


}

