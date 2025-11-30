class Spielfeld
{
    Zelle[][] feld;
    int reihen = 10;
    int spalten = 10;
    int bomben = 10;

    Spielfeld()
    {
        feld = new Zelle[reihen][spalten];

        for (int r = 0; r < reihen; r++) {
            for (int c = 0; c < spalten; c++) {
                int x = c * 50 + 5;
                int y = r * 50 + 5;

                feld[r][c] = new Zelle(x, y, "grün");
                feld[r][c].setKoordinaten(r, c);
            }
        }

        bombenLegen();
        nachbarnBerechnen();

        new MinesweeperEreignis(feld, this);
    }

    void bombenLegen() {
        int gelegt = 0;
        while (gelegt < bomben) {
            int r = (int)(Math.random() * reihen);
            int c = (int)(Math.random() * spalten);

            if (feld[r][c].istBombe!= true) {
                feld[r][c].istBombe = true;
                gelegt++;
            }
        }
    }

    void nachbarnBerechnen() {
        for (int r = 0; r < reihen; r++) {
            for (int c = 0; c < spalten; c++) {

                if (feld[r][c].istBombe) continue;

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

    
    void floodFill(int r, int c) {
        if (r < 0 || r >= reihen || c < 0 || c >= spalten){
         return;   
        }

        Zelle z = feld[r][c];
        if (z.istAufgedeckt || z.istBombe){
            return;
        }

        z.Aufdecken();

        if (z.bombeNachbarn > 0) return;

        floodFill(r-1, c);
        floodFill(r+1, c);
        floodFill(r, c-1);
        floodFill(r, c+1);
    }
}