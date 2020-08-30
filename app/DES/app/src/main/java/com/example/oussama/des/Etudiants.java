package com.example.oussama.des;

/**
 * Created by oussama on 06/04/17.
 */

public class Etudiants {



    private String mat,name,prename,email;

    public Etudiants(String mat, String name, String prename,String email){

        this.setMat(mat);
        this.setName(name);
        this.setPrename(prename);
        this.setEmail(email);


    }

    public String getMat() {
        return mat;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){

        this.email = email;
    }
}
