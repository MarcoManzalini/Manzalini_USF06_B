package org.example;

public class Hotel {
    private String description, name;
    private int id;
    private double price;
    private boolean suite;


    public Hotel(String description, String name, int id, double price, boolean suite) {
        setDescription(description);
        setName(name);
        setId(id);
        setPrice(price);
        setSuite(suite);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.equals("") ? "Generic Room" : description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.equals("") ? "Generic Hotel" : name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id <= 0 ? 1 : id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price <= 0.0 ? 1.0 : price;
    }

    public boolean isSuite() {
        return suite;
    }

    public void setSuite(boolean suite) {
        this.suite = suite;
    }
}
