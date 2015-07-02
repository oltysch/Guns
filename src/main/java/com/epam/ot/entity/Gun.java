package com.epam.ot.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 30.06.2015.
 */
public class Gun {
    private String model;
    private String origin;
    private Handy handy;
    private Map<String, String> ttc = new HashMap<String, String>();
    private String material;
    public enum Handy {ONE_HANDED, TWO_HANDED}
    public enum Availability {AVAILABLE, UNAVAILABLE}

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Gun(String model, String origin, /*Handy handy,*/ String material) {
        this.model = model;
        this.origin = origin;
//        this.handy = handy;
        this.material = material;
    }
}
