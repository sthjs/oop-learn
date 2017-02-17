/*
 * Viikkoharjoitus 5, teht�v� 4.
 *
 * Olio-ohjelmoinnin perusteet, kev�t 2017, Juho Karvinen.
 * karvinen.j.juho@student.uta.fi
 *
 * Konkreettinen ydinvoimalaa mallintava luokka.
 *
 */

public class Ydinvoimala extends Voimala {
   // Ei mainittu teht�v�nannossa
   public Ydinvoimala(double mw) {
      super(mw);
   }
   
   // Korvataan parametrit�n hajoa-metodi
   public void hajoa() {
      System.out.println("Fuu");
   }
}
