/*
 * Lausekielinen ohjelmointi, syksy 2012, toinen harjoitustyö.
 *
 * Esimerkinomaista HT2Apu-luokan käyttöä.
 *
 * Syötteet luetaan In-luokan palveluilla.
 *
 * Jorma Laurikkala, Informaatiotieteiden yksikkö, Tampereen yliopisto,
 * jorma.laurikkala@uta.fi.
 *
 * Viimeksi muutettu 11.12.2012.
 *
 */

public class TxtrisTest {

   /* Alustetaan kaksiulotteinen taulukko t siten, että taulukon reunoille
    * sijoitetaan merkki a ja taulukon sisäalkioihin sijoitetaan merkki b.
    */
   public static void alustaTaulukko(char[][] t, char a, char b) {
      // Käsitellään taulukkoa vain, jos sille on varattu muistia.
      if (t != null) {
         // Rivien ja sarakkeiden lukumäärät.
         int rivlkm = t.length;
         int sarlkm = t[0].length;

         // Asetetaan jompikumpi merkki kuhunkin taulukon alkioon.
         for (int rivi = 0; rivi < rivlkm; rivi++)
            for (int sarake = 0; sarake < sarlkm; sarake++)
               // Reuna-alkio.
               if (rivi == 0 || rivi == rivlkm - 1 || sarake == 0 || sarake == sarlkm - 1)
                  t[rivi][sarake] = a;
               // Sisäalkio.
               else
                  t[rivi][sarake] = b;
      }
   }

   /* Tulostetaan kaksiulotteisen taulukon t alkiot näytölle.
    */
   public static void tulostaTaulukko(char[][] t) {
      // Tulostetaan vain, jos taulukolle on varattu muistia.
      if (t != null) {
         // Rivien ja sarakkeiden lukumäärät.
         int rivlkm = t.length;
         int sarlkm = t[0].length;

         // Tulostetaan rivit.
         for (int rivi = 0; rivi < rivlkm; rivi++) {
            // Tulostetaan rivi.
            for (int sarake = 0; sarake < sarlkm; sarake++)
               System.out.print(t[rivi][sarake]);

            // Rivin lopussa vaihdetaan riviä.
            System.out.println();
         }
      }
   }

   /* Tulostetaan otsikko eli teksti annetulla merkillä kehystettynä.
    */
   public static void tulostaOtsikko(String teksti, char merkki) {
      // Reunan ja ajatelman väli vakiona.
      final String VALI = " ";

      // Selvitetään merkkijonon pituus.
      int pituus = teksti.length();

      // Jos pituus oli OK, niin tulostetaan.
      if (pituus > 0) {
         // Ylärivi.
         for (int i = 0; i < pituus + 2 * (VALI.length() + 1); i++)
            System.out.print(merkki);

         // Keskimmäinen rivi.
         System.out.println();
         System.out.println(merkki + VALI + teksti + VALI + merkki);

         // Alarivi.
         for (int j = 0; j < pituus + 2 * (VALI.length() + 1); j++)
            System.out.print(merkki);

         // Vaihdetaan riviä.
         System.out.println();
      }
   }

   /* Metodi, joka tutkii onko kaksiulotteisessa taulukossa t paikka,
    * jonka rivi- ja sarakeindeksein arvot ovat r ja s. Jos paikka (r, s)
    * on taulukossa, niin paluuarvo true, muuten palautetaan false.
    */
   public static boolean paikkaTaulukossa(int r, int s, char[][] t) {
      // Arvataan, että on ongelmaa.
      boolean onOK = false;

      // Taulukko kunnossa.
      if (t != null) {
         // Selvitetään rivien ja sarakkeiden lukumäärät.
         int rivlkm = t.length;
         int sarlkm = t[0].length;

         // Päätellään onko paikka kunnossa.
         onOK = (0 <= r && r < rivlkm) && (0 <= s && s < sarlkm);
      }

      // Palautetaan tulos.
      return onOK;
   }

   /* Tutkii ovatko paikat taulukossa. Paikka on kunnollinen, jos paikka on
    * taulukon sisällä. Paluuarvo on true, jos kaikki paikat ovat kunnossa.
    * Paluuarvo on false, jos yksikin paikka on virheellinen tai muistia ei
    * ole varattu.
    */
   public static boolean paikatTaulukossa(char[][] taulu, int[][] paikat) {
      // Alustetaan lippu muistinvarauksen mukaan. Sulut mukan selvyyden vuoksi.
      boolean paikatOK = (taulu != null) && (paikat != null);

      // Tarkastellaan paikat yksi kerrallaan. Silmukka pysähtyy heti, kun löytyy
      // kelvoton paikka.
      int i = 0;
      while (paikatOK && i < paikat.length) {
         // Asetetaan paikan indeksiarvot selvyyden vuoksi apumuuttujiin.
         // Paikkataulukon rivin ensimmäinen alkio sisältää paikan rivi-indeksin
         // arvon ja toisessa alkiossa on paikan sarakeindeksin arvo.
         int rivi = paikat[i][0];
         int sarake = paikat[i][1];

         // Tutkitaan onko paikka kunnollinen.
         paikatOK = paikkaTaulukossa(rivi, sarake, taulu);

         // Kasvatetaan laskuria.
         i++;
      }

      // Palautetaan tulos.
      return paikatOK;
   }

