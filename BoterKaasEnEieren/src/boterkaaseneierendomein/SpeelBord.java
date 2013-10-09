package boterkaaseneierendomein;

import java.util.HashMap;

/**
 * Een speelbord voor boter kaas en eieren
 * @author Serge Fonville
 * 
 */
public class SpeelBord {
   private HashMap<Veld, VeldStatus> velden =  new HashMap<Veld, VeldStatus>();
   private Boolean spelerEen = true;
   /**
    * Initiatliseert een nieuwe instantie van SpeelBord
    */
   public SpeelBord() {
       velden.clear();
       startNieuwSpel();
   }
   
   /**
    * Kies ongebruikt een veld en wissel van speler<br/>
    * Als het veld al gebruikt is doet deze functie niets
    * @param veld het te kiezen
    */
   public void kiesVeld(Veld veld) {
       if(velden.get(veld) == VeldStatus.ONGEBRUIKT) {
           VeldStatus veldStatus = this.spelerEen?VeldStatus.SPELEREEN:VeldStatus.SPELERTWEE;
           this.velden.put(veld, veldStatus);
           this.spelerEen = !this.spelerEen;
       }
   }
   /**
    * 
    * @param veld het veld waarvoor de status wordt opgevraagd
    * @return geeft de status van het veld terug
    */
   public VeldStatus geefVeldStatus(Veld veld) {
       return this.velden.get(veld);
   }
   
   /**
    * Start een nieuw spel.
    * Hierbij worden alle velden opnieuw ingesteld met status ONGEBRUIKT en is speler een aan de beurt
    */
   public void startNieuwSpel() {
	   this.spelerEen = true;
	   for(Veld v: Veld.values()) {
           velden.put(v, VeldStatus.ONGEBRUIKT);
       }
   }
}
