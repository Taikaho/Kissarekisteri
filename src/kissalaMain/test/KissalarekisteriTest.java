package kissalaMain.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;
import kissalaMain.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2024.08.10 12:12:48 // Generated by ComTest
 *
 */
@SuppressWarnings({ "PMD" })
public class KissalarekisteriTest {


  // Generated by ComTest BEGIN  // Kissalarekisteri: 15
   private Kissalarekisteri testirekisteri; 
   private Kissala kissala1; 
   private Kissala kissala2; 
   private Kissa kissa1; 
   private Kissa kissa2; 
   private Kissa kissa3; 
   private Kissa kissa4; 
   private Kissa kissa5; 

 // @SuppressWarnings("javadoc")
   public void alustaRekisteri() {
     testirekisteri = new Kissalarekisteri(); 
     Kissala testiKissala = new Kissala(); testiKissala.taytaKissalaTiedoilla(); 
     testirekisteri.lisaaKissala(testiKissala); 
     kissa1 = new Kissa(); kissa1.taytaKissaTiedoilla(); kissa1.rekisteroi(); 
     kissa2 = new Kissa(); kissa2.taytaKissaTiedoilla(); kissa2.rekisteroi(); 
     kissa3 = new Kissa(); kissa3.taytaKissaTiedoilla(); kissa3.rekisteroi(); 
     kissa4 = new Kissa(); kissa4.taytaKissaTiedoilla(); kissa4.rekisteroi(); 
     kissa5 = new Kissa(); kissa5.taytaKissaTiedoilla(); kissa5.rekisteroi(); 
     testirekisteri.lisaa(kissa1); 
     testirekisteri.lisaa(kissa2); 
     testirekisteri.lisaa(kissa3); 
     testirekisteri.lisaa(kissa4); 
     testirekisteri.lisaa(kissa5); 
   }
  // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLisaaKissala */
  @Test
  public void testLisaaKissala() {    // Kissalarekisteri: 65
    alustaRekisteri(); 
    Kissala kissala3 = new Kissala(); kissala3.taytaKissalaTiedoilla(); kissala3.rekisteroi(); 
    assertEquals("From: Kissalarekisteri line: 68", 1, testirekisteri.annaKissalat().size()); 
    testirekisteri.lisaaKissala(kissala3); kissala3.rekisteroi(); 
    assertEquals("From: Kissalarekisteri line: 70", 2, testirekisteri.annaKissalat().size()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLisaa */
  @Test
  public void testLisaa() {    // Kissalarekisteri: 83
    alustaRekisteri(); 
    Kissa kissa6 = new Kissa(); kissa6.taytaKissaTiedoilla(); kissa6.rekisteroi(); 
    assertEquals("From: Kissalarekisteri line: 86", 5, testirekisteri.getKissoja()); 
    testirekisteri.lisaa(kissa6); kissa6.rekisteroi(); 
    assertEquals("From: Kissalarekisteri line: 88", 6, testirekisteri.getKissoja()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueJaTallennaTiedosto 
   * @throws Exception when error
   */
  @Test
  public void testLueJaTallennaTiedosto() throws Exception {    // Kissalarekisteri: 112
    alustaRekisteri(); 
    testirekisteri.tallenna("testi"); 
    testirekisteri.poista(kissa1); 
    testirekisteri.poista(kissa2); 
    testirekisteri.poista(kissa3); 
    testirekisteri.poista(kissa4); 
    testirekisteri.poista(kissa5); 
    assertEquals("From: Kissalarekisteri line: 121", 0, testirekisteri.getKissoja()); 
    testirekisteri.lueTiedostosta("testi"); 
    assertEquals("From: Kissalarekisteri line: 123", 5, testirekisteri.getKissoja()); 
  } // Generated by ComTest END
}