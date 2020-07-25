package com.example.sampleapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Laptop implements Parcelable {

    public static final Creator<Laptop> CREATOR = new Creator<Laptop>() {
        @Override
        public Laptop createFromParcel(Parcel in) {
            return new Laptop(in);
        }

        @Override
        public Laptop[] newArray(int size) {
            return new Laptop[size];
        }
    };

    private String manufacturer;
    private String model;
    private double screenSize;
    private String cpu;
    private double price;

    public Laptop() {
        this("", "", 0.0, "", 0.0);
    }

    public Laptop(String manufacturer, String model, double screenSize, String cpu, double price) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.screenSize = screenSize;
        this.cpu = cpu;
        this.price = price;
    }

    protected Laptop(Parcel in) {
        manufacturer = in.readString();
        model = in.readString();
        screenSize = in.readDouble();
        cpu = in.readString();
        price = in.readDouble();
    }

    public static List<Laptop> getSampleList() {
        return new ArrayList<>(Arrays.asList(
                new Laptop("Asus", "X509FL-BQ293", 15.6, "Intel Core i5-8265U", 619),
                new Laptop("Apple", "MacBook Air", 13.3, "Intel Core i3", 1099),
                new Laptop("Lenovo", "IdeaPad S145-15AST", 15.6, "AMD A6-9225", 209),
                new Laptop("Asus", "TUF Gaming FX705DT-AU056", 17.3, "AMD Ryzen 5 3550H", 759),
                new Laptop("MSI", "Modern 14 (A10M-653XUA)", 14.0, "Intel Core i5-10210U", 609),
                new Laptop("Asus", "X509FL-BQ293", 15.6, "Intel Core i5-8265U", 619),
                new Laptop("Apple", "MacBook Air", 13.3, "Intel Core i3", 1099),
                new Laptop("Lenovo", "IdeaPad S145-15AST", 15.6, "AMD A6-9225", 209),
                new Laptop("Asus", "TUF Gaming FX705DT-AU056", 17.3, "AMD Ryzen 5 3550H", 759),
                new Laptop("MSI", "Modern 14 (A10M-653XUA)", 14.0, "Intel Core i5-10210U", 609),
                new Laptop("Asus", "X509FL-BQ293", 15.6, "Intel Core i5-8265U", 619),
                new Laptop("Apple", "MacBook Air", 13.3, "Intel Core i3", 1099),
                new Laptop("Lenovo", "IdeaPad S145-15AST", 15.6, "AMD A6-9225", 209),
                new Laptop("Asus", "TUF Gaming FX705DT-AU056", 17.3, "AMD Ryzen 5 3550H", 759),
                new Laptop("MSI", "Modern 14 (A10M-653XUA)", 14.0, "Intel Core i5-10210U", 609),
                new Laptop("Something long", "Really really lengthy text", 99.9, "Some good one", 9999)
        ));
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(manufacturer);
        dest.writeString(model);
        dest.writeDouble(screenSize);
        dest.writeString(cpu);
        dest.writeDouble(price);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return manufacturer + " " + model;
    }

    public String getPriceAsText() {
        return (int) price + "$";
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return Double.compare(laptop.screenSize, screenSize) == 0 &&
                Double.compare(laptop.price, price) == 0 &&
                Objects.equals(manufacturer, laptop.manufacturer) &&
                Objects.equals(model, laptop.model) &&
                Objects.equals(cpu, laptop.cpu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, model, screenSize, cpu, price);
    }

    @Override
    public String toString() {
        return "Manufacturer: " + manufacturer + "\n" +
                "Model: " + model + "\n" +
                "Screen size: " + screenSize + "″" + "\n" +
                "CPU: " + cpu + "\n" +
                "Price: " + price + "$";
    }
}
