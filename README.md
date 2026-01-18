# Symulator Taśmy Produkcyjnej

> Projekt w **Spring Boot**, pokazujący praktyczne zastosowanie **wzorców projektowych ** w aplikacji backendowej.

Aplikacja symuluje tablicę do zarządzania zadaniami w procesie produkcyjnym i działa w czasie rzeczywistym (WebSocket).

---

Celem projektu jest:

* zaprezentowanie znajomości **wzorców projektowych** w praktyce
* demonstracja pracy z **Spring Boot 3**, **WebSocketami** i **JPA**

---

##  Funkcjonalności

* Dodawanie, edycja i zarządzanie zadaniami
* Zmiana statusów i priorytetów
* Aktualizacje w czasie rzeczywistym (WebSocket / STOMP)
* Cofanie ostatniej operacji (**Undo**)
* Walidacja danych wejściowych (Chain of Responsibility)
* Dynamiczna zmiana strategii sortowania (FIFO / Priorytet)

---

##  Wzorce projektowe

| Wzorzec                     | Zastosowanie                                                                         |
| --------------------------- | ------------------------------------------------------------------------------------ |
| **Facade**                  | Uproszczenie logiki kontrolerów poprzez centralny punkt dostępu do logiki biznesowej |
| **Chain of Responsibility** | Wieloetapowa walidacja danych wejściowych                 |
| **Strategy**                | Dynamiczna zmiana algorytmu sortowania zadań                                         |
| **Memento**                 | Implementacja mechanizmu cofania zmian (Undo)                                        |
| **Observer**                | Powiadamianie klientów o zmianach przez WebSocket                                    |

---

##  Architektura

* **Controller** – obsługa żądań HTTP / WebSocket
* **Facade** – koordynacja operacji biznesowych
* **Service ** – logika aplikacyjna
* **Repository ** – warstwa dostępu do danych
* **WebSocket Layer** – broadcast zmian do klientów

---

##  Technologie

* Java 17
* Spring Boot 3
* Spring Data JPA
* H2 Database
* WebSocket
* HTML, CSS, JavaScript]

---

##  Uruchomienie lokalne

```bash
# klonowanie repozytorium
git clone <repo-url>

# uruchomienie aplikacji
./mvnw spring-boot:run
```

Następnie otwórz w przeglądarce:

```
http://localhost:8080
```
