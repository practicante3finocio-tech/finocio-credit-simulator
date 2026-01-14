package com.finocio.practicas.credit.simulator.ui;

import java.util.Scanner;

public class ConsoleIO {

    private final Scanner scanner;


    /**
     * Encapsula lectura y escritura de consola.
     * Evita mezclar System.out y Scanner en el controlador.
     */

    public ConsoleIO(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Lee un entero desde consola con mensaje.
     * Reintenta hasta que se ingrese un número válido.
     */

    public int readInt(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.println("Error: Debes ingresar un número.");
            scanner.next();
            System.out.print(message);
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    /**
     * Imprime un mensaje en consola.
     */

    public void print(String message) {
        System.out.println(message);
    }
}