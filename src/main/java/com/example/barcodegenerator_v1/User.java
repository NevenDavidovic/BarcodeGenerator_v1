package com.example.barcodegenerator_v1;

public class User {
    private String name;
    private String IBAN;
    private String email;
    private String street;
    private String zip;
    private String caller;

    public User(String name, String IBAN, String email, String street, String zip, String caller) {
        this.name = name;
        this.IBAN = IBAN;
        this.email = email;
        this.street = street;
        this.zip = zip;
        this.caller = caller;
    }

    public String getName() {
        return name;
    }

    public String getIBAN() {
        return IBAN;
    }

    public String getEmail() {
        return email;
    }

    public String getStreet() {
        return street;
    }

    public String getZip() {
        return zip;
    }

    public String getCaller() {
        return caller;
    }
}
