/*
 * Viikkoharjoitus 5, teht�v� 4.
 *
 * Olio-ohjelmoinnin perusteet, kev�t 2017, Juho Karvinen.
 * karvinen.j.juho@student.uta.fi
 *
 * Konkreettinen voimalaa mallintava luokka.
 *
 */

public class Voimala implements Comparable<Voimala> {

   /*
    * Attribuutit.
    *
    */

   // Voimalan teho megawatteina.
   private double teho;

   /*
    * Rakentajat.
    *
    */

   public Voimala(double mw) {
      teho(mw);
   }

   /*
    * Aksessorit.
    *
    */

   public double teho() {
      return teho;
   }

   public void teho(double mw) {
      if (mw >= 0)
         teho = mw;
   }

   /*
    * Oliometodit.
    *
    */

   public void hajoa() {
      System.out.println("Poks!");
   }
   
   // Hajoitetaan voimala tietyll� todenn�k�isyydell�
   public void hajoa(double todnak) {
      if (0 <= todnak && todnak <= 100) {
         // Heitet��n kolikkoa
         boolean hajoa = Math.random() < todnak / 100;
         // Hajoitetetaan, jos kolikko on true
         if (hajoa)
            hajoa();
      }
   }
   
   /*
    * Toteutettu Comparable-rajapinnan metodi.
    *
    */
   @Override
   public int compareTo(Voimala v) {
      // T�m� olio < parametrina saatu olio
      if (teho() < v.teho()) 
         return -1;
      // T�m� olio == parametrina saatu olio
      else if (teho() == v.teho())
         return 0;
      else
         return 1;
   }
}
