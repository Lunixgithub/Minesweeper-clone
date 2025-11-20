

/**
 * Zellen als Bestandteile der Welt, in der sich die Figuren bewegen können. 
 * Jede Zelle hat die Größe 46x46 Pixel. Beachte Ränder um die Zellen, bei der Anordnung in der Welt!
 * 
 * @author Peter Brichzin
 * @version 1.0
 */


class Zelle extends Rechteck
{
    // --- MINESWEEPER-FUNKTIONEN ---
    boolean istBombe = false;
    boolean istAufgedeckt = false;
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
    void setPositionImRaster(int zeile, int spalte) {
        this.zeile = zeile;
        this.spalte = spalte;
    }

    void BombeSetzen() {
        istBombe = true;
    }

    void NachbarBombenSetzen(int n) {
        bombeNachbarn = n;
    }

    void Aufdecken() {
        if (istAufgedeckt) return;

        istAufgedeckt = true;

        if (istBombe) {
            FarbeSetzen("rot"); // Bombe getroffen
        }
        else if (bombeNachbarn > 0) {
            FarbeSetzen("weiß");
        }
        else {
            FarbeSetzen("weiß"); // leeres Feld
        }
    }
}