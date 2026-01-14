# Finocio Credit Simulator

Java project to simulate the purchase of credits, with ASCII ticket printing for 58mm and 80mm printers, using Gradle and a menu-driven interface.

## Project Structure

- `com.finocio.practicas.simulador.creditos.application` → Business services (PurchaseCreditService, PrinterConfigurationService)
- `com.finocio.practicas.simulador.creditos.domain` → Domain entities and enums (Ticket, TicketStatus, PrinterStatus)
- `com.finocio.practicas.simulador.creditos.infrastructure` → Printer implementations (TicketPrinter58mm, TicketPrinter80mm)
- `com.finocio.practicas.simulador.creditos.ui` → User interface and main controller (Main, AppController)

## Features

- Purchase credits with validation
- Print tickets with different printer formats
- Configure printers dynamically
- ASCII-based ticket templates for 58mm and 80mm printers

## Getting Started

1. Clone the repository:
   ```bash
   git clone <https://github.com/practicante3finocio-tech/finocio-credit-simulator.git>