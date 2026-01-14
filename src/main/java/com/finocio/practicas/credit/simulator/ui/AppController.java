package com.finocio.practicas.credit.simulator.ui;

import com.finocio.practicas.credit.simulator.PrinterConfigurationService;
import com.finocio.practicas.credit.simulator.application.PrinterFactory;
import com.finocio.practicas.credit.simulator.application.PurchaseCreditService;
import com.finocio.practicas.credit.simulator.domain.*;
import com.finocio.practicas.credit.simulator.infrastructure.AbstractTicketPrinter;

/**
 * Controlador principal de la aplicación de consola.
 * Orquesta el flujo del menú y delega la lógica a los servicios de aplicación.
 * No contiene lógica de negocio.
 */

public class AppController {

    private final ConsoleIO io;
    private final PrinterConfigurationService printerService;
    private final PurchaseCreditService purchaseService;

    public AppController(ConsoleIO io,
                         PrinterConfigurationService printerService,
                         PurchaseCreditService purchaseService) {
        this.io = io;
        this.printerService = printerService;
        this.purchaseService = purchaseService;
    }

    public void run() {
        int option;
        do {
            showMenu();
            option = io.readInt("Elige una opción: ");
            handleOption(option);
            io.print("");
        } while (option != 4);
    }

    // ================= MENU =================

    private void showMenu() {
        io.print("===== Opciones =====");
        io.print("1. Compra de créditos");
        io.print("2. Configuración de impresora");
        io.print("3. Estado de la impresora");
        io.print("4. Salir");
    }

    private void handleOption(int option) {
        switch (option) {
            case 1 -> buyCredits();
            case 2 -> configurePrinter();
            case 3 -> showPrinterStatus();
            case 4 -> io.print("Saliendo de la aplicación...");
            default -> io.print("Opción no válida.");
        }
    }

    // ================= FLUJOS =================

    /**
     * Flujo para comprar créditos.
     * Valida que la impresora esté lista antes de crear e imprimir el ticket.
     */

    private void buyCredits() {
        if (!printerService.isPrinterReady()) {
            showPrinterNotReadyMessage();
            return;
        }

        int credits = io.readInt("Ingrese la cantidad de créditos: ");

        try {
            Ticket ticket = purchaseService.createTicket(credits);
            printerService.printTicket(ticket);
        } catch (InvalidCreditAmountException | CreditLimitExceededException e) {
            io.print("Error: " + e.getMessage());
        } catch (IllegalStateException e) {
            io.print("No se pudo imprimir el ticket: " + e.getMessage());
        }
    }

    /**
     * Configura la impresora activa usando PrinterFactory.
     * La misma instancia sirve para imprimir y consultar el estado.
     */

    private void configurePrinter() {
        int option = io.readInt("Seleccione impresora (1=58mm, 2=80mm): ");

        try {
            AbstractTicketPrinter printer = PrinterFactory.create(option);
            printerService.setPrinter(printer, printer);
            io.print("Impresora configurada correctamente.");
        } catch (IllegalArgumentException e) {
            io.print("Error: " + e.getMessage());
        }
    }

    /**
     * Muestra el estado actual de la impresora configurada.
     */

    private void showPrinterStatus() {
        if (printerService.getCurrentPrinter() == null) {
            io.print("No hay impresora configurada.");
            return;
        }

        io.print("Impresora: " +
                printerService.getCurrentPrinter().getClass().getSimpleName());
        io.print("Estado: " + printerService.getPrinterStatus());
    }

    // ================= HELPERS =================

    /**
     * Mensaje de error cuando la impresora no está lista.
     */

    private void showPrinterNotReadyMessage() {
        io.print("No se puede realizar la compra.");
        io.print("Estado actual de la impresora: "
                + printerService.getPrinterStatus());
        io.print("Configure otra impresora o solucione el problema.");
    }
}