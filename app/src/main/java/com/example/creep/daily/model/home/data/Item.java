package com.example.creep.daily.model.home.data;

import java.io.Serializable;

/**
 * Created by creep on 2016/9/15.
 */

public class Item implements Serializable {
    public Class clz;
    public String name;

    public Item(Class clz, String name) {
        this.clz = clz;
        this.name = name;
    }
}
