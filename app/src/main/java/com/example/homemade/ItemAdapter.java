package com.example.homemade;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    public static final int SPAN_COUNT_ONE = 1;
    public static final int SPAN_COUNT_THREE = 3;

    private static final int VIEW_TYPE_SMALL = 1;
    private static final int VIEW_TYPE_BIG = 2;

    private List<Item> mItems;
    private GridLayoutManager mLayoutManager;
    private Context mContext;

    public ItemAdapter(Context context, List<Item> items, GridLayoutManager layoutManager) {
        mItems = items;
        mLayoutManager = layoutManager;
        mContext=context;
    }

    @Override
    public int getItemViewType(int position) {
        int spanCount = mLayoutManager.getSpanCount();
        if (spanCount == SPAN_COUNT_ONE) {
            return VIEW_TYPE_BIG;
        } else {
            return VIEW_TYPE_SMALL;
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_BIG) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_big, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_small, parent, false);
        }
        return new ItemViewHolder(view, viewType);
    }


    @Override
    public void onBindViewHolder( ItemViewHolder holder,final int position) {

        final Item item = mItems.get(position);
        if (getItemViewType(position) == VIEW_TYPE_SMALL) {
            holder.nom.setText(item.getNom());
            holder.iv.setImageResource(Integer.valueOf(item.getImgResId()));
            holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(mContext, (mItems.get(position)).toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, AfficheItemActivity.class);
                    intent.putExtra("imgResId", (mItems.get(position)).getImgResId());
                    intent.putExtra("nom", (mItems.get(position)).getNom());
                    intent.putExtra("categorie", (mItems.get(position)).getCategorie());
                    intent.putExtra("prix", (mItems.get(position)).getPrix());
                    intent.putExtra("description", (mItems.get(position)).getDescription());
                    mContext.startActivity(intent);


                }
            })
            ;

        }

        if (getItemViewType(position) == VIEW_TYPE_BIG) {
            holder.nom.setText(item.getNom());
            holder.iv.setImageResource(Integer.valueOf(item.getImgResId()));
            holder.categorie.setText(item.getCategorie());
            holder.prix.setText(item.getPrix());
            holder.description.setText(item.getDescription());


        }

    }



    @Override
    public int getItemCount() {   // le nombre d'element a afficher  dans grid view
        return mItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView nom;
        TextView categorie;
        TextView prix;
        TextView description;
        LinearLayout parentLayout;


        ItemViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == VIEW_TYPE_BIG) {
                iv = (ImageView) itemView.findViewById(R.id.image_big);
                nom = (TextView) itemView.findViewById(R.id.id_text_prod);
                categorie = (TextView) itemView.findViewById(R.id.id_text_categorie);
                prix = (TextView) itemView.findViewById(R.id.id_text_prix);
                description = (TextView) itemView.findViewById(R.id.id_description);
            } else {
                iv = (ImageView) itemView.findViewById(R.id.image_small);
                nom = (TextView) itemView.findViewById(R.id.id_txt_prod);
                parentLayout=itemView.findViewById(R.id.parent_layout);
            }
        }






    }
}
