/*
 * Harjoitustyö 2
 *
 * Lausekielinen ohjelmointi, syksy 2012
 * Juho Karvinen, 80348
 *
 * Txtris, tekstipohjainen Tetris-peli
 *
 * Työskentelyloki
 * ***************
 * Ke 12.12.2012 klo 22-23:40
 *    Tutustuin ohjeistukseen.
 * Ma 14.1.2013 klo 10-13
 *    Ohjeistuksen kertausta.
 *    Kirjoitin myös joitain metodeja, jotka olivat yksinkertaisia tai perustuivat
 *    tehtyihin harjoituksiin.
 * Ti 15.1.2013 klo 9-11, 15:45-17
 *    Tutustuin TxtrisTest-luokkaan ja pikaisesti HT2Apu-luokkaan.
 *    Kopioin TxtrisTest-luokan metodit omaan ohjelmaani ja aloin hahmotella pääsilmukan
 *    koodia ohjeistuksessa annetun pseudokoodin perusteella.
 * Ti 29.1.2013 klo 20:30-23
 *    Pääsilmukan ja lueKomento-metodin värkkäämistä. Klo 22: Pelistä on "pelattava" versio,
 *    jossa voi kiertää palikkaa (ei toimi) ja lopettaa pelin.
 * Ke 30.1.2013 klo 13-15:30, 18-22
 *    Kirjoitin metodit paikatTyhjia ja paikkaTyhja mukaillen jo valmiita metodeja. Ne 
 *    tarkistavat, että palikalla on kentällä tilaa siirtyä uuteen paikkaansa. Siirsin
 *    komennon lukemisen lueKomento-metodista pääsilmukkaan ja muutin sen liikutaPalikkaa-
 *    metodiksi, joka vain yrittää liikuttaa palikkaa käyttäjän komennon mukaan.
 *    Tein uuden taulukko-olion, jonka avulla on mahdollista tutkia, onnistuuko aiottu siirto.
 *    Aika paljon säätämistä, kun en heti tajunnut, miten taulukon alkioihin voi sijoittaa
 *    arvoja toisesta taulukosta. Lopulta onnistuin tekemään sitä varten toimivan metodin.
 *    Samalla liikutaPalikkaa alkoi toimia niin, että vuoron ohittaminen ja palikan kiertä-
 *    minen on mahdollista ja että ohjelma tunnistaa, koska palikka kohtaa joko kentän ala-
 *    reunan tai toisen palikan. SEURAAVAKSI TYÖN ALLA LIIKUTTAMINEN SIVUILLE.
 * La 2.2.2013 klo 14:15-19:30
 *    Liikuttaminen sivuille onnistui suht nopeasti. Palikan pudottaminen samoin. Palikan
 *    pudottaminen oli aiemmin ajateltu omaan metodiinsa, mutta nyt se yhdistettiin
 *    liikutaPalikkaa-metodiin. Lisäsin rivien poiston (apuluokasta) ja pisteiden tulostuk-
 *    ja laskun. Klo 15:30 ajattelin, että nyt kaikki ominaisuudet ovat paikoillaan ja
 *    aloin lukea ohjeistusta vielä kerran ennen testeihin siirtymistä. Klo 18:30 ohjelma
 *    on testattu esimerkkiajoilla ja komentotiedostolla. Ohjelman muuttaminen latin-1-
 *    muotoon ei onnistunut, koska kommentteihin oli jäänyt nuolimerkki, jota kyseinen
 *    merkistö ei tunnista. Vielä tehtävänä on kommenttien siivous ja viilaus.
 *
 */

