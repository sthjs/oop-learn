/*
 * Viikkoharjoitus 5, tehtävä 4.
 *
 * Olio-ohjelmoinnin perusteet, kevät 2017, Juho Karvinen.
 * karvinen.j.juho@student.uta.fi
 *
 * Ydinvoimalaa testaava luokka.
 *
 */

public class YdinvoimalaTesti {
   public static void main(String[] args) {
      // Luodaan uusia ydinvoimaloita
      Ydinvoimala voimala1 = new Ydinvoimala(10);
      Ydinvoimala voimala2 = new Ydinvoimala(11);
      Ydinvoimala voimala3 = new Ydinvoimala(12);
      Ydinvoimala voimala4 = new Ydinvoimala(10);
      
      // Kokeillaan toteutettua compareTo-metodia
      System.out.println("voimala2 ja voimala2: " + voimala2.compareTo(voimala2));  // 0
      System.out.println("voimala1 ja voimala4: " + voimala1.compareTo(voimala4));  // 0
      System.out.println("voimala1 ja voimala2: " + voimala1.compareTo(voimala2));  // -1
      System.out.println("voimala2 ja voimala1: " + voimala2.compareTo(voimala1));  // 1
      System.out.println("voimala3 ja voimala4: " + voimala3.compareTo(voimala4));  // 1
      System.out.println("voimala4 ja voimala2: " + voimala4.compareTo(voimala2));  // -1
   }
}