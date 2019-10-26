package com.example.homemade;

public class Item {

    private String imgResId;
    private String nom;
    private String categorie;
    private String prix;
    private String description;
    private String date_pub;
    private String id_user;


    public Item(String imgResId, String nom, String categorie, String prix, String description,String date_pub,String id_user) {
        this.imgResId = imgResId;
        this.nom = nom;
        this.categorie = categorie;
        this.prix = prix;
        this.description=description;
        this.date_pub=date_pub;
        this.id_user=id_user;
    }

    public Item() {
    }

    public String getImgResId() {
        return imgResId;
    }

    public String getNom() {
        return nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getPrix() {
        return prix;
    }
    public String getDescription() {
        return description;
    }

}