public class Txtris {
   /* Päämetodi, jossa tarkistetaan komentoriviparametri, vaikka oletetaankin aina
    * saatavan siemenluku (yksi kappale kokonaislukuja). Käyttäjälle tulostetaan ohje,
    * jos komentoriviparametri on väärän tyyppinen tai parametreja on väärä määrä.
    * Metodi luo HT2Apu-luokan olion ja välittää sen parametrina pääsilmukkaa
    * suorittavalle metodille, jos siemenluku on kelvollinen.
    * (Lähde: TxtrisTest-luokka.)
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

   /*
    * MUUT METODIT
    *
    * 1. Tulostaa otsikkorivin pelin alkaessa.
    * void tulostaOtsikko(String teksti, char merkki)
    *
    * 2. Pääsilmukka.
    * void pelaaPelia(HT2Apu apulainen)
    *
    * Loput metodit siinä järjestyksessä, kun niitä tarvitaan pääsilmukasta käsin.
    *
    * 3. Luo pelikentän taulukko-olion.
    * char[][] luoKentta()
    * 3.1. Alustaa kentän reunuksineen
    * void alustaTaulukko(char[][] t, char a, char b)
    *
    * 4. Tutkii, ovatko paikkataulukon paikat kentällä.
    * boolean paikatTaulukossa(char[][] taulu, int[][] paikat)
    * 4.1. Tutkii, onko indeksein ilmaistu paikka kentällä.
    * boolean paikkaTaulukossa(int r, int s, char[][] t)
    *
    * 5. Tutkii, ovatko paikkataulukon paikat tyhjiä (siis ' ').
    * boolean paikatTyhjia(char[][] kentta, int[][] paikat)
    * 5.1. Tutkii, onko indeksein ilmaistu paikka tyhjä (siis ' ').
    * boolean paikkaTyhja(int r, int s, char[][] t)
    *
    * 6. Sijoittaa kentälle palikan paikkataulukon mukaisesti.
    * boolean sijoitaPaikkoihin(char[][] t, int[][] p, char m)
    *
    * 7. Tulostaa pelikentän.
    * void tulostaTaulukko(char[][] t)
    *
    * 8. Liikuttaa palikkaa annetun komennon mukaisesti.
    * boolean liikutaPalikkaa(char komento, char[][] kentta, int[][] palikanPaikat)
    * 8.1. Kopioi alkuperäisen taulukon arvot kopion arvoiksi.
    * void sijoitaTaulukonArvot(int[][] alkup, int[][] kopio)
    *
    */

   /* 1. Tulostetaan otsikko eli teksti annetulla merkillä kehystettynä.
    * (Lähde: TxtrisTest-luokka.)
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

   /* 2. Metodi pelin pelaamiseen. Sisältää PÄÄSILMUKAN.
    * Pohjana käytetty TxtrisTest-luokan vastaavaa metodia.
    */
   public static void pelaaPelia(HT2Apu apulainen) {
      // Pääsilmukan lippu. Käännetään, kun peli loppuu pelaajan tai ohjelman toimesta.
      boolean jatketaan = true;

      // Luodaan uusi pelikenttä.
      char[][] kentta = luoKentta();

      // Luodaan palikan paikkakoordinaateille taulukko.
      int[][] palikanPaikat;

      // Muuttuja pisteiden laskua varten.
      int pisteet = 0;

      // Pelin pääsilmukka.
      do {
         // Luodaan uusi palikka. Pyydetään apuluokalta sen paikat alkuasemassa.
         palikanPaikat = apulainen.annaPalikanPaikat();

         // Tarkistetaan, mahtuuko palikka kentälle eli 
         // (1) tarkistetaan varmuuden vuoksi, että arvottu palikka on pelikentällä ja
         // (2) tarkistetaan, että arvotun palikan paikoissa ei ole ennestään palikoita.
         boolean mahtuu = true;
         mahtuu = paikatTaulukossa(kentta, palikanPaikat); // (1)
         mahtuu = paikatTyhjia(kentta, palikanPaikat);     // (2)
         // Lisätään arvottu palikka kenttään.
         if (mahtuu)
            sijoitaPaikkoihin(kentta, palikanPaikat, HT2Apu.PALIKKA);
         // Jos uusi palikka ei mahdu kentälle, peli loppuu.
         else
            jatketaan = false;

         // Tulostetaan pisterivi ja pelikenttä.
         System.out.println("Points: " + pisteet);
         tulostaTaulukko(kentta);

         // Lippu, joka ilmaisee, onko palikka ilmassa.
         boolean ilmassa = true;

         // Kun palikka on ilmassa, edetään vuorotellen pelaajan ja ohjelman vuoroja.
         while (jatketaan && ilmassa) {
            // PELAAJAN VUORO. Ohjeistetaan ja luetaan komento. Tässä yhteydessä oletetaan,
            // että pelaaja ei komenna '@', koska sitä merkkiä käytetään mm. ohjelman vuoron
            // yhteydessä, kun palikkaa liikutetaan alas. Tämän voisi korjata purkkaviri-
            // tyksellä, mutta jääköön nyt siistimmän koodin nimissä korjaamatta.
            System.out.println("left (<), right (>), (r)otate, (d)rop or (q)uit?");
            char komento = In.readChar();
            // 'q': Tulostetaan pisteet ja pelikenttä sekä käännetään lopetuslippu.
            if (komento == 'q') {
               System.out.println("Points: " + pisteet);
               tulostaTaulukko(kentta);
               jatketaan = false;
            }
            // 'd': Pudotetaan palikkaa, kunnes palikka on pohjalla ja käännetään ilmassa-
            // lippu.
            else if (komento == 'd') {
               // Liikutetaan palikkaa alas (komento '@') niin kauan kuin se on mahdollista.
               while (ilmassa)
                  ilmassa = liikutaPalikkaa('@', kentta, palikanPaikat);
            }
            // Palikan liikuttaminen sivuille, kiertäminen sekä vuoron ohittaminen käsitel-
            // lään kokonaan liikutaPalikkaa-metodissa.
            else {
               liikutaPalikkaa(komento, kentta, palikanPaikat);
            }

            // OHJELMAN VUORO.
            if (jatketaan && ilmassa) {
               // Yritetään siirtää palikkaa yksi rivi alas (komento '@'). Jos palikka osuu
               // pohjaan, käännetään ilmassa-lippu.
               ilmassa = liikutaPalikkaa('@', kentta, palikanPaikat);
               if (ilmassa) {
                  // Tulostetaan pisterivi ja pelikenttä.
                  System.out.println("Points: " + pisteet);
                  tulostaTaulukko(kentta);
               }
            } // Ohjelman vuoro
         } // Kierros

         // Poistetaan tyhjät rivit ja annetaan niistä pisteet.
         if (jatketaan) {
            int poistetutRivit = HT2Apu.poistaRivit(kentta);
            pisteet = pisteet + 100 * poistetutRivit;
         }
      } // Pääsilmukka.
      while (jatketaan);
   }

