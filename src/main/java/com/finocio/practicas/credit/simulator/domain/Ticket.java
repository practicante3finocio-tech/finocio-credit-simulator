package com.finocio.practicas.credit.simulator.domain;

import java.time.LocalDateTime;

/**
 * Entidad que representa un ticket de compra de créditos.
 * Contiene información de transacción, cantidad, tarifa y estado.
 */

public class Ticket {

    private static final double FEE_PER_CREDIT = 0.50;

    private final String transactionId;
    private final LocalDateTime dateTime;
    private final int credit;
    private TicketStatus status;

    public Ticket(String transactionId,
                  LocalDateTime dateTime,
                  int credit,
                  TicketStatus status) {
        this.transactionId = transactionId;
        this.dateTime = dateTime;
        this.credit = credit;
        this.status = status;
    }

    public String getTransactionId() { return transactionId; }
    public LocalDateTime getDateTime() { return dateTime; }
    public int getCredit() { return credit; }
    public TicketStatus getStatus() { return status; }

    public double getFeePerCredit() {
        return FEE_PER_CREDIT;
    }


    /**
     * Calcula el total de la transacción.
     */

    public double getTotalAmount() {
        return credit * FEE_PER_CREDIT;
    }
}