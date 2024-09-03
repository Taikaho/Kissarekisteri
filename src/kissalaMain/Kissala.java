package kissalaMain;

/**
 * @author Toni Taikina-aho (tokaanta@jyu.fi)
 * @version 1.0, 07.08.24
 *
 * Tietää kissalan tietokentät
 * Osaa muuttaa tietokannan merkkijonon kissalan tiedoiksi
 * Osaa antaa tietyn kentän tiedot (getter)
 * Osaa muuttaa tietyn kentän tiedot (setter)
 * Tarkistaa, että annetut tiedot ovat oikeita
 */
public class Kissala implements Cloneable{

    private int id;
    private String kissalanNimi;
    private String kasvattajanEtunimi;
    private String kasvattajanSukunimi;
    private String katuosoite;
    private int postinumero;
    private String paikkakunta;
    private static int seuraavaNumero = 1;
    private int ekaKentta = 0;
    private int kenttia = 7;


    /**
     * Konstruktori, joka luo uuden kissalan
     * @param id kissalan id
     * @param kissNimi kissalan nimi
     * @param kasEtu kasvattajan etunimi
     * @param kasSuku kasvattajan sukunimi
     * @param katu katuosoite
     * @param postiNro postinumero
     * @param pk paikkakunta
     */
    public Kissala (int id, String kissNimi, String kasEtu, String kasSuku, String katu, int postiNro, String pk) {
        setId(id);
        this.kissalanNimi = kissNimi;
        this.kasvattajanEtunimi = kasEtu;
        this.kasvattajanSukunimi = kasSuku;
        this.katuosoite = katu;
        this.postinumero = postiNro;
        this.paikkakunta = pk;
    }


    /**
     * Konstruktori, joka luo uuden kissalan tyhjillä tiedoilla
     */
    public Kissala() {
        this.id = 00;
        this.kissalanNimi = "";
        this.kasvattajanEtunimi = "";
        this.kasvattajanSukunimi = "";
        this.katuosoite = "";
        this.postinumero = 00;
        this.paikkakunta = "";
    }


    /**
     * Palauttaa kissalan id:n
     * @return id
     */
    public int getID() {return this.id;}


    /**
     * Palauttaa kasvattajan etunimen
     * @return kasvattajan etunimi
     */
    public String getKasvattajanNimi() {return this.kasvattajanEtunimi;}


    /**
     * Palauttaa kasvattajan sukunimen
     * @return kasvattajanSukunimi
     */
    public String getKasvattajanSnimi() {
        return this.kasvattajanSukunimi;
    }


    /**
     * Palauttaa katuosoitteen
     * @return katuosoite
     */
    public String getKatuosoite() {
        return this.katuosoite;
    }


    /**
     * Palauttaa kasvattajan sukunimen
     * @return kasvattajan sukunimi
     */
    public String getKasvattajanSukunimi() {return this.kasvattajanSukunimi;}


    /**
     * Palauttaa kissalan nimen
     * @return kissalan nimi
     */
    public String getKissalanNimi() {return this.kissalanNimi;}


    /**
     * Palauttaa katuosoitteen
     * @return katuosoite
     */
    public String getOsoite() {return this.katuosoite;}


    /**
     * Palauttaa postinumeron
     * @return postinumero
     */
    public int getPostinumero() {return this.postinumero;}


    /**
     * Palauttaa paikkakunnan
     * @return paikkakunta
     */
    public String getPaikkakunta() { return this.paikkakunta;}


    /**
     * Asettaa kissalan id:n ja lisää juoksevaa numeroa yhdellä
     */
    public void rekisteroi() {
        this.id = seuraavaNumero;
        seuraavaNumero++;
    }


    /**
     * Palauttaa kissalan nimen
     * @return kissalanNimi
     */
    public String getNimi() {
        return this.kissalanNimi;
    }


    /**
     * Palauttaa kissalan id:n
     * @return id
     */
    public int getId () {
        return this.id;
    }


    /**
     * Palauttaa kasvattajan etunimen
     * @return kasvattajanEtunimi
     */
    public String getKasvattajanEnimi() {
        return this.kasvattajanEtunimi;
    }


