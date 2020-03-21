/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author suonpaas
 */
public class KassapaateTest {
    Kassapaate kassa;
    Maksukortti kortti;
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void tarkistaUusiKassa() {
        assertEquals(100000, kassa.kassassaRahaa());
        int myydyt = kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty();
        assertEquals(0, myydyt);
    }
    
    @Test
    public void myyMaukasKateinenRahatRiittavat() {
        assertEquals(100, kassa.syoMaukkaasti(500));
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void myyMaukasKateinenRahatEiRiita() {
        assertEquals(300, kassa.syoMaukkaasti(300));
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void myyEdullinenKateinenRahatRiittavat() {
        assertEquals(110, kassa.syoEdullisesti(350));
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void myyEdullinenKateinenRahatEiRiita() {
        assertEquals(150, kassa.syoEdullisesti(150));
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void myyMaukasKorttiRahatRiittavat() {
        assertEquals(true, kassa.syoMaukkaasti(kortti));
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void myyMaukasKorttiRahatEiRiita() {
        kortti = new Maksukortti(2);
        assertEquals(false, kassa.syoMaukkaasti(kortti));
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());        
    }
    
    @Test
    public void myyEdullinenKorttiRahatRiittavat() {
        assertEquals(true, kassa.syoEdullisesti(kortti));
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void myyEdullinenKorttiRahatEiRiita() {
        kortti = new Maksukortti(2);
        assertEquals(false, kassa.syoEdullisesti(kortti));
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void lataaSaldoaKortille() {
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals(2000, kortti.saldo());
        assertEquals(101000, kassa.kassassaRahaa());
    }
    
    @Test
    public void lataaSaldoaKortilleNegatiivinen() {
        kassa.lataaRahaaKortille(kortti, -5);
        assertEquals(1000, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
