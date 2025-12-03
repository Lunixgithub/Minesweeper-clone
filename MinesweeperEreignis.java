class MinesweeperEreignis extends Ereignisbehandlung {

    boolean flagMode = false;
    boolean spielVorbei = false;
    Spielfeld spielfeld;
    GameManager manager;

    MinesweeperEreignis(Spielfeld sf, GameManager manager) {
        this.spielfeld = sf;
        this.manager = manager;
        Starten();
    }

    @Override
    void TasteGedrückt(char taste) {
        if (taste == 'f' || taste == 'F') {
            flagMode = !flagMode;
        }
    }

    @Override
    void MausGeklickt(int x, int y, int anzahl) {

        if (spielVorbei) return;

        for (int r = 0; r < spielfeld.feld.length; r++) {
            for (int c = 0; c < spielfeld.feld[r].length; c++) {

                Zelle z = spielfeld.feld[r][c];

                if (x >= z.getX() && x <= z.getX() + z.getBreite() &&
                    y >= z.getY() && y <= z.getY() + z.getHöhe())
                {
                    //flagmode
                    if (flagMode) {
                        z.toggleFlagge();
                        spielfeld.status.flaggeGeändert(z);// status informieren
                        if (spielfeld.status.flaggenGewonnen()) {
                            spielVorbei = true;
                            this.manager.gewonnenFlaggen();
                    }
                        return;
                    }

                    
                    if (z.istBombe) {
                        z.Aufdecken();
                        spielfeld.status.bombeGetroffen();

                        // ALLE Bomben sichtbar machen
                        for (int i = 0; i < spielfeld.feld.length; i++) {
                            for (int j = 0; j < spielfeld.feld[i].length; j++) {
                                if (spielfeld.feld[i][j].istBombe)
                                    spielfeld.feld[i][j].Aufdecken();
                            }
                        }

                        spielVorbei = true;
                        this.manager.verloren();
                
                        return;
                    }

                    //aufdecken normal
                    if (!z.istAufgedeckt) {
                        if (z.bombeNachbarn == 0){
                            spielfeld.floodFill(r, c);
                        }
                        else {
                            z.Aufdecken();
                            spielfeld.status.feldAufgedeckt();
                        }

                    }

                   //Siegprüfung
                    

                    if (spielfeld.status.alleSicherenAufgedeckt()) {
                        spielVorbei = true;
                        this.manager.gewonnenFelder();
                    }

                    return;
                }
            }
        }
    }
}

