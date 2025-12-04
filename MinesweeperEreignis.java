class MinesweeperEreignis extends Ereignisbehandlung {

    boolean flagMode = false;
    boolean spielVorbei = false;
    Spielfeld spielfeld;
    GameManager manager;
    Text flaggenText = new Text();

    MinesweeperEreignis(Spielfeld sf, GameManager manager) {
        this.spielfeld = sf;
        this.manager = manager;
        flaggenText.TextSetzen("Flaggenmodus kann durch drücken von f aktiviert werden");
        flaggenText.FarbeSetzen("rot");
        flaggenText.TextGrößeSetzen(15);
        flaggenText.PositionSetzen(0, 520);
        Starten();
    }

    @Override
    void TasteGedrückt(char taste) {
        if (taste == 'f' || taste == 'F') {
            flagMode = !flagMode;
            if (flagMode == true) {
                flaggenText.TextSetzen("Flaggenmodus aktiviert");
            }
            else {
                flaggenText.TextSetzen("Flaggenmodus deaktiviert");
            }
        }
    }

    @Override
    void MausGeklickt(int x, int y, int anzahl) {

        if (spielVorbei){
            flaggenText.Entfernen();
            return;
        }

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
                        z.aufdecken();
                        spielfeld.status.bombeGetroffen();

                        // ALLE Bomben sichtbar machen
                        for (int i = 0; i < spielfeld.feld.length; i++) {
                            for (int j = 0; j < spielfeld.feld[i].length; j++) {
                                if (spielfeld.feld[i][j].istBombe)
                                    spielfeld.feld[i][j].aufdecken();
                            }
                        }

                        spielVorbei = true;
                        this.manager.verloren();
                        flaggenText.Entfernen();
                
                        return;
                    }

                    //aufdecken normal
                    if (!z.istAufgedeckt) {
                        if (z.bombeNachbarn == 0){
                            spielfeld.floodFill(r, c);
                        }
                        else {
                            z.aufdecken();
                            spielfeld.status.feldAufgedeckt();
                        }

                    }

                   //Siegprüfung
                    

                    if (spielfeld.status.alleSicherenAufgedeckt()) {
                        spielVorbei = true;
                        this.manager.gewonnenFelder();
                        flaggenText.Entfernen();
                    }

                    return;
                }
            }
        }
    }
}

