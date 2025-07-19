package com.financial.payment;

import com.financial.model.Payment;

public interface QRCodeSupport {
    String generateQRCode(Payment payment);
}