    /**
     * Asettaa kissalan id:n ja tarkistaa, että seuraava id on suurempi kuin edellinen
     * @param id
     */
    public void setId(int id) {
        this.id = id;
        if (this.id >= seuraavaNumero) seuraavaNumero = this.id + 1;
    }


    /**
     * Palauttaa ensimmäisen kentän
     * @return ekaKentta
     */
    public int ekaKentta() {
        return this.ekaKentta;
    }


    /**
     * Palauttaa kissalan tiedot merkkijonona, jonka voi tallentaa tiedostoon.
     * @return kissan tiedot merkkijonona
     *
     * @example
     * <pre name="test">
     *     Kissala kissala = new Kissala();
     *     kissala.taytaKissalaTiedoilla();
     *     kissala.toString().equals(
     *     "{\n" +
     *     "\"id\": \"1\",\n" +
     *     "\"Kissalan nimi\": \"Testikissala\",\n" +
     *     "\"Kasvattajan etunimi\": \"Testietunimi\",\n" +
     *     "\"Kasvattajan sukunimi\": \"Testisukunimi\",\n" +
     *     "\"Katuosoite\": \"Testikatu\",\n" +
     *     "\"Postinumero\": \"12345\",\n" +
     *     "\"Paikkakunta\": \"Testipaikkakunta\"\n" +
     *     "}"
     *     );
     *    </pre>
     */
    @Override
    public String toString() {
        return
                "{\n" +
                        "\"id\": \"" + this.id + "\",\n" +
                        "\"Kissalan nimi\": \"" + this.kissalanNimi + "\",\n" +
                        "\"Kasvattajan etunimi\": \"" + this.kasvattajanEtunimi + "\",\n" +
                        "\"Kasvattajan sukunimi\": \"" + this.kasvattajanSukunimi + "\",\n" +
                        "\"Katuosoite\": \"" + this.katuosoite + "\",\n" +
                        "\"Postinumero\": \"" + this.postinumero + "\",\n" +
                        "\"Paikkakunta\": \"" + this.paikkakunta + "\"\n" +
                        "}";
    }


    /**
     * Luodaan tilapäinen klooni muokkausta varten.
     * @return klooni kissalasta
     * @throws CloneNotSupportedException
     */
    @Override
    public Kissala clone() throws CloneNotSupportedException {
        Kissala uusi;
        uusi = (Kissala)super.clone();
        return uusi;
    }


    /**
     * Muuttaa kissalan tietoja
     * @param k valittu kenttä jossa tietoja muutetaan
     * @param s käyttäjän antama syöte, joka tallennetaan muuttujaan
     * @return null jos kaikki ok, muuten virheilmoitus
     */
    public String aseta(int k, String s) {
        switch (k) {
            case 0: this.id = Integer.parseInt(s);
                return null;
            case 1: this.kissalanNimi = s;
                return null;
            case 2: this.kasvattajanEtunimi = s;
                return null;
            case 3: this.kasvattajanSukunimi = s;
                return null;
            case 4: this.katuosoite = s;
                return null;
            case 5: this.postinumero = Integer.parseInt(s);
                return null;
            case 6: this.paikkakunta = s;
                return null;
            default: return "Muokattavaa kenttää ei löydy";
        }
    }


    /**
     * Palauttaa kenttien lukumäärän
     * @return kenttia
     */
    public int getKenttia() {
        return this.kenttia;
    }


    /**
     * Antaa kenttien nimet kenttien luomista varten
     * @param k kenttä, jonka niemeä haetaan
     * @return
     */
    public String getKysymys(int k) {
        switch ( k ) {
            case 0: return "id";
            case 1: return "kissalan nimi";
            case 2: return "kasvattajan etunimi";
            case 3: return "kasvattajan sukunimi";
            case 4: return "katuosoite";
            case 5: return "postinumero";
            case 6: return "paikkakunta";
            default: return "Virhe";
        }
    }


    /**
     * Täyttää kissalan tiedot testausta varten
     */
    public void taytaKissalaTiedoilla() {
        this.id = 1;
        this.kissalanNimi = "Testikissala";
        this.kasvattajanEtunimi = "Testietunimi";
        this.kasvattajanSukunimi = "Testisukunimi";
        this.katuosoite = "Testikatu";
        this.postinumero = 12345;
        this.paikkakunta = "Testipaikkakunta";
    }
}


