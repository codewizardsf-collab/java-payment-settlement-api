package com.haris.settlement;

import java.math.BigDecimal;

public record SettlementEntry(
    String idempotencyKey,
    String merchantId,
    BigDecimal grossAmount,
    BigDecimal platformFee,
    BigDecimal netAmount,
    boolean duplicate
) {
}
