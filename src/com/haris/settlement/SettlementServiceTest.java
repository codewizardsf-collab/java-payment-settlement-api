package com.haris.settlement;

import java.math.BigDecimal;

public final class SettlementServiceTest {
    public static void main(String[] args) {
        SettlementService service = new SettlementService();
        PaymentAttempt attempt = new PaymentAttempt(
            "pay-1",
            "merchant-42",
            new BigDecimal("100.00"),
            new BigDecimal("0.029")
        );

        SettlementEntry first = service.settle(attempt);
        SettlementEntry duplicate = service.settle(attempt);

        assertEquals(new BigDecimal("2.90"), first.platformFee(), "platform fee should round to cents");
        assertEquals(new BigDecimal("97.10"), first.netAmount(), "net amount should subtract platform fee");
        assertTrue(duplicate.duplicate(), "duplicate settlement should not create a new entry");

        System.out.println("PASS settlement service");
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException(message);
        }
    }

    private static void assertEquals(Object expected, Object actual, String message) {
        if (!expected.equals(actual)) {
            throw new IllegalStateException(message + ". Expected " + expected + ", got " + actual);
        }
    }
}
