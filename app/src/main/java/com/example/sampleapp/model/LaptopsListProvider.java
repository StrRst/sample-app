package com.example.sampleapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LaptopsListProvider {

    public static List<Laptop> getList() {
        return new ArrayList<>(Arrays.asList(
                new Laptop("Asus", "X509FL-BQ293", 15.6, "Intel Core i5-8265U", 619),
                new Laptop("Apple", "MacBook Air", 13.3, "Intel Core i3", 1099),
                new Laptop("Lenovo", "IdeaPad S145-15AST", 15.6, "AMD A6-9225", 209),
                new Laptop("Asus", "TUF Gaming FX705DT-AU056", 17.3, "AMD Ryzen 5 3550H", 759),
                new Laptop("MSI", "Modern 14 (A10M-653XUA)", 14.0, "Intel Core i5-10210U", 609)
        ));
    }
}
