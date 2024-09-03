package kissalaMain;

import java.io.PrintStream;

/**
 * @author Toni Taikina-aho (tokaanta@jyu.fi)
 * @version 1.0, 07.08.24
 *
 * Tietää kissan tietokentät (nimi, id jne..)
 * Osaa muuttaa tietokannan merkkijonon kissan tiedoksi.
 * Osaa antaa tietyn kentän tiedot (getter)
 * Osaa muuttaa tietyn kentän tiedot (setter)
 * Tarkistaa, että annetut tiedot ovat oikeita
 */
public class Kissa implements Cloneable {

    private int id;
    private int kissalanId;
    private int rekisterinumero;
    private String nimi;
    private String kutsumanimi;
    private String rotu;
    private String veriryhmä;
    private String kotiosoite;
    private int postinumero;
    private String syntymäaika;
    private String isä;
    private String äiti;
    private String sijoitettu;
    private String pennut;
    private String FIV;
    private String FeLV;
    private static int seuraavaNro = 1;

    public Kissa (int id, int kissalanId, int rekisterinumero, String nimi, String kutsumanimi, String rotu, String veriryhmä,
                  String kotiosoite, int postinumero, String syntymäaika, String isä, String äiti, String sijoitettu,
                  String pennut, String fiv, String felv) {

        setId(id);
        this.kissalanId = kissalanId;
        this.rekisterinumero = rekisterinumero;
        this.nimi = nimi;
        this.kutsumanimi = kutsumanimi;
        this.rotu = rotu;
        this.veriryhmä = veriryhmä;
        this.kotiosoite = kotiosoite;
        this.postinumero = postinumero;
        this.syntymäaika = syntymäaika;
        this.isä = isä;
        this.äiti = äiti;
        this.sijoitettu = sijoitettu;
        this.pennut = pennut;
        this.FIV = fiv;
        this.FeLV = felv;
    }


    public Kissa() {
        this.kissalanId = 00;
        this.rekisterinumero = 00;
        this.nimi = "anna nimi";
        this.kutsumanimi = "anna kutsumanimi";
        this.rotu = "anna rotu";
        this.veriryhmä = "anna veriryhmä";
        this.kotiosoite = "anna kotiosoite";
        this.postinumero = 00;
        this.syntymäaika = "anna syntymäaika";
        this.isä = "anna isän nimi";
        this.äiti = "anna äidin nimi";
        this.sijoitettu = "onko kissa sijoitusperheessä";
        this.pennut = "listaa pennut, jos on";
        this.FIV = "fiv tulos";
        this.FeLV = "felv tulos";
    }

    
    /**
     * Palauttaa kissan id-numeron
     * @return id
     */
    public int getID() {
        return id;
    }


    /**
     * Palauttaa kissalan id-numeron
     * @return kissalanId
     */
    public int getKissalanID() {
        return kissalanId;
    }


    /**
     * Palauttaa kissan rekisterinumeron
     * @return rekisterinumero
     */
    public int getRekisterinumero() {
        return rekisterinumero;
    }


    /**
     * Palauttaa kissan nimen
     * @return nimi
     */
    public String getNimi() {
        return nimi;
    }


    /**
     * Palauttaa kissan kutsumanimen
     * @return kutsumanimi
     */
    public String getKutsumanimi() {
        return kutsumanimi;
    }


    /**
     * Palauttaa kissan rodun
     * @return rotu
     */
    public String getRotu() {
        return rotu;
    }


    /**
     * Palauttaa kissan veriryhmän
     * @return veriryhmä
     */
    public String getVeriryhmä() {
        return veriryhmä;
    }


    /**
     * Palauttaa kissan kotiosoitteen
     * @return kotiosoite
     */
    public String getKotiosoite() {
        return kotiosoite;
    }


    /**
     * Palauttaa kissan postinumeron
     * @return postinumero
     */
    public int getPostinumero() {
        return postinumero;
    }


    /**
     * Palauttaa kissan syntymäajan
     * @return syntymäaika
     */
    public String getSyntymäaika() {
        return syntymäaika;
    }


    /**
     * Palauttaa kissan isän nimen
     * @return isä
     */
    public String getIsä() {
        return isä;
    }


    /**
     * Palauttaa kissan äidin nimen
     * @return äiti
     */
    public String getÄiti() {
        return äiti;
    }


    /**
     * Palauttaa tiedon, onko kissa sijoitettu
     * @return sijoitettu
     */
    public String getSijoitettu() {
        return sijoitettu;
    }


    /**
     * Palauttaa tiedon, onko kissalla pentuja
     * @return pennut
     */
    public String getPennut() {
        return pennut;
    }


    /**
     * Palauttaa FIV-testin tuloksen
     * @return FIV
     */
    public String getFIV() {
        return FIV;
    }


    /**
     * Palauttaa FeLV-testin tuloksen
     * @return FeLV
     */
    public String getFeLV() {
        return FeLV;
    }


