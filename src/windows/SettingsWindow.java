package windows;

import settings.ISettingsService;

import javax.swing.*;
import java.awt.*;

public class SettingsWindow extends JFrame {
    //TODO: New window must be create at the center of MainWindow. (oprional)

    // Window parameters
    private static final int WIN_HEIGHT = 260;
    private static final int WIN_WIDTH = 350;

    private ISettingsService settings;

    //Radio button elements
    private JRadioButton jRadioButtonHumanVsHuman = new JRadioButton("Human vs AI", true); //selected default
    private JRadioButton jRadioButtonHumanVsAI = new JRadioButton("Human vs Human");
    private ButtonGroup buttonGroupGameMode = new ButtonGroup();

    //Slider elements
    private JSlider fieldSizeSlider = new JSlider(0,10,0);
    private JSlider winningLengthSlider = new JSlider(0,10,0);

    //Panels elements
    JPanel settingsPanel = new JPanel(new GridLayout(10,1));
    JPanel okButtonPanel = new JPanel(new FlowLayout());

    public SettingsWindow(ISettingsService settings) {
        this.settings = settings;

        setWindowSettings();
        addSwitchModeRadioButton();
//        add(new JPanel());  //just for indentation
        addFieldAndWinSliders();
//        add(new JPanel());  //just for indentation
        addOKButton();
        
        add(okButtonPanel, BorderLayout.SOUTH);
        add(settingsPanel, BorderLayout.NORTH);
        setVisible(true);
    }

    private void setWindowSettings(){
        setTitle("New game settings");
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();    //find display center

        int x = (int) ((screenSize.getWidth() - WIN_WIDTH)/ 2);
        if (x < 0)  x = 0;

        int y = (int) ((screenSize.getHeight() - WIN_HEIGHT) / 2);
        if (y < 0)  y = 0;

        setBounds(x, y, WIN_WIDTH, WIN_HEIGHT);
        getRootPane().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
//        setLayout(new GridLayout(10,1));
        setResizable(false);
    };

    private void addSwitchModeRadioButton() {
        JLabel gameModeLable = new JLabel("Choose game mode:");
        settingsPanel.add(gameModeLable);
        buttonGroupGameMode.add(jRadioButtonHumanVsAI);
        buttonGroupGameMode.add(jRadioButtonHumanVsHuman);
        settingsPanel.add(jRadioButtonHumanVsAI);
        settingsPanel.add(jRadioButtonHumanVsHuman);
    }

    private void addFieldAndWinSliders() {
        JLabel fieldSizeLabel = new JLabel("Field Size: 0");
        JLabel winningLengthLabel = new JLabel("Winning length: 0");

        settingsPanel.add(fieldSizeLabel);
        settingsPanel.add(fieldSizeSlider);
        settingsPanel.add(winningLengthLabel);
        settingsPanel.add(winningLengthSlider);
    }

    private void addOKButton() {
        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(100,25));
        okButtonPanel.add(okButton);
    }
}

