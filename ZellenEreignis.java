

/**
 * Beschreibt ein Ereignis für die Zellen.
 * Klicks auf die Zellen werden hier verarbeitet.
 * 
 * @author (Ihr Name)
 * @version 1.0
 */
/**
 * Beschreibt ein Ereignis für die Karten.
 * Klicks auf die Karten werden hier verarbeitet.
 * 
 * @author (Ihr Name)
 * @version 1.0
 */
public class ZellenEreignis extends Ereignisbehandlung
{
    // Array mit allen Karten
    Zelle[][] karten;

    // Konstruktor
    ZellenEreignis(Zelle[][] kartenArray) {
        this.karten = kartenArray;
        Starten(); // Taktgeber starten
    }

    // Überschreibt die Maus-Klick-Funktion
    @Override
    void MausGeklickt(int x, int y, int anzahl) {
        // Durch alle Karten prüfen
        for (int i = 0; i < karten.length; i++) {
            for (int j = 0; j < karten[i].length; j++) {
                Zelle k = karten[i][j];
                if (x >= k.getX() && x <= k.getX() + k.getBreite()
                    && y >= k.getY() && y <= k.getY() + k.getHöhe())
                {
                    System.out.println("Karte [" + i + "," + j + "] wurde angeklickt!");
                    // Beispiel: Karte aufdecken / Farbe ändern
                    k.FarbeSetzen("rot"); 
                }
            }
        }
    }
}

