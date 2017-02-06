package com.example.zelin_sizebook;

public class Size {
    public String name;
    public String date_Y;
    public String date_M;
    public String date_D;
    public String neck;
    public String bust;
    public String chest;
    public String waist;
    public String hip;
    public String inseam;
    public String comment;

    public Size(String name, String date_Y, String date_M, String date_D, String neck, String bust, String chest, String waist, String hip, String inseam, String comment){
        this.name = name;
        this.date_Y = date_Y;
        this.date_M = date_M;
        this.date_D = date_D;
        this.neck = neck;
        this.bust = bust;
        this.chest = chest;
        this.waist = waist;
        this.hip = hip;
        this.inseam = inseam;
        this.comment = comment;
    }
    public String SizetoString(){
        return name + " | " + date_Y +"/"+date_M+"/"+date_D +"\n" + "neck : " + neck +"\n"+ "bust : "+bust +"\n"+"chest : "+chest+"\n" +"waist : "+waist+"\n"+ "hip : "+hip+"\n"+"inseam : "+inseam +"\n" + comment;
    }
}