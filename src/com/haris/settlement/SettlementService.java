package com.haris.settlement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public final class SettlementService {
    private final Map<String, SettlementEntry> ledger = new HashMap<>();

    public SettlementEntry settle(PaymentAttempt attempt) {
        if (ledger.containsKey(attempt.idempotencyKey())) {
            SettlementEntry existing = ledger.get(attempt.idempotencyKey());
            return new SettlementEntry(
                existing.idempotencyKey(),
                existing.merchantId(),
                existing.grossAmount(),
                existing.platformFee(),
                existing.netAmount(),
                true
            );
        }

        validate(attempt);
        BigDecimal fee = attempt.amount().multiply(attempt.feeRate()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal net = attempt.amount().subtract(fee).setScale(2, RoundingMode.HALF_UP);

        SettlementEntry entry = new SettlementEntry(
            attempt.idempotencyKey(),
            attempt.merchantId(),
            attempt.amount().setScale(2, RoundingMode.HALF_UP),
            fee,
            net,
            false
        );

        ledger.put(attempt.idempotencyKey(), entry);
        return entry;
    }

    private static void validate(PaymentAttempt attempt) {
        if (attempt.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("amount must be positive");
        }

        if (attempt.feeRate().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("feeRate cannot be negative");
        }
    }
}
