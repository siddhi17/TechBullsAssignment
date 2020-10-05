package com.example.techbullsassignment.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.techbullsassignment.Models.MovieItem;
import com.example.techbullsassignment.R;

public class ItemAdapter extends PagedListAdapter<MovieItem, ItemAdapter.ItemViewHolder> {

    private Context mCtx;

    public ItemAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.list_movies, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        MovieItem item = getItem(position);

        if (item != null) {
            holder.textView.setText(item.Title);
            Glide.with(mCtx)
                    .load(item.Poster)
                    .into(holder.imageView);
        }else{
            Toast.makeText(mCtx, "Item is null", Toast.LENGTH_LONG).show();
        }
    }

    private static DiffUtil.ItemCallback<MovieItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieItem>() {
                @Override
                public boolean areItemsTheSame(MovieItem oldItem, MovieItem newItem) {
                    return oldItem.imdbID == newItem.imdbID;
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(MovieItem oldItem, MovieItem newItem) {
                    return oldItem.equals(newItem);
                }
            };

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewName);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}