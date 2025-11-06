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

public class Blumenwelt
{
    Zelle[][] feld; // Array für alle Karten

    Blumenwelt() 
    {
        // 1. Karten-Array anlegen (z.B. 4x4)
        int reihen = 10;
        int spalten = 10;
        feld = new Zelle[reihen][spalten];

        // 2. Karten erzeugen und positionieren
        for (int i = 0; i < reihen; i++) {
            for (int j = 0; j < spalten; j++) {
                int x = 50 * i + 2;
                int y = 50 * j + 2;
                feld[i][j] = new Zelle(x, y, "grün"); // Anfangsfarbe grün
            }
        }

        // 3. Ereignisbehandlung starten
        ZellenEreignis ereignis = new ZellenEreignis(feld);
    }
}