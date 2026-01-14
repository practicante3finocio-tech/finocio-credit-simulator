package com.finocio.practicas.credit.simulator.application;

import com.finocio.practicas.credit.simulator.infrastructure.*;

/**
 * Fábrica para crear impresoras concretas según opción del usuario.
 * Evita que el controlador conozca clases concretas.
 */

public class PrinterFactory {

    public static AbstractTicketPrinter create(int option) {
        return switch (option) {
            case 1 -> new TicketPrinter58mm();
            case 2 -> new TicketPrinter80mm();
            default -> throw new IllegalArgumentException("Tipo de impresora no válido.");
        };
    }
}