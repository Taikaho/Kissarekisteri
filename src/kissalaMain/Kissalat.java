package kissalaMain;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Toni Taikina-aho (tokaanta@jyu.fi)
 * @version 1.0, 07.08.24
 *
 * Lisää ja poistaa kissaloita.
 * Lukee ja kirjoittaa kissalan tiedostosta.
 * Osaa etsiä ja lajitella.
 */
public class Kissalat {

    private final ArrayList<Kissala> kissalat = new ArrayList<>();
    private boolean muutettu = false;

    /**
     * Lisätään kissala kissalat listaan
     */
    public void lisaa(Kissala kissala) {
        kissalat.add(kissala);
    }


    /**
     * Etsii kissalan, johon kissa kuuluu
     * @param kissanId kissan tunnus
     * @return Palauttaa kissalan, johon kissa kuuluu.
     */
   public Kissala annaKissala(int kissanId) {
       for (Kissala kissala : kissalat)
               if (kissanId == kissala.getID()) {
                   return kissala;
               }
       return null;
   }

    /**
     * Tallentaa kissalat tiedostoon json-muodossa
     * @param tiedNimi tiedoston nimi
     * @throws FileNotFoundException jos tiedostoa ei löydy
     */
    public void tallenna (String tiedNimi) throws FileNotFoundException {
        File tiedosto = new File(tiedNimi);

        try (PrintStream fileOutput = new PrintStream((new FileOutputStream(tiedosto, false)))) {
            fileOutput.println("[");
            for (int i = 0; i < kissalat.size(); i++) {
                Kissala kissala = kissalat.get(i);
                fileOutput.println(kissala.toString());
                if (i == kissalat.size() - 1) {
                    fileOutput.println("]");
                }
                else {
                    fileOutput.println(",");
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    }


    /**
     * Palauttaa kaikki kissalat
     * @return palauttaa kaikki kissalat
     */
    public ArrayList<Kissala> annaKissalat() {
       return kissalat;
    }


    /**
     * Lukee tiedostosta kissalat ja lisää ne kissalat listaan
     * @param tiedosto tiedoston nimi
     */
    public void lueTiedostosta(String tiedosto) {
        File tiedostoOlio = new File(tiedosto);
        if (!tiedostoOlio.exists()) {
            System.out.println("Tiedostoa ei löydy. Jatketaan ilman tiedoston latausta.");
            return;
        }
        try {
            JSONTokener tokener = new JSONTokener(new FileReader(tiedostoOlio));
            JSONArray jsonArray = new JSONArray(tokener);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjekti = jsonArray.getJSONObject(i);
                int id = jsonObjekti.getInt("id");
                String kissalannimi = jsonObjekti.getString("Kissalan nimi");
                String kasvattajanEtunimi = jsonObjekti.getString("Kasvattajan etunimi");
                String kasvattajanSukunimi = jsonObjekti.getString("Kasvattajan sukunimi");
                String katuosoite = jsonObjekti.getString("Katuosoite");
                int postinumero = jsonObjekti.getInt("Postinumero");
                String paikkakunta = jsonObjekti.getString("Paikkakunta");
                Kissala kissala = new Kissala(id, kissalannimi, kasvattajanEtunimi, kasvattajanSukunimi, katuosoite, postinumero, paikkakunta);
                lisaa(kissala);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Tiedoston lukeminen ei onnistunut.");
        }
    }


    /**
     * Poistaa kissalan listasta
     * @param kissala poistettava kissala
     */
    public void poista(Kissala kissala) {
        kissalat.remove(kissala);
    }


    /**
     * Korvaa kissalan tiedot listassa
     * @param kissala
     */
    public void korvaaTaiLisaa(Kissala kissala) {
        int id = kissala.getID();
        for (int i = 0; i < kissalat.size(); i++) {
            if (kissalat.get(i).getID() == id) {
                kissalat.set(i, kissala);
                muutettu = true;
                return;
            }
        }
        lisaa(kissala);
    }
}
