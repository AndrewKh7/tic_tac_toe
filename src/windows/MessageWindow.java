package windows;

import javax.swing.*;
import java.awt.*;

public class MessageWindow extends JFrame {
    // Window parameters
    private static final int WIN_HEIGHT = 90;
    private static final int WIN_WIDTH = 165;

    private String text;
    public MessageWindow(String text){
        setWindowSettings();
        addText(text);
        addButton();
        setVisible(true);
    }

    private void addText(String text) {
        JPanel panel = new JPanel(new FlowLayout());
        JLabel label = new JLabel(text);
        panel.add(label);
        add(panel, BorderLayout.CENTER);
    }

    private void addButton() {
        JButton okButton = new JButton("Ok");
        JPanel panel = new JPanel(new FlowLayout());
        okButton.setPreferredSize(new Dimension(100,25));
        panel.add(okButton);
        add(panel, BorderLayout.SOUTH);

        okButton.addActionListener(event -> dispose() );
    }

    private void setWindowSettings(){
        setTitle("");
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();    //find display center

        int x = (int) ((screenSize.getWidth() - WIN_WIDTH)/ 2);
        if (x < 0)  x = 0;

        int y = (int) ((screenSize.getHeight() - WIN_HEIGHT) / 2);
        if (y < 0)  y = 0;

        setBounds(x, y, WIN_WIDTH, WIN_HEIGHT);
        getRootPane().setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        setResizable(false);
    };

}
