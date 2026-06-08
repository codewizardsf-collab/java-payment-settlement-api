# Java Payment Settlement API

Java project for payment settlement idempotency, fee calculation, and ledger entry generation.

## Resume Fit

- Spring Boot payment settlement concepts.
- Redis-style idempotency layer.
- Financial ledger correctness.

## Run

```powershell
javac src\com\haris\settlement\*.java
java -cp src com.haris.settlement.SettlementServiceTest
```

## Production Next Steps

- Add Spring REST API.
- Add PostgreSQL ledger store.
- Add Resilience4j policies for gateway calls.
