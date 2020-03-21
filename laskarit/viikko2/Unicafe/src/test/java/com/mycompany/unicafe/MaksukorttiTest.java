package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void tarkistaAlkuSaldo() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoa() {
        kortti.lataaRahaa(20);
        assertEquals(30, kortti.saldo());
    }
    
    @Test
    public void saldoVaheneeOstossa() {
        kortti.otaRahaa(5);
        assertEquals(5, kortti.saldo());
    }
    
    @Test
    public void saldoEiMeneNegatiiviseksi() {
        kortti.otaRahaa(11);
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void otaRahaaPalauttaaOikein() {
        assertEquals(true, kortti.otaRahaa(5));
        assertEquals(false, kortti.otaRahaa(6));
    }
    
}
