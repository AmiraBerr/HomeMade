package com.example.homemade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AjouterProduitActivity extends AppCompatActivity {


    boolean checked=false;

    Spinner spinner;

    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_produit);

        ImageButton i2 =  findViewById(R.id.id_img_prod2);

        i2.setEnabled(false);

        ImageButton i3 =  findViewById(R.id.id_img_prod3);

        i3.setEnabled(false);

        ImageButton i4 =  findViewById(R.id.id_img_prod4);

        i4.setEnabled(false);

        ImageButton i5 =  findViewById(R.id.id_img_prod5);

        i5.setEnabled(false);

        spinner = (Spinner) findViewById(R.id.id_spinner_categ);

        adapter = ArrayAdapter.createFromResource(this,R.array.categ,android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Toast toast = Toast.makeText(getBaseContext(), adapterView.getItemIdAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void b1(View view) {

        checked=true;




        if (ActivityCompat.checkSelfPermission(AjouterProduitActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AjouterProduitActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            return;
        }

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);

        intent.setType("image/*");

        startActivityForResult(intent, 2);

    }

    public void b2(View view) {




        if (ActivityCompat.checkSelfPermission(AjouterProduitActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AjouterProduitActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            return;
        }

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setType("image/*");

        startActivityForResult(intent, 1);

    }

    public void b3(View view) {




        if (ActivityCompat.checkSelfPermission(AjouterProduitActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AjouterProduitActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            return;
        }

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setType("image/*");

        startActivityForResult(intent, 3);

    }

    public void b4(View view) {




        if (ActivityCompat.checkSelfPermission(AjouterProduitActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AjouterProduitActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            return;
        }

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setType("image/*");

        startActivityForResult(intent, 4);

    }

    public void b5(View view) {




        if (ActivityCompat.checkSelfPermission(AjouterProduitActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AjouterProduitActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            return;
        }

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setType("image/*");

        startActivityForResult(intent, 5);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        ///////////////////////////////////b1//////////////////////////////
        if(requestCode==2 && resultCode==RESULT_OK) {

            ImageButton b1 = findViewById(R.id.id_img_prod1);
            b1.setEnabled(false);
            b1.setVisibility(View.INVISIBLE);

            ImageButton b2 = findViewById(R.id.id_img_prod2);
            b2.setEnabled(true);
            b2.setVisibility(View.VISIBLE);
            ImageView v = findViewById(R.id.id_aff_prod1);
            v.setVisibility(View.VISIBLE);

            final ImageView imgview = findViewById(R.id.id_aff_prod1);
            final List<Bitmap> bitmaps = new ArrayList<>();
            //ClipData clipData = data.getClipData();

            Uri img = data.getData();

            if (img != null) {
                Toast.makeText(this, "photo ajoutée", Toast.LENGTH_SHORT).show();

                InputStream is = null;
                try {
                    is = getContentResolver().openInputStream(img);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imgview.setImageBitmap(bitmap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Something wrong", Toast.LENGTH_SHORT).show();
                }


            } else {
                Toast.makeText(this, "Selectionnez une image", Toast.LENGTH_SHORT).show();
            }

        }


        /////////////////////////////b2////////////////////////////////////
        if(requestCode==1 && resultCode==RESULT_OK) {

            ImageButton b1 = findViewById(R.id.id_img_prod2);
            b1.setEnabled(false);
            b1.setVisibility(View.INVISIBLE);

            ImageButton c2 = findViewById(R.id.id_img_prod3);
            c2.setEnabled(true);
            c2.setVisibility(View.VISIBLE);
            ImageView v2 = findViewById(R.id.id_aff_prod2);
            v2.setVisibility(View.VISIBLE);

            final ImageView imgview2 = findViewById(R.id.id_aff_prod2);
            final List<Bitmap> bitmaps = new ArrayList<>();
            //ClipData clipData = data.getClipData();

            Uri img2 = data.getData();

            if (img2 != null) {
                Toast.makeText(this, "photo ajoutée", Toast.LENGTH_SHORT).show();

                InputStream is = null;
                try {
                    is = getContentResolver().openInputStream(img2);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imgview2.setImageBitmap(bitmap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Something wrong", Toast.LENGTH_SHORT).show();
                }


            } else {
                Toast.makeText(this, "Selectionnez une image", Toast.LENGTH_SHORT).show();
            }

        }

        ////////////////////////////////b3///////////////////////////////

        if(requestCode==3 && resultCode==RESULT_OK) {

            ImageButton b1 = findViewById(R.id.id_img_prod3);
            b1.setEnabled(false);
            b1.setVisibility(View.INVISIBLE);

            ImageButton c2 = findViewById(R.id.id_img_prod4);
            c2.setEnabled(true);
            c2.setVisibility(View.VISIBLE);
            ImageView v2 = findViewById(R.id.id_aff_prod3);
            v2.setVisibility(View.VISIBLE);

            final ImageView imgview = findViewById(R.id.id_aff_prod3);
            final List<Bitmap> bitmaps = new ArrayList<>();
            //ClipData clipData = data.getClipData();

            Uri img = data.getData();

            if (img != null) {
                Toast.makeText(this, "photo ajoutée", Toast.LENGTH_SHORT).show();

                InputStream is = null;
                try {
                    is = getContentResolver().openInputStream(img);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imgview.setImageBitmap(bitmap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Something wrong", Toast.LENGTH_SHORT).show();
                }


            } else {
                Toast.makeText(this, "Selectionnez une image", Toast.LENGTH_SHORT).show();
            }

        }


        ////////////////////////////////////b4///////////////////////////////////

        if(requestCode==4 && resultCode==RESULT_OK) {

            ImageButton b1 = findViewById(R.id.id_img_prod4);
            b1.setEnabled(false);
            b1.setVisibility(View.INVISIBLE);

            ImageButton c2 = findViewById(R.id.id_img_prod5);
            c2.setEnabled(true);
            c2.setVisibility(View.VISIBLE);
            ImageView v2 = findViewById(R.id.id_aff_prod4);
            v2.setVisibility(View.VISIBLE);

            final ImageView imgview = findViewById(R.id.id_aff_prod4);
            final List<Bitmap> bitmaps = new ArrayList<>();
            //ClipData clipData = data.getClipData();

            Uri img = data.getData();

            if (img != null) {
                Toast.makeText(this, "photo ajoutée", Toast.LENGTH_SHORT).show();

                InputStream is = null;
                try {
                    is = getContentResolver().openInputStream(img);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imgview.setImageBitmap(bitmap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Something wrong", Toast.LENGTH_SHORT).show();
                }


            } else {
                Toast.makeText(this, "Selectionnez une image", Toast.LENGTH_SHORT).show();
            }

        }


        /////////////////////////////////////b5////////////////////////////////////////
        if(requestCode==5 && resultCode==RESULT_OK) {

            ImageButton b1 = findViewById(R.id.id_img_prod5);
            b1.setEnabled(false);
            b1.setVisibility(View.INVISIBLE);


            ImageView v2 = findViewById(R.id.id_aff_prod5);
            v2.setVisibility(View.VISIBLE);

            final ImageView imgview = findViewById(R.id.id_aff_prod5);
            final List<Bitmap> bitmaps = new ArrayList<>();
            //ClipData clipData = data.getClipData();

            Uri img = data.getData();

            if (img != null) {
                Toast.makeText(this, "photo ajoutée", Toast.LENGTH_SHORT).show();

                InputStream is = null;
                try {
                    is = getContentResolver().openInputStream(img);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imgview.setImageBitmap(bitmap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Something wrong", Toast.LENGTH_SHORT).show();
                }


            } else {
                Toast.makeText(this, "Selectionnez une image", Toast.LENGTH_SHORT).show();
            }

        }
    }


    public void valider(View view) {
        EditText nom= (EditText) findViewById(R.id.id_edit_nom);
        String n=nom.getText().toString();
        if(n.matches("")){
            Toast.makeText(this, "Entrez un nom", Toast.LENGTH_SHORT).show();
            return;
        }

        Spinner cat= (Spinner) findViewById(R.id.id_spinner_categ);
        String s=cat.getSelectedItem().toString();

        EditText prix= (EditText) findViewById(R.id.id_edit_prix);
        String p=prix.getText().toString();
        if(p.matches("")){
            Toast.makeText(this, "Entrez le prix", Toast.LENGTH_SHORT).show();
            return;
        }


        EditText desc= (EditText) findViewById(R.id.id_edit_desc);
        String de=desc.getText().toString();


        if(checked==false){
            Toast.makeText(this, "choisissez une photo", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseHelper d = new DatabaseHelper(this);
        Boolean b = d.InsertDataProduit(n,s,"bbbbb","",de);


        //boolean b =InsertDataProduit(n,p,"aaddz","aaaaa","bbbbb");
        if(b==true){
            Toast.makeText(this, "ligne ajoutée", Toast.LENGTH_SHORT).show();


            //Toast.makeText(this, rr.get(0).toString(), Toast.LENGTH_SHORT).show();



        }else{
            Toast.makeText(this, "erreur !", Toast.LENGTH_SHORT).show();
        }


    }

    public void annuler(View view) {
       /* EditText nom= (EditText) findViewById(R.id.id_edit_nom);
        nom.getText().clear();
        EditText prix= (EditText) findViewById(R.id.id_edit_prix);
        prix.getText().clear();
        EditText desc= (EditText) findViewById(R.id.id_edit_desc);
        desc.getText().clear();

        Spinner s = (Spinner) findViewById(R.id.id_spinner_categ);
        s.setSelection(0);

        ImageButton i1 = (ImageButton) findViewById(R.id.id_img_prod1);
        i1.setEnabled(true);
        i1.setVisibility(View.VISIBLE);

        ImageButton i2 = (ImageButton) findViewById(R.id.id_img_prod2);
        i2.setEnabled(false);
        i2.setVisibility(View.INVISIBLE);

        ImageButton i3 = (ImageButton) findViewById(R.id.id_img_prod3);
        i3.setEnabled(false);
        i3.setVisibility(View.INVISIBLE);

        ImageButton i4 = (ImageButton) findViewById(R.id.id_img_prod4);
        i4.setEnabled(false);
        i4.setVisibility(View.INVISIBLE);

        ImageButton i5 = (ImageButton) findViewById(R.id.id_img_prod5);
        i5.setEnabled(false);
        i5.setVisibility(View.INVISIBLE);

        ImageView im1 = (ImageView) findViewById(R.id.id_aff_prod1);
        im1.setVisibility(View.INVISIBLE);

        ImageView im2 = (ImageView) findViewById(R.id.id_aff_prod2);
        im2.setVisibility(View.INVISIBLE);

        ImageView im3 = (ImageView) findViewById(R.id.id_aff_prod3);
        im3.setVisibility(View.INVISIBLE);

        ImageView im4 = (ImageView) findViewById(R.id.id_aff_prod4);
        im4.setVisibility(View.INVISIBLE);

        ImageView im5 = (ImageView) findViewById(R.id.id_aff_prod5);
        im5.setVisibility(View.INVISIBLE);*/

        DatabaseHelper d = new DatabaseHelper(this);
        ArrayList rr = d.getDataProduitModifier("1");


        String ligne = rr.get(0).toString();
        String[] colonne=ligne.split(":");
        EditText nom= (EditText) findViewById(R.id.id_edit_nom);
        nom.setText(colonne[1]);


        Spinner sp= (Spinner) findViewById(R.id.id_spinner_categ);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.categ,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        if(colonne[2]!= null){
            int pos = adapter.getPosition(colonne[2]);
            sp.setSelection(pos);
        }

    }

}
