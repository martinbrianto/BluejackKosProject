package com.example.projectlab.Adapter;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectlab.Model.Book;
import com.example.projectlab.R;
import com.example.projectlab.Storage.BookStorage;

import static com.example.projectlab.Storage.BookStorage.ListBook;

public class BookingFormAdapter extends RecyclerView.Adapter<BookingFormAdapter.ViewHolder> {

    private OnBookListener mOnBookListener;

    public BookingFormAdapter(OnBookListener mOnBookListener) {
        this.mOnBookListener = mOnBookListener;
    }

    @NonNull
    @Override
    public BookingFormAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_book,parent,false);
        return new ViewHolder(v, mOnBookListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingFormAdapter.ViewHolder holder, int position) {

        Book b = BookStorage.currentbook.get(position);

        holder.btvTitle.setText(b.getKosdata().getName());
        holder.btvPrice.setText("Rp "+b.getKosdata().getPrice()+"/Bulan");
        holder.btvKodeBook.setText("Kode Book: "+b.getBookingID()+b.getBookingDate());
        holder.bpicture.setImageResource(b.getKosdata().getPic());
        holder.btvKosId.setText("Kode Kos: "+b.getKosdata().getID());

    }

    @Override
    public int getItemCount() {
        return BookStorage.currentbook.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {

        TextView btvTitle, btvPrice, btvKodeBook, btvKosId;
        ImageView bpicture;
        OnBookListener onBookListener;

        public ViewHolder(@NonNull View itemView, OnBookListener onBookListener) {
            super(itemView);
            btvKosId = itemView.findViewById(R.id.btv_kosid);
            btvTitle = itemView.findViewById(R.id.btv_name);
            btvPrice = itemView.findViewById(R.id.btv_price);
            btvKodeBook = itemView.findViewById(R.id.btv_ID);
            bpicture = itemView.findViewById(R.id.bpicture);
            this.onBookListener = onBookListener;
            itemView.setOnCreateContextMenuListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        onBookListener.onBookClick(getAdapterPosition());
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Cancel Book?");
            contextMenu.add("Yes");
        }
    }

    public interface OnBookListener{
    void onBookClick(int position);

    }


}
