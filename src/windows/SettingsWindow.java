package windows;

import settings.GameSettings;
import settings.ISettingsService;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SettingsWindow extends JFrame {
    //TODO: New window must be create at the center of MainWindow. (oprional)

    // Window parameters
    private static final int WIN_HEIGHT = 260;
    private static final int WIN_WIDTH = 350;

    private ISettingsService settings;

    //Radio button elements
    private JRadioButton jRadioButtonHumanVsHuman ;
    private JRadioButton jRadioButtonHumanVsAI ;
    private ButtonGroup buttonGroupGameMode ;

    //Slider elements
    private JSlider fieldSizeSlider ;
    private JSlider winningLengthSlider ;

    //Panels elements
    private JPanel settingsPanel ;
    private JPanel okButtonPanel ;

    //Buttons
    private JButton okButton;

    public SettingsWindow(ISettingsService settings) {
        this.settings = settings;

        //Radio button elements
        this.jRadioButtonHumanVsHuman = new JRadioButton("Human vs Human"); //selected default
        this.jRadioButtonHumanVsAI = new JRadioButton("Human vs AI",true );
        this.buttonGroupGameMode = new ButtonGroup();

        //Slider elements
        this.fieldSizeSlider = new JSlider(GameSettings.MIN_SIZE, GameSettings.MAX_SIZE, GameSettings.MIN_SIZE);
        this.winningLengthSlider = new JSlider(GameSettings.MIN_SIZE, GameSettings.MIN_SIZE,GameSettings.MIN_SIZE);

        //Panels elements
        this.settingsPanel = new JPanel(new GridLayout(7,1));
        this.okButtonPanel = new JPanel(new FlowLayout());

        //Buttons
        okButton = new JButton("OK");

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
        String winningTextLabel = "Winning length: ";

        JLabel fieldSizeLabel = new JLabel(fieldTextLabel + GameSettings.MIN_SIZE);
        JLabel winningLengthLabel = new JLabel(winningTextLabel + GameSettings.MIN_SIZE);

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
        okButton.setPreferredSize(new Dimension(100,25));
        okButtonPanel.add(okButton);

        /*--- Callback functions ---*/

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                settings.setFieldSize( fieldSizeSlider.getValue());
                settings.setWinningLength( winningLengthSlider.getValue());
                if ( jRadioButtonHumanVsHuman.isSelected())
                    settings.setHumanVsHumanMode();
                else
                    settings.setHumanVsAiMode();
                System.out.println(settings.toString());
            }
        });
    }
}

