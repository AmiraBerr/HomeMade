package com.example.homemade;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static com.example.homemade.PathUtil.getPath;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper my_Data_Base;

    boolean checked=false;

    EditText nom_user;

    EditText pass_user;

    EditText cmf_pass_user;

    EditText tel_user;

    EditText email_user;

    ImageView img_profile;

    Spinner spinner;

    String path;

    Button btn;


    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinner = findViewById(R.id.id_spinner_wilaya);

        adapter = ArrayAdapter.createFromResource(this,R.array.wilaya,android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getBaseContext(), adapterView.getItemIdAtPosition(i)+ " ",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        nom_user = findViewById(R.id.id_edit_username);

        btn = findViewById(R.id.id_sinscrire);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nv_compte();
            }
        });
    }


    public void ajoute_img_profile(View view) {


        if (ActivityCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(RegisterActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            return;
        }

        boolean checked=false;

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);


        intent.setType("image/*");

        startActivityForResult(Intent.createChooser(intent,"Select a file"), 2);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        ///////////////////////////////////b1//////////////////////////////
        if (requestCode == 2 && resultCode == RESULT_OK) {

            ImageButton b1 = findViewById(R.id.id_img_register);
            b1.setEnabled(false);
            b1.setVisibility(View.INVISIBLE);

            ImageView v = findViewById(R.id.id_aff_profile);
            v.setVisibility(View.VISIBLE);

            final ImageView imgview = findViewById(R.id.id_aff_profile);
            final List<Bitmap> bitmaps = new ArrayList<>();
            //ClipData clipData = data.getClipData();

            Uri img = data.getData();

            //path = img.toString();


            try {
                path=getPath(this,img);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            Log.v("myapp",path);
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

    public void nv_compte() {


        nom_user = findViewById(R.id.id_edit_username);

        pass_user= findViewById(R.id.id_edit_mdp);

        cmf_pass_user = findViewById(R.id.id_edit_nvmdp);

        tel_user = findViewById(R.id.id_edit_tel);

        email_user = findViewById(R.id.id_edit_email);

        img_profile = findViewById(R.id.id_aff_profile);


        if(nom_user.getText().toString().matches("")){
            Toast.makeText(this, "Entrez un nom", Toast.LENGTH_SHORT).show();
            return;
        }
        if(pass_user.getText().toString().matches("")){
            Toast.makeText(this, "Entrez le mot de passe", Toast.LENGTH_SHORT).show();
            return;
        }
        if(cmf_pass_user.getText().toString().matches("")){
            Toast.makeText(this, "Confirmer le mot de passe", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!pass_user.getText().toString().matches(cmf_pass_user.getText().toString())){
            Toast.makeText(this, "Mots de passe non identiques ", Toast.LENGTH_SHORT).show();
            return;
        }
        my_Data_Base = new DatabaseHelper(this);

        if (my_Data_Base.checkedTel(tel_user.getText().toString())== true){
            Toast.makeText(this, "Mobile existe déjà ", Toast.LENGTH_SHORT).show();
            return;
        }
       try{
        String[] name = path.split("/");

        Toast.makeText(this,name.toString(),Toast.LENGTH_LONG).show();

        String fname_img = Environment.getExternalStorageDirectory() +"/homemade/"+ name[name.length-1];
        File file = new File(fname_img );
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        //bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
        fileOutputStream.flush();
    } catch (IOException e) {
        Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }

       if(my_Data_Base.insertDataUser(nom_user.getText().toString(),spinner.toString(),pass_user.getText().toString(),tel_user.getText().toString(),email_user.getText().toString())==false){
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);

            builder.setMessage("User existe déja").setPositiveButton("OK", null);

            AlertDialog alert = builder.create();

            alert.show();
        }

        else
        {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

            startActivity(intent);

            finish();
        }

    }


    public void reset(View view) {
    }
}
