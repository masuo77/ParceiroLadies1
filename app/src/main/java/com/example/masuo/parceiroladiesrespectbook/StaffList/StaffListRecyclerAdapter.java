package com.example.masuo.parceiroladiesrespectbook.StaffList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.masuo.parceiroladiesrespectbook.R;
import com.example.masuo.parceiroladiesrespectbook.SeasonList.SeasonListItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Masuo on 2017/01/04.
 */

public class StaffListRecyclerAdapter
        extends RecyclerView.Adapter<StaffListRecyclerAdapter.CustomViewHolder> {
    private static final String LOG = "SRecyclerAdapter";

    private Context context;
    private SeasonListItem seasonListItem;
    private List<StaffListItem> staffListItems;
    private onItemClickListener listener;

    public StaffListRecyclerAdapter(Context context,
                                    List<StaffListItem> staffListItems) {
        this.context = context;
        this.staffListItems = staffListItems;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_staff_list, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {

        StaffListItem item = staffListItems.get(position);
        if (item != null) {
            holder.bind(context, item);
        }

        // Itemクリック
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // 行のクリック
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listener.onClick(view,
                                holder.getAdapterPosition(),
                                staffListItems.get(holder.getAdapterPosition()).getId(),
                                staffListItems.get(holder.getAdapterPosition()).getName());
                    }
                }, 240);
            }
        });
    }

    @Override
    public int getItemCount() {
        return staffListItems.size();
    }

    // Itemクリック
    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public interface onItemClickListener {
        void onClick(View view, int position, String id, String name);
    }

// ここでViewの中の要素を登録する。

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        final int id;
        final CardView linearLayout;
        final ImageView imageViewFace;
        final TextView textViewName;
        final TextView textViewPost;

        final TextView textViewNote;
        final TextView textViewJoin;
        final TextView textViewLeaving;

        public CustomViewHolder(View itemView) {
            super(itemView);
            id = 0;
            linearLayout = (CardView) itemView.findViewById(R.id.card_view_player_list);
            imageViewFace = (ImageView) itemView.findViewById(R.id.faceImageView);
            textViewName = (TextView) itemView.findViewById(R.id.list_item_name);
            textViewPost = (TextView) itemView.findViewById(R.id.list_item_post);

            textViewNote = (TextView) itemView.findViewById(R.id.list_item_note);
            textViewJoin = (TextView) itemView.findViewById(R.id.list_item_join);
            textViewLeaving = (TextView) itemView.findViewById(R.id.list_item_leaving);

        }

        public void bind(Context context, @NonNull StaffListItem item) {
            String name = item.getName();
            String post = item.getPost();

            if (!TextUtils.isEmpty(item.getFace())) {
                try {
                    InputStream istream = context.getResources().getAssets().open("face/" + item.getFace());
                    Bitmap bitmap = BitmapFactory.decodeStream(istream);
                    imageViewFace.setImageBitmap(bitmap);
                    imageViewFace.setAlpha(1f);
                } catch (IOException e) {
                    Log.d("Assets", "Error");
                    imageViewFace.setImageResource(R.drawable.ic_account_circle_black_48dp);
                    imageViewFace.setAlpha(0.26f);
                }
            } else {
                imageViewFace.setImageResource(R.drawable.ic_account_circle_black_48dp);
                imageViewFace.setAlpha(0.26f);
            }

            textViewName.setText(name);
            textViewPost.setText(post);

            textViewNote.setText("");
            textViewJoin.setText("");
            textViewLeaving.setText("");


        }

    }

}