   /* Sijoittaa taulukon t paikkoihin p merkin m. Paikkojen rivi- ja sarake-
    * indeksien arvot ovat taulukon p riveillä. Näin kukin taulukon p riveistä
    * on kahden alkion mittainen. Rivin ensimmäinen alkio kertoo paikan rivin
    * indeksin ja toinen alkio paikan sarakkeen indeksin. Metodi ei muuta
    * taulukkoa t millään tavoin, jos muistia ei ole varattu tai taulukossa
    * p on virheellinen indeksiarvo. Paluuarvo on true, jos voitiin sijoittaa,
    * muuten palautetaan false-arvo.
    */
   public static boolean sijoitaPaikkoihin(char[][] t, int[][] p, char m) {
      // Tosi, jos voidaan sijoittaa.
      boolean sijoitusOK = paikatTaulukossa(t, p);

      // Sijoitetaan merkki paikkoihin, jos taulukot ja paikat ovat kunnossa.
      if (sijoitusOK)
         for (int i = 0; i < p.length; i++) {
            // Paikan rivi-indeksin arvo.
            int paikanRivi = p[i][0];
            int paikanSarake = p[i][1];

            // Sijoitetaan merkki taulukon paikkassa olevaan alkioon.
            t[paikanRivi][paikanSarake] = m;
         }

      // Palautetaan paluuarvona lipun arvo.
      return sijoitusOK;
   }

   /* Luo pelikentän sisältävän taulukon, alustaa sen ja palauttaa viitteen.
    */
   public static char[][] luoKentta() {
      // Esitellään muuttuja ja varataan taulukolle muistia.
      char[][] uusiKentta = new char[HT2Apu.RIVIENLKM][HT2Apu.SARAKKEIDENLKM];

      // Alustetaan taulukko kentäksi.
      alustaTaulukko(uusiKentta, HT2Apu.REUNA, HT2Apu.TAUSTA);

      // Palautetaan viite kentän sisältävään taulukkoon.
      return uusiKentta;
   }

   /* Pääsilmukka pelin pelaamiseen.
    */
   public static void pelaaPelia(HT2Apu apulainen) {
      // Luodaan uusi pelikenttä.
      char[][] kentta = luoKentta();

      // Pyydetään palikan paikat alkuasemassa.
      int[][] palikanPaikat = apulainen.annaPalikanPaikat();

      // Kerrotaan.
      System.out.println("Palikka lähtöasemassaan:");

      // Lisätään arvottu palikka kenttään.
      sijoitaPaikkoihin(kentta, palikanPaikat, HT2Apu.PALIKKA);

      // Tulostetaan kenttä.
      tulostaTaulukko(kentta);

      // Kerrotaan lisää.
      System.out.println("Palikka siirrettynä ja kerran kierrettynä:");

      // Pyyhitään palikka vanhasta asemasta.
      sijoitaPaikkoihin(kentta, palikanPaikat, HT2Apu.TAUSTA);

      // Siirretään palikkaa alaspäin kasvattamalla paikkojen rivi-indeksien arvoja.
      for (int rivi = 0; rivi < palikanPaikat.length; rivi++)
         palikanPaikat[rivi][0] = palikanPaikat[rivi][0] + 10;

      // Lasketaan kierretyn palikan paikat.
      int[][] palikanUudetPaikat = HT2Apu.kierraPalikanPaikat(palikanPaikat);

      // Lisätään kierretty palikka kenttään.
      sijoitaPaikkoihin(kentta, palikanUudetPaikat, HT2Apu.PALIKKA);

      // Tulostetaan kenttä.
      tulostaTaulukko(kentta);
   }

   /* Päämetodi, jossa tarkistetaan komentoriviparametri, vaikka oletetaankin aina
    * saatavan siemenluku (yksi kappale kokonaislukuja). Käyttäjälle tulostetaan ohje,
    * jos komentoriviparametri on väärän tyyppinen tai parametreja on väärä määrä.
    * Metodi luo HT2Apu-luokan olion ja välittää sen parametrina pääsilmukkaa
    * suorittavalle metodille, jos siemenluku on kelvollinen.
    */
   public static void main(String[] args) {
      // Tosi, jos komentoriviparametri oli kunnossa.
      boolean argsOK = true;

      // Siemenluku.
      int siemen = 0;

      // Saatiin tasan yksi komentoriviparametri.
      if (args.length == 1) {
         // Yritetään muuntaa komentoriviparametri int-tyyppiseksi siemenluvuksi.
         try {
            siemen = Integer.parseInt(args[0]);
         }
         catch (NumberFormatException e) {
            // Komentoriviparametria ei voitu muuntaa int-tyyppiseksi luvuksi.
            argsOK = false;
         }
      }
      // Nolla tai kaksi tai useampia komentoriviparametreja.
      else
         argsOK = false;

      // Komentoriviparametri kunnossa.
      if (argsOK) {
         // Infoa käyttälle.
         tulostaOtsikko("T X T R I S", '*');

         // Luodaan palikoiden tyyppejä arpova olio ja asetetaan siihen viite.
         // Olion rakentimelle annetaan komentoriviparametrina saatu luku.
         // (Ohjelmassa tulee käyttää vain yhtä HT2Apu-oliota, jotta palikoiden
         // järjestys olisi oikea.)
         HT2Apu apulainen = new HT2Apu(siemen);

         // Pelataan peliä.
         pelaaPelia(apulainen);

         // Lyhyen lyhyet jäähyväiset.
         System.out.println("Bye, see you soon.");
      }
      // Ohjeistetaan virheen tapahtuessa.
      else
         System.out.println("Usage: java Texris seed");
   }
}
