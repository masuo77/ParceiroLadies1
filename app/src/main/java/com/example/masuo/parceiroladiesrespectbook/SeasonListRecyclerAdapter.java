package com.example.masuo.parceiroladiesrespectbook;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


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

        SeasonListItem item = seasonListItems.get(position);
        if (item != null) {
            holder.bind(context, item, position);
        }

//        String year = seasonListItems.get(position).getYear();
//        String league = seasonListItems.get(position).getLeague();
//        String rank = seasonListItems.get(position).getRank();
//        String slogan = seasonListItems.get(position).getSlogan();
//
//        holder.textViewYear.setText(year);
//        holder.textViewLeague.setText(league);
//        holder.textViewRank.setText(rank);
//        holder.textViewSlogan.setText("「" + slogan + "」");
//        if (position == 0) {
//            holder.linearLayout.getLayoutParams().height = (int) context.getResources().getDimension(R.dimen.seasonview_height);
//            holder.linearLayout.requestLayout();
//            holder.textViewLeague.setTextSize(18.0f);
//            holder.textViewSlogan.setTextSize(18.0f);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                int color = context.getResources().getColor(R.color.colorControlHighlight, context.getTheme());
//                holder.linearLayout.setBackgroundColor(color);
//            } else {
//                int color = context.getResources().getColor(R.color.colorControlHighlight);
//                holder.linearLayout.setBackgroundColor(color);
//            }
//        }

        // Itemクリック
        holder.linearLayout.setOnClickListener(new View.OnClickListener()

                                               {
                                                   @Override
                                                   public void onClick(final View view) {
                                                       Handler handler = new Handler();
                                                       handler.postDelayed(new Runnable() {
                                                           @Override
                                                           public void run() {
                                                               listener.onClick(view, holder.getAdapterPosition(), seasonListItems.get(holder.getAdapterPosition()).getYear());
                                                           }
                                                       }, 240);
                                                   }
                                               }

        );


//        // Buttonクリック
//        holder.imageButtonInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onImageButtonInfoClickListener.onClick(v, holder.getAdapterPosition());
//            }
//        });
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
        void onClick(View view, int position, String year);
    }

    // Buttonクリック
    public void setOnImageButtonInfoClickListener(onImageButtonInfoClickListener listener) {
        this.onImageButtonInfoClickListener = listener;
    }

    public interface onImageButtonInfoClickListener {
        void onClick(View view, int position);
    }

// ここでViewの中の要素を登録する。

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        final CardView linearLayout;
        final TextView textViewYear;
        final TextView textViewLeague;
        final TextView textViewRank;
        final TextView textViewSlogan;
//        final ImageButton imageButtonInfo;

        public CustomViewHolder(View itemView) {
            super(itemView);

            linearLayout = (CardView) itemView.findViewById(R.id.card_view_season_list);
            textViewYear = (TextView) itemView.findViewById(R.id.text_view_season);
            textViewLeague = (TextView) itemView.findViewById(R.id.text_view_league);
            textViewRank = (TextView) itemView.findViewById(R.id.text_view_rank);
            textViewSlogan = (TextView) itemView.findViewById(R.id.text_view_slogan);
//            imageButtonInfo = (ImageButton) itemView.findViewById(R.id.image_button_season_info);
        }

        public void bind(Context context, @NonNull SeasonListItem seasonListItem, int position) {
            String year = seasonListItem.getYear();
            String league = seasonListItem.getLeague();
            String rank = seasonListItem.getRank();
            String slogan = seasonListItem.getSlogan();

            textViewYear.setText(year);
            textViewLeague.setText(league);
            textViewRank.setText(rank);
            textViewSlogan.setText("「" + slogan + "」");

            if (position == 0) {
                linearLayout.getLayoutParams().height = (int) context.getResources().getDimension(R.dimen.seasonview_height);
                linearLayout.requestLayout();
                textViewLeague.setTextSize(18.0f);
                textViewSlogan.setTextSize(18.0f);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int color = context.getResources().getColor(R.color.colorControlHighlight, context.getTheme());
                    linearLayout.setBackgroundColor(color);
                } else {
                    int color = context.getResources().getColor(R.color.colorControlHighlight);
                    linearLayout.setBackgroundColor(color);
                }
            }
        }
    }
}
