package com.finocio.practicas.credit.simulator.infrastructure;

import java.time.format.DateTimeFormatter;
import java.util.function.Function;

import com.finocio.practicas.credit.simulator.domain.*;

/**
 * Clase base para impresoras de tickets.
 * Contiene la lógica común de impresión y formateo.
 * Implementa Printer y PrinterStatusProvider.
 */

public abstract class AbstractTicketPrinter
        implements Printer, PrinterStatusProvider {

    protected PrinterStatus status;

    protected abstract int getWidth();

    public AbstractTicketPrinter() {
        // Inicializa el estado de la impresora aleatoriamente
        double rand = Math.random();
        if (rand < 0.8) status = PrinterStatus.READY;
        else if (rand < 0.95) status = PrinterStatus.PAPER_LOW;
        else status = PrinterStatus.OFFLINE;
    }

    @Override
    public PrinterStatus getStatus() {
        return status;
    }

    /**
     * Formatea y imprime un ticket.
     * Verifica que la impresora esté lista antes de imprimir.
     */

    @Override
    public void printTicket(Ticket ticket) {
        if (status != PrinterStatus.READY) {
            throw new IllegalStateException("Printer not ready");
        }

        int width = getWidth();
        String border = "+" + "-".repeat(width - 2) + "+";
        Function<String, String> center = text -> {
            int pad = (width - 2 - text.length()) / 2;
            return "|" + " ".repeat(pad)
                    + text
                    + " ".repeat(width - 2 - pad - text.length()) + "|";
        };

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println(border);
        System.out.println(center.apply("FINOCIO ATM"));
        System.out.println(center.apply("COMPRA CREDITOS"));
        System.out.println(border);
        System.out.println("| TX: " + ticket.getTransactionId());
        System.out.println("| Fecha: " + ticket.getDateTime().format(formatter));
        System.out.println(border);
        System.out.println("| Créditos: " + ticket.getCredit());
        System.out.println("| Tarifa: €" + ticket.getFeePerCredit());
        System.out.println("| Importe: €" + ticket.getTotalAmount());
        System.out.println(border);
        System.out.println("| Estado: " + ticket.getStatus());
        System.out.println(border);
    }
}
