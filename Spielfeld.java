/**
 * Essentielle Klasse
 * 
 * relevant für die Generierung und Verarbeitung des Feldes
 * 
 * flood-fill: extrem wichtiger Algorythmus ohne den Minesweeper nicht klappt
 */


class Spielfeld
{
    Zelle[][] feld;
    //größe des Feldes
    int reihen = 10; 
    int spalten = 10;
    
    int bomben = 10; // Anzahl Bomben auf dem Feld: mehr Bomben = schwerer, weniger Bomben = leichter
    int gesetzteFlaggen = 0;
    SpielStatus status;
    
    Spielfeld()
    {
        feld = new Zelle[reihen][spalten];
        status = new SpielStatus(feld, bomben);

        for (int r = 0; r < reihen; r++) {
            for (int c = 0; c < spalten; c++) {
                int x = c * 50 + 5;
                int y = r * 50 + 5;

                feld[r][c] = new Zelle(x, y, "grau");
                feld[r][c].setKoordinaten(r, c);
            }
        }

        bombenLegen();
        nachbarnBerechnen();
    }

    void bombenLegen() {
        int gelegt = 0;
        while (gelegt < bomben) {
            int r = (int)(Math.random() * reihen); //zufällige Reihe
            int c = (int)(Math.random() * spalten); //zufällige Spalte

            if (feld[r][c].istBombe!= true) {
                feld[r][c].istBombe = true;
                gelegt++;
            }
        }
    }

    void nachbarnBerechnen() {
        for (int r = 0; r < reihen; r++) {
            for (int c = 0; c < spalten; c++) {

                if (feld[r][c].istBombe) continue; //eine Bombe braucht keine Nachbarberechnung

                int count = 0;
                for (int dr = -1; dr <= 1; dr++) {
                    for (int dc = -1; dc <= 1; dc++) {

                        if (dr == 0 && dc == 0) continue;

                        int nr = r + dr;
                        int nc = c + dc;

                        if (nr >= 0 && nr < reihen && nc >= 0 && nc < spalten) {
                            if (feld[nr][nc].istBombe) count++;
                        }
                    }
                }
                feld[r][c].bombeNachbarn = count;
            }
        }
    }

    // rekursive Flood-Fill Methode
   void floodFill(int r, int c) {

    // Grenzen prüfen
    if (r < 0 || r >= feld.length || c < 0 || c >= feld[0].length) {
        return;
    }
    Zelle z = feld[r][c];

    // Wenn schon offen oder Flagge
    if (z.istAufgedeckt || z.hatFlagge) {
        return;
    }

    // Zelle aufdecken
    z.aufdecken(); 
    status.feldAufgedeckt();

    // Wenn diese Zelle eine Zahl hat
    if (z.bombeNachbarn > 0) {
        return;
    }
    // wenn bombenachbarn Null ist -> weitergeflutet
    floodFill(r - 1, c);
    floodFill(r + 1, c);
    floodFill(r, c - 1);
    floodFill(r, c + 1);
}

}