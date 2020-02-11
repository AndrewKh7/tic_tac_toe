package windows;

import settings.ISettingsService;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class SettingsWindow extends JFrame {
    //TODO: New window must be create at the center of MainWindow. (oprional)

    // Window parameters
    private static final int WIN_HEIGHT = 260;
    private static final int WIN_WIDTH = 350;

    //Game default parameters
    private static final int FIELD_MAX_SIZE = 10;
    private static final int FIELD_MIN_SIZE = 3;
    private static final int WINNING_LENGTH_MAX = FIELD_MAX_SIZE;
    private static final int WINNING_LENGTH_MIN = FIELD_MIN_SIZE;

    private ISettingsService settings;

    //Radio button elements
    private JRadioButton jRadioButtonHumanVsHuman = new JRadioButton("Human vs AI", true); //selected default
    private JRadioButton jRadioButtonHumanVsAI = new JRadioButton("Human vs Human");
    private ButtonGroup buttonGroupGameMode = new ButtonGroup();

    //Slider elements
    private JSlider fieldSizeSlider = new JSlider(FIELD_MIN_SIZE, FIELD_MAX_SIZE, FIELD_MIN_SIZE);
    private JSlider winningLengthSlider = new JSlider(WINNING_LENGTH_MIN, WINNING_LENGTH_MAX, WINNING_LENGTH_MIN);

    //Panels elements
    JPanel settingsPanel = new JPanel(new GridLayout(10,1));
    JPanel okButtonPanel = new JPanel(new FlowLayout());

    public SettingsWindow(ISettingsService settings) {
        this.settings = settings;

        setWindowSettings();
        addSwitchModeRadioButton();
        addFieldAndWinSliders();
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
        String fieldTextLabel = "Field Size: ";
        String winningTextLabel = "Winnings length: ";

        JLabel fieldSizeLabel = new JLabel(fieldTextLabel + 0);
        JLabel winningLengthLabel = new JLabel(winningTextLabel + 0);

        settingsPanel.add(fieldSizeLabel);
        settingsPanel.add(fieldSizeSlider);
        settingsPanel.add(winningLengthLabel);
        settingsPanel.add(winningLengthSlider);

        /*--- CAllBack Function ----*/
        fieldSizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                int currentVal = fieldSizeSlider.getValue();
                fieldSizeLabel.setText(fieldTextLabel + currentVal);
                winningLengthSlider.setMaximum(currentVal);
                winningLengthLabel.setText(winningTextLabel + winningLengthSlider.getValue());
            }
        });

        winningLengthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                winningLengthLabel.setText(winningTextLabel + winningLengthSlider.getValue());
            }
        });
    }

    private void addOKButton() {
        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(100,25));
        okButtonPanel.add(okButton);
    }
}

