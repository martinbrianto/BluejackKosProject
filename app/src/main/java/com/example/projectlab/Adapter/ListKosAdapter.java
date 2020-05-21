package com.example.projectlab.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectlab.KosListForm;
import com.example.projectlab.Model.Kos;
import com.example.projectlab.R;
import com.example.projectlab.Storage.KosStorage;

import static com.example.projectlab.Storage.KosStorage.ListKos;

public class ListKosAdapter extends RecyclerView.Adapter<ListKosAdapter.ViewHolder> {

    private OnKosListerner mKosListener;


    public ListKosAdapter(OnKosListerner mKosListener) {
        this.mKosListener = mKosListener;
    }

    @NonNull
    @Override
    public ListKosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_kos ,parent, false);



        return new ViewHolder(v, mKosListener);


    }

    @Override
    public void onBindViewHolder(@NonNull ListKosAdapter.ViewHolder holder, int position) {


    Kos k = ListKos.get(position);
    holder.tvName.setText(k.getName());
    holder.tvPrice.setText("Rp "+k.getPrice()+" /Bulan");
    holder.tvFacility.setText(k.getFacility());
    holder.picture.setImageResource(k.getPic());



    }

    @Override
    public int getItemCount() {
        return ListKos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvName, tvPrice, tvFacility;
        ImageView picture;
        OnKosListerner onKosListerner;
        public ViewHolder(@NonNull View itemView, OnKosListerner onKosListerner) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvFacility = itemView.findViewById(R.id.tv_facility);
            picture = itemView.findViewById(R.id.picture);
            this.onKosListerner = onKosListerner;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onKosListerner.onKosClick(getAdapterPosition());
        }
    }
    public interface OnKosListerner{
        void onKosClick(int position);
    }
}
