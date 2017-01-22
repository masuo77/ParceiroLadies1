package com.example.masuo.parceiroladiesrespectbook;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.R.attr.delay;


/**
 * Created by Masuo on 2017/01/04.
 */

public class SeasonListRecyclerAdapter extends RecyclerView.Adapter<SeasonListRecyclerAdapter.CustomViewHolder> {
    private Context context;
    private List<SeasonListItem> seasonListItems;
    private onItemClickListener listener;
    private onImageButtonInfoClickListener onImageButtonInfoClickListener;

    public SeasonListRecyclerAdapter(Context context, List<SeasonListItem> seasonListItems) {
        this.context = context;
        this.seasonListItems = seasonListItems;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_season_list, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {
        int year = seasonListItems.get(position).getYear();
        String league = seasonListItems.get(position).getLeague();
        String rank = seasonListItems.get(position).getRank();
        String slogan = seasonListItems.get(position).getSlogan();

        holder.textViewYear.setText(String.valueOf(year));
        holder.textViewLeague.setText(league);
        holder.textViewRank.setText(rank);
        holder.textViewSlogan.setText("「"+ slogan + "」");

        // Itemクリック
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listener.onClick(view, holder.getAdapterPosition(), seasonListItems.get(holder.getAdapterPosition()).getYear());
                    }
                }, 200);
            }
        });


        // Buttonクリック
        holder.imageButtonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onImageButtonInfoClickListener.onClick(v, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return seasonListItems.size();
    }

    // Itemクリック
    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public interface onItemClickListener {
        void onClick(View view, int position, int year);
    }

    // Buttonクリック
    public void setOnImageButtonInfoClickListener(onImageButtonInfoClickListener listener) {
        this.onImageButtonInfoClickListener = listener;
    }

    public interface onImageButtonInfoClickListener {
        void onClick(View view, int position);
    }

    // ここでViewの中の要素を登録する。

    public class CustomViewHolder extends RecyclerView.ViewHolder{
        final CardView linearLayout;
        final TextView textViewYear;
        final TextView textViewLeague;
        final TextView textViewRank;
        final TextView textViewSlogan;
        final ImageButton imageButtonInfo;

        public CustomViewHolder(View itemView) {
            super(itemView);

            linearLayout = (CardView) itemView.findViewById(R.id.card_view_season_list);
            textViewYear = (TextView) itemView.findViewById(R.id.text_view_season);
            textViewLeague = (TextView) itemView.findViewById(R.id.text_view_league);
            textViewRank = (TextView) itemView.findViewById(R.id.text_view_rank);
            textViewSlogan = (TextView) itemView.findViewById(R.id.text_view_slogan);
            imageButtonInfo = (ImageButton) itemView.findViewById(R.id.image_button_season_info);
        }

    }
}
