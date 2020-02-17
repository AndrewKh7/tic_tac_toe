package windows;

import engine.IEngine;
import factory.IRouter;
import factory.Router;
import settings.IGetSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MainWindow extends JFrame {
    // TODO: Create another file or class with setting for all windows.(But is it necessary?)
    // TODO: Create new settings window must block this window (not visible or  not clickable)
    // TODO: Create Ctrl+Q handler. (optional)

    //Window parameters
    private static final int WIN_HEIGHT = 555;
    private static final int WIN_WIDTH = 507;

    private IEngine  engine;
    private IGetSettings settings;
    private IRouter router;

    private MapPanel map;

    public MainWindow(IEngine engine, IGetSettings settings, IRouter router) {
        this.engine = engine;
        this.settings = settings;
        this.router = router;

        setWindowSettings();
        addMap();
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

    private void addMap(){
        this.map = new MapPanel(settings);
        add(map);
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

        /*--- Handlers ---*/
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                router.createSettingsWindow(() ->{
                    System.out.println("Main Window got: " + settings.toString());
                    map.initialize((x,y)->{
                        System.out.println("x: " + x + " y: " + y);
                    });
                });

            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }

}
