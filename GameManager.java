
import javax.swing.JOptionPane;
/**
 * Beschreiben Sie hier die Klasse GameManager.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class GameManager
{
   private Spielfeld spielfeld;
   private MinesweeperEreignis ereignis;
   
   
   public GameManager () {
       reset();
   }
   
   private void reset() {
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
