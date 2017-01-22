package com.example.masuo.parceiroladiesrespectbook;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import android.support.v7.widget.RecyclerView;


/**
 * Created by Masuo on 2017/01/04.
 */

public class PlayerListRecyclerAdapter extends RecyclerView.Adapter<PlayerListRecyclerAdapter.CustomViewHolder> {
    private Context context;
    private List<PlayerListItem> playerListItems;
    private onItemClickListener listener;
    private onImageButtonInfoClickListener onImageButtonInfoClickListener;

    public PlayerListRecyclerAdapter(Context context, List<PlayerListItem> playerListItems) {
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
        String name = playerListItems.get(position).getName();
        String number = playerListItems.get(position).getNumber();
        String pos = playerListItems.get(position).getPosition();
        String note = playerListItems.get(position).getNote();
        String join = playerListItems.get(position).getJoin();
        String leaving = playerListItems.get(position).getLeaving();
        holder.textViewName.setText(name);

        holder.textViewNumber.setText(number);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "ParNum2016.ttf");
        holder.textViewNumber.setTypeface(face);

        holder.textViewPosition.setText(pos);
        holder.textViewNote.setText(note);
        holder.textViewJoin.setText(join);
        holder.textViewLeaving.setText(leaving);

        // Itemクリック
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 行のクリック
                int id = playerListItems.get(holder.getAdapterPosition()).getId();
                String name = playerListItems.get(holder.getAdapterPosition()).getName();
                listener.onClick(view, holder.getAdapterPosition(), id, name);
            }
        });

        // Buttonクリック
        holder.imageButtonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = playerListItems.get(holder.getAdapterPosition()).getId();
                onImageButtonInfoClickListener.onClick(v, id);
//                onImageButtonInfoClickListener.onClick(v, holder.getAdapterPosition());
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

    // Buttonクリック
    public void setOnImageButtonInfoClickListener(onImageButtonInfoClickListener listener) {
        this.onImageButtonInfoClickListener = listener;
    }

    public interface onImageButtonInfoClickListener {
        void onClick(View view, int position);
    }

    // ここでViewの中の要素を登録する。

    static class CustomViewHolder extends RecyclerView.ViewHolder {
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
    }
}
