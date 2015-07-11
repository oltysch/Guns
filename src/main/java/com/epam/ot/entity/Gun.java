package com.epam.ot.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 30.06.2015.
 */
@XmlRootElement
public class Gun {
    private String model;
    private String origin;
    private Handy handy;
//    private Map<String, String> ttc = new HashMap<String, String>();
    private int firingRange;
    private int effectiveFiringRange;
    private Boolean cartridgeClip;
    private Boolean optics;
    private String material;
    public enum Handy {One_handed, Two_handed;}
    public Gun() {
    }

    public Gun(String model, String origin, Handy handy, int firingRange, int effectiveFiringRange,
               Boolean cartridgeClip, Boolean optics, String material) {
        this.model = model;
        this.origin = origin;
        this.handy = handy;
        this.firingRange = firingRange;
        this.effectiveFiringRange = effectiveFiringRange;
        this.cartridgeClip = cartridgeClip;
        this.optics = optics;
        this.material = material;
    }

    @Override
    public String toString() {
        return model + " " + origin + " " + handy + " " + material + "\n     FR: " + firingRange + " EFR: " + effectiveFiringRange + " CC: " + cartridgeClip + " Opt.: " + optics;
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
    public Boolean getCartridgeClip() {
        return cartridgeClip;
    }


    public void setCartridgeClip(Boolean cartridgeClip) {
        this.cartridgeClip = cartridgeClip;
    }
    public Boolean getOptics() {
        return optics;
    }


    public void setOptics(Boolean optics) {
        this.optics = optics;
    }

    public String getMaterial() {
        return material;
    }


    public void setMaterial(String material) {
        this.material = material;
    }
}
