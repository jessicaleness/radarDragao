package paineis;

import java.awt.Color;
import static java.awt.Color.blue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static logica.RadarDoDragao.Mapa;
import logica.Esfera;
import logica.RadarDoDragao;
import static logica.RadarDoDragao.SorteiaEsferas;

/**
 *
 * @author Jhonatan
 */
public class PainelMapa extends JPanel {
    Esfera[] esferas = new Esfera[7];
    
    public void paintComponent( Graphics g ){
        super.paintComponent( g );
        int x = 0, y = 0;
        //Rectangle2D.Double r;
        //Graphics2D g2 = (Graphics2D) g.create();
        for(int i = 0; i < 42; i++) {
            for(int j = 0; j < 42; j++) {
                if(Mapa[i][j] == 'V') {
                    g.setColor(Color.RED);
                        g.drawRect(x, y, 13, 13);
                        g.fillRect(x, y, 13, 13);
                        x += 15;
                } else
                    if(Mapa[i][j] == 'G') {
                        g.setColor(Color.GREEN);
                        g.drawRect(x, y, 13, 13);
                        g.fillRect(x, y, 13, 13);
                        /*g2.setPaint(Color.GREEN);
                        r = new Rectangle2D.Double(x,y,13,13);
                        g2.fill(r);
                        g2.dispose(); */
                        x += 15;
                    }
                    else if(Mapa[i][j] == 'M') {
                        g.drawRect(x, y, 13, 13);
                        g.setColor(Color.DARK_GRAY);
                        g.fillRect(x, y, 13, 13);
                        x += 15;
                    } else {
                        g.drawRect(x, y, 13, 13);
                        g.setColor(Color.BLUE);
                        g.fillRect(x, y, 13, 13);
                        x += 15;
                    }
                }
                x = 0; y += 15;
            }  
            y += 15;
        
        // Desenha esferas
        SorteiaEsferas();
        esferas = RadarDoDragao.getEsferas();
        g.setColor(Color.ORANGE);
        g.drawOval(esferas[0].getX()*15, esferas[0].getY()*15, 13, 13);
        g.fillOval(esferas[0].getX()*15, esferas[0].getY()*15, 13, 13);
        g.drawOval(esferas[1].getX()*15, esferas[1].getY()*15, 13, 13);
        g.fillOval(esferas[1].getX()*15, esferas[1].getY()*15, 13, 13);
        g.drawOval(esferas[2].getX()*15, esferas[2].getY()*15, 13, 13);
        g.fillOval(esferas[2].getX()*15, esferas[2].getY()*15, 13, 13);
        g.drawOval(esferas[3].getX()*15, esferas[3].getY()*15, 13, 13);
        g.fillOval(esferas[3].getX()*15, esferas[3].getY()*15, 13, 13);
        g.drawOval(esferas[4].getX()*15, esferas[4].getY()*15, 13, 13);
        g.fillOval(esferas[4].getX()*15, esferas[4].getY()*15, 13, 13);
        g.drawOval(esferas[5].getX()*15, esferas[5].getY()*15, 13, 13);
        g.fillOval(esferas[5].getX()*15, esferas[5].getY()*15, 13, 13);
        g.drawOval(esferas[6].getX()*15, esferas[6].getY()*15, 13, 13);
        g.fillOval(esferas[6].getX()*15, esferas[6].getY()*15, 13, 13);
        // System.out.println("X é " +esferas[0].getX() +" Y é " +esferas[0].getY());
        
        /* Desenha legenda */
        // g.setColor(Color.BLACK);
        g.drawString("Legenda:", x, y);
        g.setColor(Color.GREEN);
        x+=60;
        g.drawRect(x, y, 13, 13);
        g.fillRect(x, y, 13, 13);
        x+=20;
        g.drawString(" = Grama; ", x, y);
    }
}