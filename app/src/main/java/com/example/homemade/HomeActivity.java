package com.example.homemade;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    ArrayAdapter<CharSequence> adapter;

    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void filt_prix(View view) {
    }

    public void filt_categorie(View view) {
    }

    public void filt_wilaya(View view) {

        PopupMenu p = new PopupMenu(this, view);
        p.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) this);
        p.inflate(R.menu.wilaya_menu);
        p.show();

    }

    @Override

    public boolean onMenuItemClick(MenuItem item){

        btn = findViewById(R.id.id_aff_wilaya);

        btn.setText(item.getTitle());

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    public void act_register(MenuItem item) {

        Intent intent = new Intent(HomeActivity.this , RegisterActivity.class);

        startActivity(intent);

        finish();
    }

    public void activity_login(MenuItem item) {

        Intent intent = new Intent(HomeActivity.this , LoginActivity.class);

        startActivity(intent);

        finish();
    }
}
