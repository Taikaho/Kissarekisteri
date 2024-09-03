package kissalaMain;

import fi.jyu.mit.ohj2.WildChars;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Toni Taikina-aho (tokaanta@jyu.fi)
 * @version 1.0, 07.08.24
 *
 * Lisää ja poistaa kissoja
 * Lukee ja kirjoittaa kissan tiedostoon
 * Osaa etsiä ja lajitella kissoja
 */
public class Kissat {
    private static final int MAX_JASENIA = 4;
    int lkm = 0;
    private Kissa[] kissat;
    private boolean muutettu = false;
    private Kissalarekisteri kissalarekisteri;

    /**
     * Rakentajassa luodaan neljän alkion kokoinen lista kissoista.
     */
    public Kissat (Kissalarekisteri kissalarekisteri) {
        this.kissat = new Kissa[MAX_JASENIA];
        this.kissalarekisteri = kissalarekisteri;
    }


    /** Lisätään kissa alkiot listaan. Ensin tarkistetaan, onko lista täynnä. Jos on, niin luodaan uusi neljä aikiota pidempi lista, johon
    kopioidaan vanhan listan tiedot. Uusi lista asetetaan lopuksi entisen alkiot listan osoitinmuuttujaan.
     */
    public void lisaa(Kissa kissa) {
        if (this.kissat.length >= lkm) {
            Kissa[] tpAlkiot = new Kissa[kissat.length + 4];

            for (int i = 0; i < this.kissat.length; i++) {
                tpAlkiot[i] = this.kissat[i];
            }
            this.kissat = tpAlkiot;
        }
        this.kissat[this.lkm] = kissa;
        this.lkm++;
        muutettu = true;
    }


    /**
     * Palauttaa listassa olevien kissojen lukumäärän
     * @return reksiterin kissojen lukumäärä
     */
    public int getLkm() {
        return this.lkm;
    }


