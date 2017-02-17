import java.util.ArrayList;

/*
 * Viikkoharjoitus 5, tehtävä 5.
 *
 * Olio-ohjelmoinnin perusteet, kevät 2017, Juho Karvinen.
 * karvinen.j.juho@student.uta.fi
 *
 * Taulukkolistaa testaava luokka.
 *
 */
 
public class TaulukkolistaTesti {
   public static void main(String[] args) {
      try {
         // Luodaan taulukkolista, jonka alkiot viittaavat Object-luokan
         // tai sen jälkeläisluokkien olioihin.
         ArrayList<Object> taulukkolista = new ArrayList<Object>();
         // Luodaan olioita ja lisätään viitteet taulukkolistalle.
         Integer luku1 = new Integer(10);
         Integer luku2 = new Integer(12);
         String mjono = new String("abc");
         taulukkolista.add(luku1);
         taulukkolista.add(luku2);
         taulukkolista.add(mjono);
         
         // Lasketaan taulukkolistan Integerit
         int lukumaara = laskeIntit(taulukkolista);
         
         // Tulostetaan 
         System.out.println(lukumaara);
      }
      catch (IllegalArgumentException e) {
         System.out.println("Virheellinen syöte!");
      }
      catch (Exception e) {
         System.out.println("Virhe!");
      }
   }
   
   public static int laskeIntit(ArrayList<Object> tl) {
      // Jos parametrinä saadaan null, heitetään poikkeus
      if (tl == null)
         throw new IllegalArgumentException();
      else {
         int lkm = 0;
         // Käydään taulukkolista läpi ja kasvatetaan laskuria aina, kun kohdataan Integer
         for (int i = 0; i < tl.size(); i++) {
            // Asetetaan apuviite kyseessä olevaan alkioon
            Object apu = tl.get(i);
            if (apu instanceof Integer)
               lkm++;
         }
         return lkm;
      }
   }
}