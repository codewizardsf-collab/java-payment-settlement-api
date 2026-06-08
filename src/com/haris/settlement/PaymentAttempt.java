package com.haris.settlement;

import java.math.BigDecimal;

public record PaymentAttempt(
    String idempotencyKey,
    String merchantId,
    BigDecimal amount,
    BigDecimal feeRate
) {
}
