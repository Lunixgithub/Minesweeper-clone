class MinesweeperEreignis extends Ereignisbehandlung {
    
    Zelle[][] zellen;
    Spielfeld spielfeldRef; // Referenz auf das Spielfeld

    MinesweeperEreignis(Zelle[][] zellenArray, Spielfeld sf) {
        this.zellen = zellenArray;
        this.spielfeldRef = sf;
        Starten(); // Taktgeber starten
    }

    @Override
    void MausGeklickt(int x, int y, int anzahl) {

        for (int r = 0; r < zellen.length; r++) {
            for (int c = 0; c < zellen[r].length; c++) {

                Zelle z = zellen[r][c];

                if (x >= z.getX() && x <= z.getX() + z.getBreite() &&
                    y >= z.getY() && y <= z.getY() + z.getHöhe())
                {
                    if (z.istBombe) {
                        z.Aufdecken();
                        System.out.println("BOOM!");
                        return;
                    }

                    if (!z.istAufgedeckt) {
                        if (z.bombeNachbarn == 0)
                            spielfeldRef.floodFill(r, c);
                        else
                            z.Aufdecken();
                    }

                    return;
                }
            }
        }
    }
}
