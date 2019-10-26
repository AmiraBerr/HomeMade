package com.example.homemade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

public class ContactActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
    }

    public void email(View view) {

        //Toast.makeText(this, "bouton", Toast.LENGTH_SHORT).show();

        PopupMenu p = new PopupMenu(this, view);
        p.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) this);
        p.inflate(R.menu.popup_contact_menu);
        p.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_tel:
                //Toast.makeText(this, "item 1", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:0558335325"));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    return false;
                }
                startActivity(intent);
                return true;
            case R.id.id_email :
                //Toast.makeText(this, "item 2", Toast.LENGTH_SHORT).show();
                String[] send_to ={"tester.this@gmail.com"};
                Intent intent2 = new Intent(Intent.ACTION_SENDTO);
                intent2.setData(Uri.parse("mailto:"));
                intent2.putExtra(Intent.EXTRA_EMAIL,send_to);
                intent2.putExtra(Intent.EXTRA_SUBJECT,"Commande de Home Made");
                intent2.putExtra(Intent.EXTRA_TEXT,"exemple hada le corps de l'email");

                startActivity(Intent.createChooser(intent2,"choisissez une app"));

                return true;

            default: return false;
        }
    }
}