   /* 3. Luo pelikentän sisältävän taulukon, alustaa sen ja palauttaa viitteen.
    * (Lähde: TxtrisTest-luokka.)
    */
   public static char[][] luoKentta() {
      // Esitellään muuttuja ja varataan taulukolle muistia.
      char[][] uusiKentta = new char[HT2Apu.RIVIENLKM][HT2Apu.SARAKKEIDENLKM];

      // Alustetaan taulukko kentäksi.
      alustaTaulukko(uusiKentta, HT2Apu.REUNA, HT2Apu.TAUSTA);

      // Palautetaan viite kentän sisältävään taulukkoon.
      return uusiKentta;
   }

   /* 3.1. Alustetaan kaksiulotteinen taulukko t siten, että taulukon reunoille
    * sijoitetaan merkki a ja taulukon sisäalkioihin sijoitetaan merkki b.
    * (Lähde: TxtrisTest-luokka.)
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

   /* 4. Tutkii ovatko paikat taulukossa. Paikka on kunnollinen, jos paikka on
    * taulukon sisällä. Paluuarvo on true, jos kaikki paikat ovat kunnossa.
    * Paluuarvo on false, jos yksikin paikka on virheellinen tai muistia ei
    * ole varattu.
    * (Lähde: TxtrisTest-luokka.)
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

   /* 4.1. Metodi, joka tutkii onko kaksiulotteisessa taulukossa t paikka,
    * jonka rivi- ja sarakeindeksein arvot ovat r ja s. Jos paikka (r, s)
    * on taulukossa, niin paluuarvo true, muuten palautetaan false.
    * (Lähde: TxtrisTest-luokka.)
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

   /* 5. Metodi tutkii, onko palikan paikoissa ennestään palikoita.
    * Toteutettu mukailemalla paikatTaulukossa-metodia.
    */
   public static boolean paikatTyhjia(char[][] kentta, int[][] paikat) {
      // Alustetaan lippu muistinvarauksen mukaan. Sulut mukan selvyyden vuoksi.
      boolean paikatOK = (kentta != null) && (paikat != null);

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
         paikatOK = paikkaTyhja(rivi, sarake, kentta);

         // Kasvatetaan laskuria.
         i++;
      }
      // Palautetaan tulos.
      return paikatOK;
   }

   /* 5.1. Metodi tutkii, onko annetussa kentän koordinaatissa (r, s) ennestään palikka.
    * Toteutettu mukailemalla paikkaTaulukossa-metodia.
    */
   public static boolean paikkaTyhja(int r, int s, char[][] t) {
      // Arvataan, että on ongelmaa.
      boolean onOK = false;

      // Taulukko kunnossa.
      if (t != null) {
         // Selvitetään, onko kentällä valmiiksi palikka.
         onOK = (t[r][s] == ' ');
      }

      // Palautetaan tulos.
      return onOK;
   }

   /* 6. Sijoittaa taulukon t paikkoihin p merkin m. Paikkojen rivi- ja sarake-
    * indeksien arvot ovat taulukon p riveillä. Näin kukin taulukon p riveistä
    * on kahden alkion mittainen. Rivin ensimmäinen alkio kertoo paikan rivin
    * indeksin ja toinen alkio paikan sarakkeen indeksin. Metodi ei muuta
    * taulukkoa t millään tavoin, jos muistia ei ole varattu tai taulukossa
    * p on virheellinen indeksiarvo. Paluuarvo on true, jos voitiin sijoittaa,
    * muuten palautetaan false-arvo.
    * (Lähde: TxtrisTest-luokka.)
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

   /* 7. Tulostetaan kaksiulotteisen taulukon t alkiot näytölle.
    * (Lähde: TxtrisTest-luokka.)
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

   /* 8. Siirretään palikkaa. Pelaaja voi vuorollaan yrittää siirtää palikkaa sivuille,
    * kiertää sitä tai pudottaa sen alas. Ohjelma yrittää vuorollaan siirtää palikkaa
    * yhden rivin alaspäin. Ohjelman vuoroa varten on kiinnitetty komento '@', joten
    * on oletettava, ettei pelaaja anna sitä syötteeksi. Palikan pudottaminen käyttäjän
    * toimesta on metodissa toteutettu myös komennon '@' avulla, jota pääsilmukassa
    * silmukoidaan, kunnes palikka on alhaalla.
    */
   public static boolean liikutaPalikkaa(char komento, char[][] kentta, int[][] palikanPaikat) {
      // Tarkistetaan, että taulukoille on varattu muistia.
      if (kentta == null || palikanPaikat == null)
         return false;

      // palikanUudetPaikat on taulukko, jonka avulla tutkitaan, onko siirto mahdollinen.
      // Sijoitetaan siihen nykyiset paikat.
      int[][] palikanUudetPaikat = new int[4][2];
      sijoitaTaulukonArvot(palikanPaikat, palikanUudetPaikat);
      // Arvataan, että on ongelmia siirtää.
      boolean siirtoMahdollinen = false;
      // Aluksi pyyhitään palikka vanhasta asemasta.
      sijoitaPaikkoihin(kentta, palikanPaikat, HT2Apu.TAUSTA);

      if (komento == '<') { // Vasemmalle
         // Siirretään palikan uusia paikkoja vasemmalle pienentämällä paikkojen
         // sarakeindeksien arvoja.
         for (int rivi = 0; rivi < palikanUudetPaikat.length; rivi++)
            palikanUudetPaikat[rivi][1] = palikanUudetPaikat[rivi][1] - 1;
      }
      else if (komento == '>') { // Oikealle
         // Siirretään palikan uusia paikkoja oikealle kasvattamalla paikkojen
         // sarakeindeksien arvoja.
         for (int rivi = 0; rivi < palikanUudetPaikat.length; rivi++)
            palikanUudetPaikat[rivi][1] = palikanUudetPaikat[rivi][1] + 1;
      }
      else if (komento == 'r') { // Kierto
         // Kierretään palikan uusia paikkoja HT2Apu-luokan metodilla.
         palikanUudetPaikat = HT2Apu.kierraPalikanPaikat(palikanPaikat);
      }
      else if (komento == '@') { // Alas
         // Siirretään palikan uusia paikkoja alaspäin kasvattamalla paikkojen
         // rivi-indeksien arvoja.
         for (int rivi = 0; rivi < palikanUudetPaikat.length; rivi++)
            palikanUudetPaikat[rivi][0] = palikanUudetPaikat[rivi][0] + 1;
      }

      // Siirto on mahdollinen, jos uudet palikan paikat ovat tyhjiä (eli ' ').
      siirtoMahdollinen = paikatTyhjia(kentta, palikanUudetPaikat);
      // Jos siirto onnistuu, päivitetään palikan nykyiset paikat.
      if (siirtoMahdollinen) {
         sijoitaTaulukonArvot(palikanUudetPaikat, palikanPaikat);
      }
      // Lopuksi asetetaan palikka (päivitettyyn tai vanhaan) asemaansa kentällä.
      sijoitaPaikkoihin(kentta, palikanPaikat, HT2Apu.PALIKKA);
      // Palautetaan tieto siitä, onnistuiko siirto.
      return siirtoMahdollinen;
   }

   /* 8.1. Kopioi taulukon (alkup) arvot toiseen taulukkoon (kopio).
    */
   public static void sijoitaTaulukonArvot(int[][] alkup, int[][] kopio) {
      // Tarkistetaan, että taulukoille on varattu muistia.
      if (alkup != null && kopio != null) {
         // Käydään alkuperäisen taulukon alkiot läpi ja sijoitetaan arvot kopion alkioihin.
         for (int i = 0; i < alkup.length; i++)
            for (int j = 0; j < alkup[i].length; j++)
               kopio[i][j] = alkup[i][j];
      }
   }

}
