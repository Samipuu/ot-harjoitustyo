/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpaddiction.ui;

/**
 * Pääluokka, josta sovellus käynnistetään.
 * @author suonpaas
 */
public class Main {
    public static void main(String[] args) {
        System.setProperty("quantum.multithreaded", "false");
        UI.main(args);
    }
}