    /**
     * Hakee kissat listasta halutun kissan
     * @param i syötetty indeksi
     * @return indeksin mukainen kissaolio
     * @throws IndexOutOfBoundsException
     */
    public Kissa anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return kissat[i];
    }

    /**
     * Hakee jokaisen kissat tiedot ja muuttaa ne merkkijonoksi. Tallentaa kaikki kissat json tiedostomuodossa.
     * @param tiedNimi
     * @throws FileNotFoundException
     */
    public void tallenna(String tiedNimi) throws FileNotFoundException {
        if (!muutettu) return;
        File tiedosto = new File(tiedNimi);

        try (PrintStream fileOutput = new PrintStream(new FileOutputStream(tiedosto, false))) {
            fileOutput.println("[");
            for (int i = 0; i < getLkm(); i++) {
                Kissa kissa = anna(i);

                if (kissa != null) {
                    fileOutput.println(kissa.toString());
                    if (i == getLkm() - 1) {
                        fileOutput.println("]");
                    } else {
                        fileOutput.println(",");
                    }
                }
            }
            muutettu = false;
        } catch(FileNotFoundException e){
            throw e;
        }
    }


    /**
     * Hakee kissalaan kuuluvat kissat
     * @param kissalaId kissalan tunnus
     * @return lista halutun kissalan kissoista
     */
    public ArrayList<Kissa> haeKissalanKissat (int kissalaId) {
        ArrayList<Kissa> loydetyt = new ArrayList<Kissa>();
        for (int i = 0; i < this.lkm; i++) {
            if (anna(i).getKissalanID() == kissalaId) {
                loydetyt.add(anna(i));
            }
        }
        return loydetyt;
    }


    /**
     * Lataa json kirjastoa käyttäen kissojen tiedot tiedostosta ja luo jokaisesta kissasta kissaolion tietojen perusteella.
     */
    public void lueTiedostosta(String tiedosto) {
        try {
            // Luodaan JSON-tokeneri ja JSON-taulukko tiedostosta
            JSONTokener tokener = new JSONTokener(new FileReader(tiedosto));
            JSONArray jsonArray = new JSONArray(tokener);

            // Käydään läpi JSON-taulukon jokainen alkio
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjekti = jsonArray.getJSONObject(i);

                // Haetaan tiedot JSON-objektista
                int id = jsonObjekti.getInt("id");
                int kissalanId = jsonObjekti.getInt("Kissalan ID");
                int rekisterinumero = jsonObjekti.getInt("Rekisterinumero");
                String nimi = jsonObjekti.getString("Nimi");
                String kutsumanimi = jsonObjekti.getString("Kutsumanimi");
                String rotu = jsonObjekti.getString("Rotu");
                String veriryhmä = jsonObjekti.getString("Veriryhmä");
                String kotiosoite = jsonObjekti.getString("Kotiosoite");
                int postinumero = jsonObjekti.getInt("Postinumero");
                String syntymäaika = jsonObjekti.getString("Syntymäaika");
                String isä = jsonObjekti.getString("Isä");
                String äiti = jsonObjekti.getString("Äiti");
                String sijoitettu = jsonObjekti.getString("Sijoitettu");
                String pennut = jsonObjekti.getString("Pennut");
                String fiv = jsonObjekti.getString("FIV");
                String felv = jsonObjekti.getString("FeLV");

                // Luodaan uusi kissa ja lisätään se rekisteriin
                Kissa kissa = new Kissa(id, kissalanId, rekisterinumero, nimi, kutsumanimi, rotu, veriryhmä, kotiosoite, postinumero, syntymäaika, isä, äiti, sijoitettu, pennut, fiv, felv);
                lisaa(kissa);
            }
        } catch (FileNotFoundException e) {
            // Tiedostoa ei löydy
            System.out.println("Tiedostoa ei löydy: " + tiedosto);
        } catch (JSONException e) {
            // JSON-tiedosto on virheellisessä muodossa
            System.out.println("Virheellinen JSON-tiedosto: " + tiedosto);
        } catch (Exception e) {
            // Jokin muu virhe
            System.out.println("Virhe tiedoston lukemisessa: " + tiedosto);
            e.printStackTrace();
        }
    }


    /**
     * Korvaa kissan tiedot listassa
     * @param kissa
     */
    public void korvaaTaiLisaa(Kissa kissa) {
        int id = kissa.getID();
        for (int i = 0; i < lkm; i++) {
            if (kissat[i].getID() == id) {
                kissat[i] = kissa;
                muutettu = true;
                return;
            }
        }
        lisaa(kissa);
    }


    /**
     * Etsitään hakuehtoja vastaava kissa hakupalkissa näytettäviä tietoja varten. Samalla rajataan näkyviin vain ne kissat, joilla on sama kissalaID kuin käytettävällä kissalalla.
     * @param hakuehto
     * @param k
     * @return
     */
    public Collection<Kissa> etsi(String hakuehto, int k) {
        //Kissalarekisteri kissalarekisteri = new Kissalarekisteri();

        String ehto = "*";
        if ( hakuehto != null && hakuehto.length() > 0 ) ehto = hakuehto;
        int hk = k;
        if ( hk < 0 ) hk = 1;
        Collection<Kissa> loytyneet = new ArrayList<Kissa>();
        for (Kissa kissa : this.kissat) {
            if (kissa != null && kissa.getKissalanID() == kissalarekisteri.getKaytettavaKissala().getID() && WildChars.onkoSamat(kissa.anna(hk), ehto)) {
                loytyneet.add(kissa);
            }
        }
        return loytyneet;
    }


    /**
     * Poistetaan kissa listasta, jos se on olemassa. Lopuksi kissat-listaan luodaan uusi lista, josta poistettu kissa on poistettu.
     * @param poistettavaKissa
     */
    public void poista(Kissa poistettavaKissa) {
        int poistettavaId = poistettavaKissa.getID();
        Kissa[] poistonJalkeen = new Kissa[kissat.length - 1];
        int uusiIndeksi = 0;

        for (Kissa kissa : kissat) {
            if (kissa != null && kissa.getID() != poistettavaId) {
                poistonJalkeen[uusiIndeksi] = kissa;
                uusiIndeksi ++;
            }
        }
        kissat = poistonJalkeen;
        lkm--;
    }
}