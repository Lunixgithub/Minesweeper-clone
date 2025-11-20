/**
 * Legt die Blumenwelt an und die nicht beweglichen Objekte. 
 * Die Welt besteht aus 10x10 Zellen. Damit die Objekte der Klasse Zelle gut sichtbar sind, wird 
 * zwischen den Zellen ein Abstand von 4 Pixeln gelassen. Mit diesem Rand hat die Welt Zellen der 
 * Größe 50x 50 Pixel.
 * In der Blumenwelt sind auch mehrere Blumen.
 * 
 * @author Peter Brichzin
 * @version version 1.0
 */
import java.util.Random;

public class Spielfeld
{
    Zelle[][] feld; // Array für alle Zellen

    Spielfeld() 
    {
        
        int reihen = 12;
        int spalten = 10;
         int bombenAnzahl = 10;
        feld = new Zelle[reihen][spalten];

        // Zellen erzeugen und positionieren
        for (int i = 0; i < reihen; i++) {
            for (int j = 0; j < spalten; j++) {
                int x = 50 * i + 2;
                int y = 50 * j + 2;
                feld[i][j] = new Zelle(x, y, "grün"); // am Anfang Grün
            }
        }
        
        Random rand = new Random();
        int bomben = bombenAnzahl;

        while (bomben > 0) {
            int i = rand.nextInt(reihen);
            int j = rand.nextInt(spalten);

            if (!feld[i][j].istBombe) {
                feld[i][j].BombeSetzen();
                bomben--;
            }
        }

        ZellenEreignis ereignis = new ZellenEreignis(feld);
    }
}