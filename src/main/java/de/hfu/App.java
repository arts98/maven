package de.hfu;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        System.out.println("Gib deinen Text ein");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        System.out.println(text.toUpperCase());
    }
}
