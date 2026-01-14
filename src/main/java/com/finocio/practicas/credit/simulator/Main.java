package com.finocio.practicas.credit.simulator;


import java.util.Scanner;
import com.finocio.practicas.credit.simulator.application.PrinterConfigurationService;
import com.finocio.practicas.credit.simulator.application.PurchaseCreditService;
import com.finocio.practicas.credit.simulator.ui.AppController;
import com.finocio.practicas.credit.simulator.ui.ConsoleIO;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleIO io = new ConsoleIO(scanner);

        PrinterConfigurationService printerService = new PrinterConfigurationService();
        PurchaseCreditService purchaseService = new PurchaseCreditService();

        AppController app = new AppController(io, printerService, purchaseService);
        app.run();

        scanner.close();
    }
}
