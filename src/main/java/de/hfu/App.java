package de.hfu;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
    	/** Ausgabe, was der Nutzer eingeben soll. */
        System.out.println("Gib deinen Text ein");
        /** scanner für die Eingabe eingefügt*/
        Scanner scanner = new Scanner(System.in);
        /** Nutzereingabe wird eingelesen */
        String text = scanner.nextLine();
        /** Nutzereingabe wird in Großbuchstaben ausgegeben */
        System.out.println(text.toUpperCase());
    }
}
