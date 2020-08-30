package com.example.oussama.des;

/**
 * Created by oussama on 15/04/17.
 */

public class Matiers {



    private String matier,cc,exm,rfs,moy;

    public Matiers(String matier,String cc,String exm,String rfs,String moy){

        this.setMatier(matier);
        this.setCc(cc);
        this.setExm(exm);
        this.setRfs(rfs);
        this.setMoy(moy);

    }


    public String getMatier() {
        return matier;
    }

    public void setMatier(String matier) {
        this.matier = matier;
    }

    public String getExm() {
        return exm;
    }

    public void setExm(String exm) {
        this.exm = exm;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getRfs() {
        return rfs;
    }

    public void setRfs(String rfs) {
        this.rfs = rfs;
    }

    public String getMoy() {
        return moy;
    }

    public void setMoy(String moy) {
        this.moy = moy;
    }
}
