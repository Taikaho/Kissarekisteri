package kissalaMain;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Pitää huolen Kissat ja Kissalat luokkien yhteistyöstä, ja mahdollistaa luokkien välisen tiedonsiirron
 * Lukee ja kirjoittaa tietokantaan Kissala ja Kissa luokan tietoja
 *
 * @author Toni Taikina-aho (tokaanta@jyu.fi)
 * @version 1.0, 07.08.24
 *
 * Testien alustus
 * @example
 * <pre name="testJAVA">
 *  #import: java.io.*;
 *  private Kissalarekisteri testirekisteri;
 *  private Kissala kissala1;
 *  private Kissala kissala2;
 *  private Kissa kissa1;
 *  private Kissa kissa2;
 *  private Kissa kissa3;
 *  private Kissa kissa4;
 *  private Kissa kissa5;
 *
 *  // @SuppressWarnings("javadoc")
 *  public void alustaRekisteri() {
 *    testirekisteri = new Kissalarekisteri();
 *    Kissala testiKissala = new Kissala(); testiKissala.taytaKissalaTiedoilla();
 *    testirekisteri.lisaaKissala(testiKissala);
 *    kissa1 = new Kissa(); kissa1.taytaKissaTiedoilla(); kissa1.rekisteroi();
 *    kissa2 = new Kissa(); kissa2.taytaKissaTiedoilla(); kissa2.rekisteroi();
 *    kissa3 = new Kissa(); kissa3.taytaKissaTiedoilla(); kissa3.rekisteroi();
 *    kissa4 = new Kissa(); kissa4.taytaKissaTiedoilla(); kissa4.rekisteroi();
 *    kissa5 = new Kissa(); kissa5.taytaKissaTiedoilla(); kissa5.rekisteroi();
 *    testirekisteri.lisaa(kissa1);
 *    testirekisteri.lisaa(kissa2);
 *    testirekisteri.lisaa(kissa3);
 *    testirekisteri.lisaa(kissa4);
 *    testirekisteri.lisaa(kissa5);
 *  }
 * </pre>
 */
public class Kissalarekisteri {
    private static Kissat kissat;
    private static Kissalat kissalat = new Kissalat();
    private static Kissala kaytettavaKissala;


    /**
     * Kissalarekisterin muodostaja lataa tiedostot
     */
    public Kissalarekisteri() {
        kissat = new Kissat(this);
        lueTiedostosta("kissalarekisteri");
        System.out.println(kissalat.annaKissalat());
    }


    /**
     * Lisää kissalan kissalat listaan
     * @param kissala lisättävä kissala
     *
     * @example
     * <pre name="testLisaaKissala">
     * alustaRekisteri();
     * Kissala kissala3 = new Kissala(); kissala3.taytaKissalaTiedoilla(); kissala3.rekisteroi();
     * testirekisteri.annaKissalat().size() === 1;
     * testirekisteri.lisaaKissala(kissala3); kissala3.rekisteroi();
     * testirekisteri.annaKissalat().size() === 2;
     */
    public void lisaaKissala(Kissala kissala) {
        kaytaTataKissalaa(kissala);
        kissalat.lisaa(kissala);
    }


    /**
     * Lisää kissalan kissalat listaan
     * @param kissa lisättävä kissala
     *
     * @example
     * <pre name="testLisaa">
     * alustaRekisteri();
     * Kissa kissa6 = new Kissa(); kissa6.taytaKissaTiedoilla(); kissa6.rekisteroi();
     * testirekisteri.getKissoja() === 5;
     * testirekisteri.lisaa(kissa6); kissa6.rekisteroi();
     * testirekisteri.getKissoja() === 6;
     */
    public void lisaa(Kissa kissa) {
        kissat.lisaa(kissa);
    }


    /**
     * Palauttaa kissojen lukumäärän
     * @return kissat.getLkm()
     */
    public int getKissoja() {
        return kissat.getLkm();
    }


    /**
     * Palauttaa käytettävän kissalan
     * @return kaytettavaKissala
     */
    public Kissala getKaytettavaKissala() {
        return kaytettavaKissala;
    }


    /**
     * Palauttaa kaikki kissat
     * @return lista kissaloista
     */
    public ArrayList<Kissala> annaKissalat() {
        return kissalat.annaKissalat();
    }


    /**
     * Lukee tiedostosta kissat ja kissalat
     * @param tiedostonNimi tiedoston nimi
     *
     * @example
     * <pre name="testLueJaTallennaTiedosto">
     * #THROWS Exception
     * alustaRekisteri();
     * testirekisteri.tallenna("testi");
     * testirekisteri.poista(kissa1);
     * testirekisteri.poista(kissa2);
     * testirekisteri.poista(kissa3);
     * testirekisteri.poista(kissa4);
     * testirekisteri.poista(kissa5);
     * testirekisteri.getKissoja() === 0;
     * testirekisteri.lueTiedostosta("testi");
     * testirekisteri.getKissoja() === 5;
     *
     */
    public void lueTiedostosta(String tiedostonNimi) {
        kissat = new Kissat(this);
        kissalat = new Kissalat();
        kissat.lueTiedostosta(tiedostonNimi + "_kissat.json");
        System.out.println("Kissoja luettu: " + kissat.getLkm());
        kissalat.lueTiedostosta(tiedostonNimi + "_kissalat.json");
    }


    /**
     * Kutsutaan Kissa ja Kissat luokan tallenna metodia
     * @param tiedNimi tiedoston nimen alkuosa
     * @throws Exception
     */
    public void tallenna(String tiedNimi) throws Exception {
        String virhe = "";
        try {
            kissat.tallenna(tiedNimi + "_kissat.json");
        } catch (Exception e) {
            virhe += e.getMessage();
        }
        try {
            kissalat.tallenna(tiedNimi + "_kissalat.json");
        }catch (Exception e) {
            virhe += e.getMessage();
        }
        if (!"".equals(virhe)) throw new Exception(virhe);
    }


    /**
     * Asetetaan valittu kissala käyttöön
     */
    public void kaytaTataKissalaa(Kissala kissala) {
        this.kaytettavaKissala = kissala;
        System.out.println("Käytetään kissalaa " + kaytettavaKissala.getID() + " " + kaytettavaKissala.getKissalanNimi());
    }


    /**
     * Korvaa tai lisää kissan
     * @param kissa korvattava tai lisättävä kissa
     */
    public void korvaaTaiLisaa(Kissa kissa) {
        kissat.korvaaTaiLisaa(kissa);
    }


    /**
     * Korvaa tai lisää kissalan
     * @param kissala korvattava tai lisättävä kissala
     */
    public void korvaaTaiLisaa(Kissala kissala) {
        kissalat.korvaaTaiLisaa(kissala);
    }


    /**
     * Etsii kissan listasta
     * @param hakuehto hakuehto
     * @param k montako kpl
     * @return löydetyt kissat
     */
    public Collection<Kissa> etsi(String hakuehto, int k) {
        return kissat.etsi(hakuehto, k);
    }


    /**
     * Poistaa kissan
     */
    public void poista(Kissa kissa) {
        kissat.poista(kissa);
    }
}
