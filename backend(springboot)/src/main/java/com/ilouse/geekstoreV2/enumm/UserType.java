package com.ilouse.geekstoreV2.enumm;

public enum UserType {
    NULL(0),
    CLIENT(1),
    SELLER(2), // furnisher
    ADMIN(3);

    int userTypeNumber;

    UserType(int userTypeNumber) {
        this.userTypeNumber = userTypeNumber;
    }
}
