class MinesweeperEreignis extends Ereignisbehandlung {

    boolean flagMode = false;
    boolean spielVorbei = false;

    Zelle[][] zellen;
    Spielfeld spielfeldRef;
    SpielStatus status;   

    MinesweeperEreignis(Zelle[][] zellenArray, Spielfeld sf, SpielStatus st) {
        this.zellen = zellenArray;
        this.spielfeldRef = sf;
        this.status = st;
        Starten();
    }

    @Override
    void TasteGedrückt(char taste) {
        if (taste == 'f' || taste == 'F') {
            flagMode = !flagMode;
            System.out.println("Flag-Modus: " + (flagMode ? "AN" : "AUS"));
        }
    }

    @Override
    void MausGeklickt(int x, int y, int anzahl) {

        if (spielVorbei) return;

        for (int r = 0; r < zellen.length; r++) {
            for (int c = 0; c < zellen[r].length; c++) {

                Zelle z = zellen[r][c];

                if (x >= z.getX() && x <= z.getX() + z.getBreite() &&
                    y >= z.getY() && y <= z.getY() + z.getHöhe())
                {
                    //flagmode
                    if (flagMode) {
                        z.toggleFlagge();
                        status.flaggeGeändert(z);   // status informieren
                        return;
                    }

                    
                    if (z.istBombe) {
                        z.Aufdecken();
                        status.bombeGetroffen();

                        // ALLE Bomben sichtbar machen
                        for (int i = 0; i < zellen.length; i++) {
                            for (int j = 0; j < zellen[i].length; j++) {
                                if (zellen[i][j].istBombe)
                                    zellen[i][j].Aufdecken();
                            }
                        }

                        spielVorbei = true;
                        System.out.println("BOOM! Game Over!");
                        return;
                    }

                    //aufdecken normal
                    if (!z.istAufgedeckt) {
                        if (z.bombeNachbarn == 0)
                            spielfeldRef.floodFill(r, c);
                        else
                            z.Aufdecken();

                        status.feldAufgedeckt();
                    }

                   //Siegprüfung
                    if (status.flaggenGewonnen()) {
                        System.out.println("GEWONNEN – alle Bomben markiert!");
                        spielVorbei = true;
                    }

                    if (status.alleSicherenAufgedeckt()) {
                        System.out.println("GEWONNEN – alle sicheren Felder aufgedeckt!");
                        spielVorbei = true;
                    }

                    return;
                }
            }
        }
    }
}

