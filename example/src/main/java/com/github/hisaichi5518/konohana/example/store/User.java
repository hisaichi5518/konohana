package com.github.hisaichi5518.konohana.example.store;

import com.github.hisaichi5518.konohana.annotation.Key;
import com.github.hisaichi5518.konohana.annotation.Store;

import java.util.ArrayList;
import java.util.List;

@Store
interface User {
    @Key
    String name = "default name";

    @Key
    Type type = Type.none;

    @Key
    List<Product> products = new ArrayList<>();

    enum Type {
        none
    }

    class Product {
        String name;
    }
}
