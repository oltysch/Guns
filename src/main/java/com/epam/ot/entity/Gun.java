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
}
