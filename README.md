# Java Payment Settlement API

A Java payment settlement domain for platform fee calculation, net settlement generation, and duplicate settlement prevention.

## Stack

Java, settlement idempotency, ledger calculations

## Problem

Payment APIs must keep ledger entries consistent under retries and callback duplication. This project models idempotent settlement behavior.

## Architecture

- PaymentAttempt captures the settlement input.
- SettlementService calculates fee and net amounts.
- SettlementServiceTest verifies rounding and duplicate handling.

## Implemented Production Readiness

- CI compiles and executes the Java test main.
- Duplicate idempotency keys return existing settlement state.
- Money calculations round using HALF_UP at cents precision.

## Run And Test

```powershell
javac src\com\haris\settlement\*.java
java -cp src com.haris.settlement.SettlementServiceTest
```

## Quality Gates

- Project-specific GitHub Actions workflow included under .github/workflows/ci.yml.
- Generated build outputs and dependency folders are excluded through .gitignore.
- Tests and validation commands are intentionally small enough to run during code review.

## Production Extension Points

- Add Spring Boot REST endpoints.
- Back the ledger with PostgreSQL.
- Add Resilience4j policies around gateway calls.

## Repository Hygiene

This repository contains original portfolio code only. It does not include employer source code, private resumes, generated binaries, local credentials, or large media files.