    /**
     * Palauttaa seuraavan numeron
     * @return seuraavaNro
     */
    public static int getSeuraavaNro() {
        return seuraavaNro;
    }


    /**
     * Palauttaa kenttien määrän
     * @return 16
     */
    public int getKenttia() {
        return 16;
    }


    /**
     * Eka kenttä joka on mielekäs kysyttäväksi
     * @return eknn kentän indeksi
     */
    public int ekaKentta() {
        return 0;
    }


    /**
     * Asettetaan kissalle kissalan id:n
     * @param i kissalan id
     */
    public void setKissalanId(int i) {
        this.kissalanId = i;
    }


    /** Tulostetaan kissaan tiedot.
     * @param out tietovirta, johon tulostetaan.
     */
    public void tulosta(PrintStream out) {
        out.println("id: " + this.id);
        out.println("Kissalan id: " +  this.kissalanId);
        out.println("Nimi: " + this.nimi);
        out.println("Kutsumanimi: " + this.kutsumanimi);
        out.println("Rekisterinumero: " + this.rekisterinumero);
        out.println("Rotu: " + this.rotu);
        out.println("Veriryhmä: " + this.veriryhmä);
        out.println("Kotiosoite: " + this.kotiosoite);
        out.println("Postinumero: " + this.postinumero);
        out.println("Syntymäaika: " + this.syntymäaika);
        out.println("Isä: " + this.isä);
        out.println("Äiti: " + this.äiti);
    }


    /** Tulostetaan kissan id
     * @param out tietovirta, johon tulostetaan.
     */
    public void tulostaId (PrintStream out) {
        out.println("Kissan id: " + this.id);
    }


    /**
     * Kissan tunnukseksi asetetaan juokseva numero. Seuraava numero laskuria lisätään yhdellä.
     */
    public void rekisteroi() {
        this.id = seuraavaNro;
        seuraavaNro++;
    }


    /**
     * Palauttaa kissan tiedot merkkijonona, jonka voi tallentaa tiedostoon.
     * @return kissan tiedot merkkijonona
     *
     * @example
     * <pre name="test">
     *     Kissa kissa = new Kissa();
     *     kissa.taytaKissaTiedoilla();
     *     kissa.toString().equals(
     *         "{\n" +
     *         "  \"id\": \"1\",\n" +
     *         "  \"Kissalan ID\": \"1\",\n" +
     *         "  \"Rekisterinumero\": \"123456789\",\n" +
     *         "  \"Nimi\": \"Kissa\",\n" +
     *         "  \"Kutsumanimi\": \"Helmi\",\n" +
     *         "  \"Rotu\": \"Brittiläinen lyhytkarva\",\n" +
     *         "  \"Veriryhmä\": \"A\",\n" +
     *         "  \"Kotiosoite\": \"Kissakuja 7\",\n" +
     *         "  \"Postinumero\": \"90100\",\n" +
     *         "  \"Syntymäaika\": \"01.01.2020\",\n" +
     *         "  \"Isä\": \"Mese\",\n" +
     *         "  \"Äiti\": \"Siru\",\n" +
     *         "  \"Sijoitettu\": \"kyllä\",\n" +
     *         "  \"Pennut\": \"Ei\",\n" +
     *         "  \"FIV\": \"neg\",\n" +
     *         "  \"FeLV\": \"neg\",\n" +
     *         "}"
     *         );
     *         </pre>
     */
    @Override
    public String toString() {
            return
                    "{\n" +
                            "\"id\": \"" + this.id + "\",\n" +
                            "\"Kissalan ID\": \"" + this.kissalanId + "\",\n" +
                            "\"Rekisterinumero\": \"" + this.rekisterinumero + "\",\n" +
                            "\"Nimi\": \"" + this.nimi + "\",\n" +
                            "\"Kutsumanimi\": \"" + this.kutsumanimi + "\",\n" +
                            "\"Rotu\": \"" + this.rotu + "\",\n" +
                            "\"Veriryhmä\": \"" + this.veriryhmä + "\",\n" +
                            "\"Kotiosoite\": \"" + this.kotiosoite + "\",\n" +
                            "\"Postinumero\": \"" + this.postinumero + "\",\n" +
                            "\"Syntymäaika\": \"" + this.syntymäaika + "\",\n" +
                            "\"Isä\": \"" + this.isä + "\",\n" +
                            "\"Äiti\": \"" + this.äiti + "\",\n" +
                            "\"Sijoitettu\": \"" + this.sijoitettu + "\",\n" +
                            "\"Pennut\": \"" + this.pennut + "\",\n" +
                            "\"FIV\": \"" + this.FIV + "\",\n" +
                            "\"FeLV\": \"" + this.FeLV + "\"\n" +
                            "}";
        }

    /**
     * Asettaa kissalle tunnusnumron. Samalla tarkistetaan, seuraava numero on isompi, kuin annettu.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
        if (this.id >= seuraavaNro) seuraavaNro = this.id + 1;
    }


    /**
     * Asettaa kissalle nimen
     * @param s nimi
     * @return null
     */
    public String setNimi(String s) {
        this.nimi = s;
        return null;
    }


