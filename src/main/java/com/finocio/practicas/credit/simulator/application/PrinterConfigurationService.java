package com.finocio.practicas.credit.simulator.application;

import com.finocio.practicas.credit.simulator.domain.*;

/**
 * Servicio de aplicación que gestiona la impresora activa.
 * No conoce la implementación concreta.
 */

public class PrinterConfigurationService {

    private Printer printer;
    private PrinterStatusProvider statusProvider;

    /**
     * Configura la impresora activa y su proveedor de estado.
     */

    public void setPrinter(Printer printer, PrinterStatusProvider statusProvider) {
        this.printer = printer;
        this.statusProvider = statusProvider;
    }

    public Printer getCurrentPrinter() {
        return printer;
    }

    public PrinterStatus getPrinterStatus() {
        return statusProvider == null
                ? PrinterStatus.OFFLINE
                : statusProvider.getStatus();
    }

    public boolean isPrinterReady() {
        return getPrinterStatus() == PrinterStatus.READY;
    }

    /**
     * Imprime un ticket usando la impresora activa.
     * Lanza excepción si la impresora no está lista.
     */

    public void printTicket(Ticket ticket) {
        if (!isPrinterReady()) {
            throw new IllegalStateException("Impresora no lista para imprimir.");
        }
        printer.printTicket(ticket);
    }
}
