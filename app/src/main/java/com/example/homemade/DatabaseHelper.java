package com.example.homemade;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Homemade.db";
    public static final String Table1_NAME = "User";
    public static final String Table2_NAME = "Produit";
    SQLiteDatabase sqLiteDatabase;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
        sqLiteDatabase = this.getWritableDatabase();
    }

    /**
     *
     * @param sqLiteDatabase
     * Methode pour creation des tables dans la base de données
     */


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("Create table "+Table1_NAME+
         "("+
           "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
           "Nom                  Text , " +
           "Wilaya               Text , " +
           "Mot_pass             Text , " +
           "Tel                  Text unique, " +
           "Email                Text" +
         ")"
        );

        sqLiteDatabase.execSQL("Create table "+Table2_NAME+
                "("+
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Nom_Prod        Text , " +
                "Category_Prod   Text , " +
                "Image_Prod      Text,"+
                "ID_User        Integer,"+
                "desc_prod           Text "+
                ")"
        );

    }

    /**
     *
     * @param sqLiteDatabase
     * @param i
     * @param i1
     * Methode pour la supprission des Tables
     */

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("Drop table if exists "+ Table1_NAME);

        sqLiteDatabase.execSQL("Drop table if exists "+ Table1_NAME);

        onCreate(sqLiteDatabase);
    }

    /**
     *
     * @param Nom
     * @param Wilaya
     * @param Mot_pass
     * @param Tel
     * @param Email
     * @return boolean
     * Methode pour insertion dans la Table User
     */

    public boolean insertDataUser(String Nom, String Wilaya , String  Mot_pass ,String Tel ,String  Email ){

        ContentValues contentValues = new ContentValues();

        contentValues.put("Nom",Nom);

        contentValues.put("Wilaya",Wilaya);

        contentValues.put("Mot_pass",Mot_pass);

        contentValues.put("Tel",Tel);

        contentValues.put("Email",Email);

        long result = sqLiteDatabase.insert(Table1_NAME,null,contentValues);

        if (result == -1)
        {
            return  false;
        }
        else
        {
            return  true;
        }
    }


    /**
     *
     * @return ArrayList
     * Methode pour select des User
     */

    public ArrayList getDataUser(){

        ArrayList arrayList = new ArrayList();

        String t1,t2,t3,t4,t5,t6;

        Cursor res = sqLiteDatabase.rawQuery("select * from "+Table1_NAME,null);

        res.moveToFirst();

        while(res.isAfterLast()==false)
        {
             t1 = res.getString(0);
             t2 = res.getString(1);
             t3 = res.getString(2);
             t4 = res.getString(3);
             t5 = res.getString(4);
             t6 = res.getString(5);

             arrayList.add(t1 + " " + t2 + " " + t3 + " " + t4 + " " + t5 + " " + t6);

            res.moveToNext();
        }

         return arrayList;
    }


    /**
     *
     * @param ID
     * @param Nom
     * @param Wilaya
     * @param Mot_pass
     * @param Tel
     * @param Email
     * @return boolean
     * Methode pour Update User
     */

    public boolean UpdateUser(String ID, String Nom, String Wilaya , String  Mot_pass ,String Tel ,String  Email)
    {
            ContentValues contentValues = new ContentValues();

            contentValues.put("Nom",Nom);

            contentValues.put("Wilaya",Wilaya);

            contentValues.put("Mot_pass",Mot_pass);

            contentValues.put("Tel",Tel);

            contentValues.put("Email",Email);

            sqLiteDatabase.update(Table1_NAME , contentValues, "ID = ?", new String[]{ID});

            return  true ;

    }


    /**
     *
     * @param ID
     * @return Integer
     * Methode pour supprimer User
     */
    public Integer deleteUser(String ID){

        return sqLiteDatabase.delete(Table1_NAME , " ID = ?" , new String[]{ID});
    }

    /**
     *
     * @param Nom_Prod
     * @param Category_Prod

     * @param Image_Prod
     * @return boolean
     * Methode pour Insertion dans la Table Produit
     */

    public boolean InsertDataProduit(String Nom_Prod , String Category_Prod ,String Image_Prod,String ID_User,String desc_prod){

        ContentValues contentValues = new ContentValues();

        contentValues.put("Nom_Prod",Nom_Prod);

        contentValues.put("Category_Prod",Category_Prod);

        contentValues.put("Image_Prod",Image_Prod);

        contentValues.put("desc_prod",desc_prod);

        contentValues.put("ID_User",ID_User);

        long result = sqLiteDatabase.insert(Table2_NAME,null,contentValues);

        if (result == -1){

            return false;
        }
        else{

            return true;
        }
    }

    /**
     *
     * @return ArrayList
     * Methode pour select les Produits
     */

    public ArrayList<String> getDataProduit(){

        ArrayList arrayList = new ArrayList<String>();

        String t1,t2,t3,t4,t5,t6;

        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+ Table2_NAME,null);

        cursor.moveToFirst();

        while (cursor.isAfterLast() == false){

             t1 = cursor.getString(0);
             t2 = cursor.getString(1);
             t3 = cursor.getString(2);
             t4 = cursor.getString(3);
             t5 = cursor.getString(4);
             t6 = cursor.getString(5);

             arrayList.add(t1 + " : " + t2 + " : " + t3 + " : " + t4 + " : " + t5+ ":"+t6);

             cursor.moveToNext();

        }

        return arrayList;
    }

    public ArrayList getDataProduitModifier(String idp) {

        ArrayList arrayList = new ArrayList();

        String t1, t2, t3, t4, t5, t6;

        //Cursor cursor = sqLiteDatabase.rawQuery("select * from "+Table2_NAME+" where ID =?" ,new String[]{idp});
        //Cursor cursor = sqLiteDatabase.rawQuery("select * from "+Table2_NAME+"" ,new String[]{});
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + Table2_NAME+" where ID =?", new String[]{idp});

        cursor.moveToFirst();


        t1 = cursor.getString(0);
        t2 = cursor.getString(1);
        t3 = cursor.getString(2);
        t4 = cursor.getString(3);
        t5 = cursor.getString(4);
        t6 = cursor.getString(5);

        arrayList.add(t1 + " : " + t2 + " : " + t3 + " : " + t4 + " : " + t5 + " : " + t6);

        cursor.moveToNext();



        return arrayList;
    }

    /**
     *
     * @param ID
     * @param Nom_Prod
     * @param Category_Prod
     * @param Image_Prod
     * @return boolean
     * Methode pour update Produit
     */

    public boolean UpdateDataProduit(String ID ,String Nom_Prod , String Category_Prod,String Image_Prod,String desc_prod){

        ContentValues contentValues = new ContentValues();

        contentValues.put("Nom_Prod",Nom_Prod);

        contentValues.put("Category_Prod",Category_Prod);

        contentValues.put("Image_Prod",Image_Prod);

        contentValues.put("dsec_Prod",desc_prod);

        sqLiteDatabase.update(Table2_NAME,contentValues,"ID = ?",new String[]{ID});

        return true;
    }

    /**
     *
     * @param ID
     * @return Integer
     * Methode pour supprimer Produit
     */

    public Integer deleteProduit(String ID){

        return  sqLiteDatabase.delete(Table2_NAME,"ID = ?", new String[]{ID});
    }

    public boolean Connection(String user, String password){

        String query ="SELECT * FROM "+ Table1_NAME + "WHERE Nom LIKE"+user+" AND Mot_pass LIKE" + password;

        //Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+Table1_NAME+" where Nom =? and Mot_pass= ? ", new String[]{user,password});

        if (cursor.getCount()==0)
        {
            return false;
        }
            return true;
    }

    public boolean checkedTel(String tel){

        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+Table1_NAME+" where Tel=?",new String[]{tel});

        if (cursor.getCount()==0){
            return false;
        }

        else {
            return  true;
        }
    }
}
