package windows;

import engine.IEngine;
import settings.IGetSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainWindow extends JFrame  implements SettingsWindowCloseHandler{
    // TODO: Create another file or class with setting for all windows.(But is it necessary?)
    // TODO: Create new settings window must block this window (not visible or  not clickable)
    // TODO: Create Ctrl+Q handler. (optional)

    //Window parameters
    private static final int WIN_HEIGHT = 555;
    private static final int WIN_WIDTH = 507;

    private IEngine  engine;
    private IGetSettings settings;

    public MainWindow(IEngine engine, IGetSettings settings) {
        this.engine = engine;
        this.settings = settings;

        setWindowSettings();
        addButtons();

        setVisible(true);
    }

    private void setWindowSettings(){
        setTitle("Tic Tac Toe");
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();    //find display center

        int x = (int) ((screenSize.getWidth() - WIN_WIDTH)/ 2);
        if (x < 0)  x = 0;

        int y = (int) ((screenSize.getHeight() - WIN_HEIGHT) / 2);
        if (y < 0)  y = 0;

        setBounds(x, y, WIN_WIDTH, WIN_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getRootPane().setBorder(BorderFactory.createEmptyBorder(5, 5, 2, 5));
    }

    private void addButtons(){
        JButton startButton = new JButton("Start");
        JButton exitButton = new JButton("Exit");
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        startButton.setPreferredSize(new Dimension(200,25));
        exitButton.setPreferredSize(new Dimension(200,25));
        buttonsPanel.add(startButton);
        buttonsPanel.add(exitButton);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

}
