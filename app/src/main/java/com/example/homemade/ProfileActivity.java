package com.example.homemade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import static com.example.homemade.ItemAdapter.SPAN_COUNT_ONE;
import static com.example.homemade.ItemAdapter.SPAN_COUNT_THREE;

public class ProfileActivity extends AppCompatActivity {
    DatabaseHelper my_Data_Base;
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private GridLayoutManager gridLayoutManager;
    private List<Item> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        initItemsData();

        gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT_THREE);
        itemAdapter = new ItemAdapter(ProfileActivity.this,items,gridLayoutManager);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);



    }

    private void initItemsData() {
        items = new ArrayList<>(5);

        my_Data_Base = new DatabaseHelper(this);

        ArrayList arrayList = new ArrayList<String>();

        arrayList= my_Data_Base.getDataProduit();

        for(int i=0;i<arrayList.size();i++){
            String ch = (String) arrayList.get(i);
            String[] item =ch.split(":") ;
            items.add(new Item(item[5], item[1], item[2],item[3],item[4],item[7],item[6]));

        }
    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater menuInflater = getMenuInflater();

        //menuInflater.inflate(R.menu.profile_menu,menu);

        return true;
    }


/*
    public void Switch(View view) {
        switchLayout();

    }

    private void switchLayout() {
        if (gridLayoutManager.getSpanCount() == SPAN_COUNT_ONE) {
            gridLayoutManager.setSpanCount(SPAN_COUNT_THREE);
        } else {
            gridLayoutManager.setSpanCount(SPAN_COUNT_ONE);

        }
        itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());

    }

    private void switchLayout()
    {
        if (gridLayoutManager.getSpanCount() == SPAN_COUNT_THREE){
            Intent intent=new Intent(this,AfficheItemActivity.class);
            intent.putExtra("imgResId",item.getImgResId());
            intent.putExtra("nom",item.getNom());
            intent.putExtra("categorie",item.getCategorie());
            intent.putExtra("prix",item.getPrix());
            intent.putExtra("description",item.getDescription());
            this.startActivity(intent);

        }


    }

*/

}
