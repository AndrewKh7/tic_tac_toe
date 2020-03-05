package windows;

import settings.IGetSettings;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FieldPanel extends JPanel {
    private MapClickhandler clickHandler;
    private IGetSettings settings;
    private boolean initialized = false;

    private int cellWidth;
    private int cellHeight;

    char[][] field;

    public FieldPanel(IGetSettings settings){
        this.settings = settings;
        this.clickHandler = (x,y) -> {};
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.black));
        addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                if( initialized ){
                    int x = (int)(mouseEvent.getX()/cellWidth);
                    int y = (int)(mouseEvent.getY()/cellHeight);

                    //Compensation from inaccurate size calculation cellHeight and cellWidth(casting to int)
                    x = x >= settings.getFieldSize()? x-1:x;
                    y = y >= settings.getFieldSize()? y-1:y;

                    clickHandler.handler(x,y);
                }
            }

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {}

            @Override
            public void mousePressed(MouseEvent mouseEvent) {}

            @Override
            public void mouseEntered(MouseEvent mouseEvent){}

            @Override
            public void mouseExited(MouseEvent mouseEvent) {}
        });
    }

    public void initialize( MapClickhandler handler){
        this.clickHandler = handler;

        this.cellHeight = (int)(getHeight() / settings.getFieldSize());
        this.cellWidth = (int) (getWidth() / settings.getFieldSize());

        this.initialized = true;

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g){
        for(int i = 1; i < settings.getFieldSize(); i++){
            g.drawLine(0, i*this.cellHeight, getWidth(), i*this.cellHeight);
            g.drawLine(i*this.cellWidth,0, i*this.cellWidth,getHeight());
        }
        if(this.field != null)
            for (int i = 0; i < this.field.length; i++){
                for (int j = 0; j < this.field[i].length; j++){
                    drawinto(i, j, this.field[i][j], g);
                }
            }

    }

        private void drawinto(int x, int y, char sym, Graphics g){
            if(sym == 'X'){
                g.drawLine( x*this.cellWidth + 10, y*this.cellHeight + 10,
                        (x + 1)*this.cellWidth - 10 , (y + 1)*this.cellHeight - 10);
                g.drawLine( (x + 1)*this.cellWidth - 10, y*this.cellHeight + 10,
                        x*this.cellWidth + 10 , (y + 1)*this.cellHeight - 10);
            }else if ( sym == 'O'){
                g.drawOval(x*this.cellWidth + 5, y*this.cellHeight + 5,
                        this.cellWidth - 10 , this.cellHeight - 10);
            }

        }

        public void update(char[][] field){
            this.field = field;
            repaint();
        }


    }
