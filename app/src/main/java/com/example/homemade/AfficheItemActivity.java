package com.example.homemade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class AfficheItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_big);
        getIncomingIntent();
    }

    private void getIncomingIntent(){


        if(getIntent().hasExtra("imgResId") && getIntent().hasExtra("nom")&& getIntent().hasExtra("categorie")&& getIntent().hasExtra("prix")&&getIntent().hasExtra("description")){


            int img = getIntent().getIntExtra("imgResId",1);


            String Name = getIntent().getStringExtra("nom");
            String cat = getIntent().getStringExtra("categorie");
            String Prix = getIntent().getStringExtra("prix");
            String desc = getIntent().getStringExtra("description");
            setItems(img,Name,cat,Prix,desc);

        }
    }


    private void setItems(int img, String Name, String cat , String Prix, String desc){



        ImageView iv = (ImageView) findViewById(R.id.image_big);
        Glide.with(this)
                .load(img)
                .into(iv);

        TextView nom = (TextView) findViewById(R.id.id_text_prod);
        nom.setText(Name);
        TextView  categorie = (TextView) findViewById(R.id.id_text_categorie);
        categorie.setText(cat);
        TextView  prix =  (TextView) findViewById(R.id.id_text_prix);
        prix.setText(Prix);
        TextView  description = (TextView) findViewById(R.id.id_description);
        description.setText(desc);



    }




}
