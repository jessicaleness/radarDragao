/*
 * Esta classe desencadeia os eventos que seguem o clique no botão.
 */
package logica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jhonatan Nabhan
 */
public class EventoBotao implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        RadarDoDragao radar = new RadarDoDragao();
        radar.DesenhaMap();
    }
    
}
