package com.epam.ot.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Gun {
    private long id;
    private String model;
    private String origin;
    private Handy handy;
    //    private Map<String, String> ttc = new HashMap<String, String>();
    private int firingRange;
    private int effectiveFiringRange;
    private Boolean cartridgeClipAvailability;
    private Boolean opticsAvailability;
    private String material;

    public Gun() {
    }

    public Gun(String model, String origin, Handy handy, int firingRange, int effectiveFiringRange,
               Boolean cartridgeClip, Boolean optics, String material) {
        this.model = model;
        this.origin = origin;
        this.handy = handy;
        this.firingRange = firingRange;
        this.effectiveFiringRange = effectiveFiringRange;
        this.cartridgeClipAvailability = cartridgeClip;
        this.opticsAvailability = optics;
        this.material = material;
    }

    @Override
    public String toString() {
        return model + " " + origin + " " + handy + " " + material + "\n     FR: " + firingRange + " EFR: " + effectiveFiringRange + " CC: " + cartridgeClipAvailability + " Opt.: " + opticsAvailability;
    }

    //TODO delete this after tests
    //this a fake getter - needs for test parser and writer
    public String getStr() {
        return "fake";
    }

    //TODO delete this after tests
    //this a fake setter - needs for test parser and writer
    public void setStr(String s) {
        String str = "fake";
    }


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

    public Handy getHandy() {
        return handy;
    }

    public void setHandy(Handy handy) {
        this.handy = handy;
    }

    public int getFiringRange() {
        return firingRange;
    }

    public void setFiringRange(int firingRange) {
        this.firingRange = firingRange;
    }

    public int getEffectiveFiringRange() {
        return effectiveFiringRange;
    }

    public void setEffectiveFiringRange(int effectiveFiringRange) {
        this.effectiveFiringRange = effectiveFiringRange;
    }

    public Boolean getCartridgeClipAvailability() {
        return cartridgeClipAvailability;
    }

    public void setCartridgeClipAvailability(Boolean cartridgeClipAvailability) {
        this.cartridgeClipAvailability = cartridgeClipAvailability;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getOpticsAvailability() {
        return opticsAvailability;
    }

    public void setOpticsAvailability(Boolean opticsAvailability) {
        this.opticsAvailability = opticsAvailability;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }


    public enum Handy {One_handed, Two_handed;}
}
