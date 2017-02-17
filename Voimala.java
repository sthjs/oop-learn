/*
 * Viikkoharjoitus 5, tehtävä 4.
 *
 * Olio-ohjelmoinnin perusteet, kevät 2017, Juho Karvinen.
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
   
   // Hajoitetaan voimala tietyllä todennäköisyydellä
   public void hajoa(double todnak) {
      if (0 <= todnak && todnak <= 100) {
         // Heitetään kolikkoa
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
      // Tämä olio < parametrina saatu olio
      if (teho() < v.teho()) 
         return -1;
      // Tämä olio == parametrina saatu olio
      else if (teho() == v.teho())
         return 0;
      else
         return 1;
   }
}
