
import javax.swing.JOptionPane;
/**
 * eine Übergeordnete Verwalterklasse die den Status des Spiels verwaltet (gewonnen, verloren, im spiel)
* 
 * 
 */
public class GameManager
{
   private Spielfeld spielfeld;
   private MinesweeperEreignis ereignis;
   
   
   public GameManager () { //beim erstellen ein neues Spielfeld und MinesweeperEreignis erstellen
       reset();
   }
   
   private void reset() { // neues Spiel
       this.spielfeld = new Spielfeld();
       this.ereignis = new MinesweeperEreignis(this.spielfeld, this);
   }
   
   public void verloren() {
       JOptionPane.showMessageDialog(null, "Du hast verloren! Versuche es nochmal");
       reset();
   }
   
   public void gewonnenFlaggen() {
       JOptionPane.showMessageDialog(null, "Gewonnen! Alle Flaggen makiert ");
       reset();
   }
   public void gewonnenFelder() {
        JOptionPane.showMessageDialog(null, "Gewonnen! Alle Felder aufgedeckt! ");
       reset();
   }
}
