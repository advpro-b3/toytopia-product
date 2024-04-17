package id.ac.ui.cs.advprog.toytopiaproduct.enums;

import lombok.Getter;

@Getter
public enum Availability {
    PREORDER("PREORDER"),
    READY("READY");

    private final String value;

    Availability(String value) {
        this.value = value;
    }
}
