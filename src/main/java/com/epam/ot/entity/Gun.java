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
    private Availability cartridgeClip;
    private Availability optics;
    private String material;
    public enum Handy {One_handed, Two_handed}
    public enum Availability {Available, Unavailable}

    public Gun(String model, String origin, Handy handy, int firingRange, int effectiveFiringRange,
               Availability cartridgeClip, Availability optics, String material) {
        this.model = model;
        this.origin = origin;
        this.handy = handy;
        this.firingRange = firingRange;
        this.effectiveFiringRange = effectiveFiringRange;
        this.cartridgeClip = cartridgeClip;
        this.optics = optics;
        this.material = material;
    }

    public String gunToString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(model+" "+origin+" "+handy+" "+material+"\n     FR: "+firingRange+" EFR: "+effectiveFiringRange+" CC: "+cartridgeClip+" Opt.: "+optics);
        return stringBuffer.toString();
    }

    public String getModel() {
        return model;
    }
    @XmlElement
    public void setModel(String model) {
        this.model = model;
    }

    public String getOrigin() {
        return origin;
    }
    @XmlElement
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Handy getHandy() {
        return handy;
    }
    @XmlElement
    public void setHandy(Handy handy) {
        this.handy = handy;
    }

    public int getFiringRange() {
        return firingRange;
    }
    @XmlElement
    public void setFiringRange(int firingRange) {
        this.firingRange = firingRange;
    }

    public int getEffectiveFiringRange() {
        return effectiveFiringRange;
    }
    @XmlElement
    public void setEffectiveFiringRange(int effectiveFiringRange) {
        this.effectiveFiringRange = effectiveFiringRange;
    }

    public Availability getCartridgeClip() {
        return cartridgeClip;
    }
    @XmlElement
    public void setCartridgeClip(Availability cartridgeClip) {
        this.cartridgeClip = cartridgeClip;
    }

    public Availability getOptics() {
        return optics;
    }
    @XmlElement
    public void setOptics(Availability optics) {
        this.optics = optics;
    }

    public String getMaterial() {
        return material;
    }

    public Gun() {
    }

    @XmlElement
    public void setMaterial(String material) {
        this.material = material;
    }
}
