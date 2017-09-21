package com.github.hisaichi5518.konohana.test;

import com.github.hisaichi5518.konohana.annotation.Key;
import com.github.hisaichi5518.konohana.annotation.Store;

@Store
interface Success {
    @Key
    int id = 0;
}