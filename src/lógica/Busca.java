package lógica;

import static lógica.RadarDoDragao.esferas;

public class Busca {
    
    public boolean hasEsfera(int i, int j) {
        for(int a = 0; a < 7; a++) {
            if((esferas[a].getX() == i) && (esferas[a].getY() == j)) {
                return true;
            }
        }
        return false;
    }    
    
}