    /**
     * Luodaan tilapäinen klooni muokkausta varten.
     * @return klooni kissasta
     * @throws CloneNotSupportedException
     */
    @Override
    public Kissa clone() throws CloneNotSupportedException {
        Kissa uusi;
        uusi = (Kissa)super.clone();
        return uusi;
    }


    /**
     * Muuttaa kissan tietoja
     * @param k valittu kenttä jossa tietoja muutetaan
     * @param s käyttäjän antama syöte, joka tallennetaan muuttujaan
     * @return null
     */
    public String aseta(int k, String s) {
        switch (k) {
            case 0: this.id = Integer.parseInt(s);
                return null;
            case 1: this.kissalanId = Integer.parseInt(s);
                return null;
            case 2: this.rekisterinumero = Integer.parseInt(s);
                return null;
            case 3: this.nimi = s;
                return null;
            case 4: this.kutsumanimi = s;
                return null;
            case 5: this.rotu = s;
                return null;
            case 6: this.veriryhmä = s;
                return null;
            case 7: this.kotiosoite = s;
                return null;
            case 8: this.postinumero = Integer.parseInt(s);
                return null;
            case 9: this.syntymäaika = s;
                return null;
            case 10: this.isä = s;
                return null;
            case 11: this.äiti = s;
                return null;
            case 12: this.sijoitettu = s;
                return null;
            case 13: this.pennut = s;
                return null;
            case 14: this.FIV = s;
                return null;
            case 15: this.FeLV = s;
                return null;
            default: return "Muokattavaa kenttää ei löydy";
        }
    }


    /**
     * Antaa kentän tiedot
     * @param k kenttä, jonka tietoja haetaan
     * @return kentän tiedot
     */
    public String anna(int k) {
        switch (k) {
            case 0:
                return "" + this.id;
            case 1:
                return "" + this.getKissalanID();
            case 2:
                return "" + this.rekisterinumero;
            case 3:
                return "" + this.nimi;
            case 4:
                return "" + this.kutsumanimi;
            case 5:
                return "" + this.rotu;
            case 6:
                return "" + this.veriryhmä;
            case 7:
                return "" + this.kotiosoite;
            case 8:
                return "" + this.postinumero;
            case 9:
                return "" + this.syntymäaika;
            case 10:
                return "" + this.isä;
            case 11:
                return "" + this.äiti;
            case 12:
                return "" + this.sijoitettu;
            case 13:
                return "" + this.pennut;
            case 14:
                return "" + this.FIV;
            case 15:
                return "" + this.FeLV;
            default:
                return "Virhe tietojen antamisessa";
        }
    }


    /**
     * Antaa kenttien nimet kenttien luomista varten
     * @param k kenttä, jonka niemeä haetaan
     * @return kentän nimi
     */
    public String getKysymys(int k) {
        switch ( k ) {
            case 0: return "id";
            case 1: return "kissalan id";
            case 2: return "rekisterinumero";
            case 3: return "nimi";
            case 4: return "kutsumanimi";
            case 5: return "rotu";
            case 6: return "veriryhmä";
            case 7: return "kotiosoite";
            case 8: return "postinumero";
            case 9: return "syntymäaika";
            case 10: return "isä";
            case 11: return "äiti";
            case 12: return "sijoitettu";
            case 13: return "pennut";
            case 14: return "FIV";
            case 15: return "FeLV";
            default: return "Virhe";
        }
    }

    /**
     * Verrataan kahta kissaa niiden ID:n perusteella
     * @param kissa
     * @return true, jos ID:t ovat samat
     */
    public boolean equals(Kissa kissa) {
        if ( kissa == null ) return false;
        for (int k = 0; k < getKenttia(); k++)
            if ( !anna(k).equals(kissa.anna(k)) ) return false;
        return true;
    }


    /**
     * Verrataan ovatko kissat sama olio
     * @param kissa
     * @return true, jos ovat sama olio
     */
    @Override
    public boolean equals(Object kissa) {
        if ( kissa instanceof Kissa ) return equals((Kissa)kissa);
        return false;
    }

    /**
     * Täyttää kissan tiedot testeihin
     */
    public void taytaKissaTiedoilla() {
        this.rekisterinumero = 123456789;
        this.kissalanId = 1;
        this.nimi = "Kissa " + this.id;
        this.kutsumanimi = "Helmi";
        this.rotu = "Brittiläinen lyhytkarva";
        this.veriryhmä = "A";
        this.kotiosoite = "Kissakuja 7";
        this.postinumero = 90100;
        this.syntymäaika = "01.01.2020";
        this.isä = "Mese";
        this.äiti = "Siru";
        this.sijoitettu = "kyllä";
        this.pennut = "Ei";
        this.FIV = "neg";
        this.FeLV = "neg";
    }
}
