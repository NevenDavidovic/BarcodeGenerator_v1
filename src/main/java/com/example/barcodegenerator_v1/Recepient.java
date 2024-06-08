package com.example.barcodegenerator_v1;

public class Recepient {
    private int id;
    private String name;
    private String address;
    private String city;
    private String description;
    private String email;
    private String model;
    private String pozivNaBroj;
    private String amount;

    public Recepient(int id, String name, String address, String city, String description, String email, String model, String pozivNaBroj, String amount) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.description = description;
        this.email = email;
        this.model = model;
        this.pozivNaBroj = pozivNaBroj;
        this.amount = amount;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getPozivNaBroj() { return pozivNaBroj; }
    public void setPozivNaBroj(String pozivNaBroj) { this.pozivNaBroj = pozivNaBroj; }
    public String getAmount() { return amount; }
    public void setAmount(String amount) { this.amount = amount; }
}
