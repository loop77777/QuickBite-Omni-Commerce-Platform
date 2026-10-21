# QuickBite Omni-Commerce Platform

QuickBite is a small, console-based Java demonstration of an omni-commerce order workflow. It uses in-memory DAOs and simulated payment/notification integrations to demonstrate layered application structure and common object-oriented design patterns. It does **not** connect to a real database or charge real payment methods.

## Requirements

- JDK 8 or later (the project uses Java 8 language and library features).
- No third-party dependencies or build tool are required.

## Project structure

All Java sources are under `src/com/quickbite/`:

| Package / file | Responsibility |
| --- | --- |
| `Main.java`, `main2.java` | Two runnable console demonstrations; `main2` also exercises extra methods and exception paths. |
| `builder/` | Fluent `CustomerBuilder`. |
| `collection/` | `MaxHeap`, used to find the top-selling product in reports. |
| `controller/` | Order checkout and analytics presentation. |
| `dao/`, `dao/impl/` | Generic DAO contract and in-memory customer, product, and order stores. |
| `decorator/` | Customer loyalty decorator example. |
| `entity/` | Domain types such as `Customer`, `Product`, `Order`, and `Address`. |
| `exception/` | Domain-specific runtime exceptions. |
| `facade/` | `OrderFacade`, coordinating customer, catalog, cart, payment, and order services. |
| `factory/`, `strategy/` | Payment creation and payment implementations/adapters. |
| `observer/` | Email and SMS order notification observers. |
| `service/` | Cart, customer, inventory, order, product, and reporting logic. |
| `singleton/` | Demonstration database-connection singleton (logs a query; it is not a real JDBC connection). |

## Run from the command line

Run these commands in PowerShell from the project root. The commands compile all source files into `out` and then start each entry point separately:

```powershell
$sourceFiles = Get-ChildItem -Path .\src -Recurse -Filter *.java | ForEach-Object { $_.FullName }
New-Item -ItemType Directory -Force .\out | Out-Null
javac -d .\out $sourceFiles
java -cp .\out com.quickbite.Main
```

To run the extended demonstration instead:

```powershell
java -cp .\out com.quickbite.main2
```

The source file is named `main2.java`, so its Java entry-point class is case-sensitive and is `com.quickbite.main2` (lowercase `m`). In IntelliJ IDEA, open the project and run `Main.main()` or `main2.main()` from the gutter beside the method.

## Demonstration walkthrough

Both entry points create in-memory DAO instances and services, register email/SMS observers, and use the facade to run two checkouts:

1. Register four products (headphones, biryani, olive oil, and a jacket) in four categories and two customers.
2. Place order `5001` using the UPI payment strategy: one headphone and two biryanis, totaling **₹12,700**.
3. Place order `5002` using the simulated PayPal adapter: four biryanis and two olive oils, totaling **₹3,100**.
4. Deduct stock, save the completed orders, notify the observers, and print analytics. The expected total revenue is **₹15,800**; the top-selling product is **Hyderabadi Chicken Biryani** (six units sold).
5. `main2` additionally demonstrates product search/lookups, cart removal, DAO operations, card and wallet payment strategies, and caught product, stock, order, and payment exceptions.

Payment implementations, the PayPal SDK, notifications, and `DatabaseConnection.executeQuery` are demonstrations that print to the console; they do not contact external services. The DAOs are in-memory, so data resets each time an entry point runs. Each entry point should be run in its own process to see the full initial stock values.

## Design concepts demonstrated

- **Builder:** constructs customer objects with a fluent API.
- **Facade:** `OrderFacade` coordinates validation, payment, inventory/order creation, and cart clearing for checkout.
- **Factory and Strategy:** selects among UPI, card, wallet, and simulated PayPal payment implementations.
- **Adapter:** wraps the PayPal SDK-shaped interface as the platform's `Payment` type.
- **Observer:** dispatches email and SMS notifications when an order is created.
- **Singleton:** exposes a single demonstration database-connection object.
- **DAO and Service layers:** separate in-memory persistence from business logic.
- **Java collections and streams:** store domain data and generate customer/product/order analytics.
