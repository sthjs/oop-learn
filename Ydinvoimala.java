/*
 * Viikkoharjoitus 5, tehtävä 4.
 *
 * Olio-ohjelmoinnin perusteet, kevät 2017, Juho Karvinen.
 * karvinen.j.juho@student.uta.fi
 *
 * Konkreettinen ydinvoimalaa mallintava luokka.
 *
 */

public class Ydinvoimala extends Voimala {
   // Ei mainittu tehtävänannossa
   public Ydinvoimala(double mw) {
      super(mw);
   }
   
   // Korvataan parametritön hajoa-metodi
   public void hajoa() {
      System.out.println("Fuu");
   }
}
