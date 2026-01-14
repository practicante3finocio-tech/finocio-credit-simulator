package com.finocio.practicas.credit.simulator.application;

import com.finocio.practicas.credit.simulator.domain.Ticket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Servicio de aplicación para la creación de tickets de compra de créditos.
 * Contiene reglas de negocio (cantidad mínima, máxima y tarifa).
 */

public class PurchaseCreditService {
    private static final AtomicInteger COUNTER = new AtomicInteger(0);
    private static final int MAX_CREDITS = 1000;

    public Ticket createTicket(int credits) {
        if (credits <= 0) {
            throw new InvalidCreditAmountException("La cantidad debe ser positiva.");
        }
        if (credits > MAX_CREDITS) {
            throw new CreditLimitExceededException("Máximo " + MAX_CREDITS + " créditos.");
        }

        LocalDateTime now = LocalDateTime.now();
        String txId = generateTransactionId(now);

        return new Ticket(txId, now, credits, TicketStatus.APPROVED);
    }

    /**
     * Genera un ID único de transacción basado en fecha y contador atómico.
     */

    private String generateTransactionId(LocalDateTime dateTime) {
        String date = dateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return String.format("TX-%s-%06d", date, COUNTER.incrementAndGet());
    }
}