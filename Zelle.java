

/**
 * Zellen als Bestandteile der Welt, in der sich die Figuren bewegen können. 
 * Jede Zelle hat die Größe 46x46 Pixel. Beachte Ränder um die Zellen, bei der Anordnung in der Welt!
 * 
 * @author Peter Brichzin
 * @version 1.0
 */


class Zelle extends Rechteck
{
    boolean istBombe = false;
    boolean istAufgedeckt = false;
    boolean hatFlagge = false;
    int bombeNachbarn = 0;

    int zeile;
    int spalte;

    /**
     * Erzeugt und platziert eine Zelle in der gewünschten Farbe.
     * 
     * @param xLinksOben    x-Wert der Ecke links oben der Zelle
     * @param yLinksOben    y-Wert der Ecke links oben der Zelle
     * @param farbeNeu      Farbe der Zelle
     */
    Zelle (int xLinksOben, int yLinksOben, String farbeNeu)
    {
        super();
        PositionSetzen(xLinksOben, yLinksOben);
        GrößeSetzen(46, 46);
        FarbeSetzen(farbeNeu);
    }

    // Getter
    int getX() { 
        return x; 
    }
    int getY() { 
        return y; 
    }
    int getBreite() { 
        return breite; 
    }
    int getHöhe() { 
        return höhe; 
    }

   
    //setter
    void setKoordinaten(int zeile, int spalte) {
        this.zeile = zeile;
        this.spalte = spalte;
    }

    void BombeSetzen() {
        istBombe = true;
    }

    void nachbarBombenSetzen(int n) {
        bombeNachbarn = n;
    }


public void toggleFlagge() {
    // Flaggen dürfen nur gesetzt werden, wenn das Feld NICHT aufgedeckt ist
    if (istAufgedeckt) return;

    hatFlagge = !hatFlagge;

    if (hatFlagge) {
        this.FarbeSetzen("gelb"); 
    } else {
        this.FarbeSetzen("grau");   // ursprüngliche Farbe
    }
}

    void aufdecken() {
    if (istAufgedeckt) return;
    istAufgedeckt = true;

    if (istBombe) {
        FarbeSetzen("rot");
        Text t = new Text();
        t.PositionSetzen(x + breite/2, y + höhe/2); // mittig
        t.TextSetzen("B");
        t.FarbeSetzen("schwarz");
    } else {
        FarbeSetzen("grün");

        if (bombeNachbarn > 0) {
            Text t = new Text();
            t.PositionSetzen(x + breite/2, y + höhe/2); // mittig
            t.TextSetzen("" + bombeNachbarn);
            
            if (bombeNachbarn ==1) {
                t.FarbeSetzen("blau");
            }
            else if (bombeNachbarn ==2) {
                t.FarbeSetzen("schwarz");
                
            }
             else if (bombeNachbarn >2) {
                t.FarbeSetzen("rot");
             }
        }
    }
}

}