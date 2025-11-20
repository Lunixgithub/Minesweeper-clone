

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
    // Array mit allen Zellen
    Zelle[][] zellen;

    // Konstruktor
    ZellenEreignis(Zelle[][] zellenArray) {
        this.zellen = zellenArray;
        Starten(); // Taktgeber starten
    }

    // Überschreibt die Maus-Klick-Funktion
    @Override
    void MausGeklickt(int x, int y, int anzahl) {
    for (int i = 0; i < zellen.length; i++) {
        for (int j = 0; j < zellen[i].length; j++) {
            Zelle z = zellen[i][j];

            if (x >= z.getX() && x <= z.getX() + z.getBreite() &&
                y >= z.getY() && y <= z.getY() + z.getHöhe())
            {
                System.out.println("Zelle [" + i + "," + j + "] wurde angeklickt!");
                
                z.Aufdecken();  // ✔ richtig
            }
        }
    }
}
}

