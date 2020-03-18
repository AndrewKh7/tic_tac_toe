package windows;

import engine.ActionStatus;
import engine.IEngine;
import factory.IRouter;
import settings.IGetSettings;

import javax.swing.*;
import java.awt.*;


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

    private FieldPanel field;

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
        this.field = new FieldPanel(settings);
        add(field);
    }

    private void addButtons(){
        JButton startButton = new JButton("New game");
        JButton exitButton = new JButton("Exit");
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        startButton.setPreferredSize(new Dimension(200,25));
        exitButton.setPreferredSize(new Dimension(200,25));
        buttonsPanel.add(startButton);
        buttonsPanel.add(exitButton);
        add(buttonsPanel, BorderLayout.SOUTH);

        /*--- Handlers ---*/
        startButton.addActionListener( event -> startButtonHandler() );
        exitButton.addActionListener( event -> System.exit(0) );
    }

    private void startButtonHandler(){
        this.setEnabled(false);
        router.createSettingsWindow(() ->{
            //Close SettingsWindow handler
            this.setEnabled(true);
            engine.initGame();
            field.initialize(engine.getField(), (x, y) -> mapHandler(x,y) );
        });
    }

    private void mapHandler(int x, int y){
        //Map field click handler
        ActionStatus move = engine.nextTurn(x,y);
        if(move == ActionStatus.winPlayer1)
            router.echo("Player 1 Win!");
        else if( move == ActionStatus.winPlayer2)
            router.echo("Playe 2 Win!");
        else if(move == ActionStatus.draw)
            router.echo("Draw!");
        
        field.update(engine.getField());
    }

}
