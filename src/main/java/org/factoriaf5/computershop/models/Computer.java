package org.factoriaf5.computershop.models;

import jakarta.persistence.*;

@Entity
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private int memory;
    @Column(nullable = false)
    private String process;
    @Column(nullable = false)
    private String operatingSystem;
    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "id_shop")
    private Shop shop;

    public Computer() {
    }

    
    public Computer(String brand, int memory, String process, String operatingSystem, double price,
            Shop shop) {
        this.brand = brand;
        this.memory = memory;
        this.process = process;
        this.operatingSystem = operatingSystem;
        this.price = price;
        this.shop = shop;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

}
