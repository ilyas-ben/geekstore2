package com.ilouse.geekstoreV2.enumm;

public enum DeliveryStatus {
    AWAITING_COLLECTION(0),
    DELIVERING(1),
    DELIVERED(2);

    private final int index;

    DeliveryStatus(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static DeliveryStatus getStatusWithIndex(int index) {
        for (DeliveryStatus status : DeliveryStatus.values()) {
            if (status.getIndex() == index) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid index: " + index);
    }
}
