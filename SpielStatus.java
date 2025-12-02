public class SpielStatus {

    int bombenGesamt;
    int sichereFelderGesamt;

    int gesetzteFlaggen = 0;
    int korrektGesetzteFlaggen = 0;
    int aufgedeckteFelder = 0;

    boolean bombeGetroffen = false;

    Zelle[][] feld;

    SpielStatus(Zelle[][] feldArray, int bomben) {
        this.feld = feldArray;
        this.bombenGesamt = bomben;

        // sichere Felder bestimmen mit a*b wegen 2D array
        int total = feldArray.length * feldArray[0].length;
        sichereFelderGesamt = total - bomben;
    }

    
    void flaggeGeändert(Zelle z) {

        if (z.hatFlagge) {
            gesetzteFlaggen++;

            if (z.istBombe)
                korrektGesetzteFlaggen++;
        }
        else {
            gesetzteFlaggen--;

            if (z.istBombe)
                korrektGesetzteFlaggen--;
        }
    }

 
    void feldAufgedeckt() {
        aufgedeckteFelder++;
    }

   
    void bombeGetroffen() {
        bombeGetroffen = true;
    }

 
    boolean flaggenGewonnen() {
        return korrektGesetzteFlaggen == bombenGesamt && gesetzteFlaggen == bombenGesamt && !bombeGetroffen;
    }

    
    boolean alleSicherenAufgedeckt() {
        return aufgedeckteFelder == sichereFelderGesamt
               && !bombeGetroffen;
    }
}
