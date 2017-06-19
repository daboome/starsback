package org.daboo.stars.domain.enums;

public enum AuthorityName {
    ROLE_USER("ROLE_USER"), ROLE_ADMIN("ROLE_ADMIN"), ROLE_ADVER("ROLE_ADVER"), ROLE_CUSTOMER("ROLE_CUSTOMER");
    private String value;

    AuthorityName(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

    public boolean equalsName(String compare) {
        return this.value.equalsIgnoreCase(compare);
    }
}