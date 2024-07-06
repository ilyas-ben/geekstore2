package com.ilouse.geekstoreV2.enumm;

public enum PaymentMethod {
    NULL(0),
    ON_DELIVERY(1),
    BANK_CARD(2);

    int index;

    PaymentMethod(int index) {
        this.index = index;
    }

    public static PaymentMethod getPaymentMethodByItsIndex(int index) {
        for (PaymentMethod method : PaymentMethod.values()) {
            if (method.index == index) {
                return method;
            }
        }
        return null; // Retourne NULL si aucune correspondance trouvée
    }

    
}
