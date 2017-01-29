package com.example.masuo.parceiroladiesrespectbook;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Masuo on 2017/01/04.
 */

public class PlayerListRecyclerAdapter
        extends RecyclerView.Adapter<PlayerListRecyclerAdapter.CustomViewHolder> {
    private static final String LOG = "PRecyclerAdapter";

    private Context context;
    private List<PlayerListItem> playerListItems;
    private onItemClickListener listener;

    public PlayerListRecyclerAdapter(Context context,
                                     List<PlayerListItem> playerListItems) {
        this.context = context;
        this.playerListItems = playerListItems;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_player_list, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {

        PlayerListItem item = playerListItems.get(position);
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
                                playerListItems.get(holder.getAdapterPosition()).getId(),
                                playerListItems.get(holder.getAdapterPosition()).getName());
                    }
                }, 240);
            }
        });
    }

    @Override
    public int getItemCount() {
        return playerListItems.size();
    }

    // Itemクリック
    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public interface onItemClickListener {
        void onClick(View view, int position, int id, String name);
    }

// ここでViewの中の要素を登録する。

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        final int id;
        final CardView linearLayout;
        final TextView textViewName;
        final TextView textViewNumber;
        final TextView textViewPosition;
        final TextView textViewNote;
        final TextView textViewJoin;
        final TextView textViewLeaving;
        final ImageButton imageButtonInfo;

        public CustomViewHolder(View itemView) {
            super(itemView);
            id = 0;
            linearLayout = (CardView) itemView.findViewById(R.id.card_view_player_list);
            textViewName = (TextView) itemView.findViewById(R.id.list_item_name);
            textViewNumber = (TextView) itemView.findViewById(R.id.list_item_number);
            textViewPosition = (TextView) itemView.findViewById(R.id.list_item_position);
            textViewNote = (TextView) itemView.findViewById(R.id.list_item_note);
            textViewJoin = (TextView) itemView.findViewById(R.id.list_item_join);
            textViewLeaving = (TextView) itemView.findViewById(R.id.list_item_leaving);
            imageButtonInfo = (ImageButton) itemView.findViewById(R.id.image_button_season_info);
        }

        public void bind(Context context, @NonNull PlayerListItem item) {
            String name = item.getName();
            String number = item.getNumber();
            String pos = item.getPosition();
            String note = item.getNote();
            String join = item.getJoin();
            String leaving = item.getLeaving();

            textViewName.setText(name);
            textViewNumber.setText(number);
            Typeface face = Typeface.createFromAsset(context.getAssets(), "ParNum2016.ttf");
            textViewNumber.setTypeface(face);
            textViewPosition.setText(pos);
            textViewNote.setText(note);
            textViewJoin.setText(join);
            textViewLeaving.setText(leaving);
        }

    }

}
