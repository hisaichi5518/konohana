package com.github.hisaichi5518.konohana.test;

import com.github.hisaichi5518.konohana.annotation.Key;
import com.github.hisaichi5518.konohana.annotation.Store;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

@Store
interface Success {
    @Key
    int id = 0;

    @Key
    String name = "";

    @Key
    float rate = 0F;

    @Key
    long longRate = 0L;

    @Key
    Type type = Type.none;

    @Key
    List<String> productNames = new ArrayList<>();

    @Key
    List<Type> types = new ArrayList<>();

    @Key
    List<Product> products = new ArrayList<>();

    @Key
    Set<String> stringSet = new HashSet<>();

    @Key
    Set<Integer> productIdSet = new HashSet<>();

    @Key
    Set<Type> typeSet = new HashSet<>();

    @Key
    Set<Product> productSet = new HashSet<>();

    @Key
    Product product = new Product();

    enum Type {
        none
    }

    class Product {
        String name;
    }
}