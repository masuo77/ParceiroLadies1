package com.example.masuo.parceiroladiesrespectbook.SeasonList;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.masuo.parceiroladiesrespectbook.R;

import java.util.List;


/**
 * Created by Masuo on 2017/01/04.
 */

//public class SeasonListRecyclerAdapter extends RecyclerView.Adapter<SeasonListRecyclerAdapter.CustomViewHolder> {
public class SeasonListRecyclerAdapter2 extends RecyclerView.Adapter<SeasonListRecyclerAdapter2.ViewHolder> {
    private static final String LOG = "SeasonListRecycler";
    private Context context;
    private List<SeasonListItem> seasonListItems;
    private onItemClickListener listener;

    public SeasonListRecyclerAdapter2(Context context, List<SeasonListItem> seasonListItems) {
        this.context = context;
        this.seasonListItems = seasonListItems;
        Log.i(LOG, "SeasonListRecyclerAdapter2");

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(final View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(final View view) {
                                                // 行のクリック
                                                if (!isClickEvent()) {
                                                    return;
                                                }
                                                Handler handler = new Handler();
                                                handler.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        listener.onClick(view, getAdapterPosition(),
                                                                seasonListItems.get(getAdapterPosition()).getYear(),
                                                                seasonListItems.get(getAdapterPosition()).getLeague(),
                                                                seasonListItems.get(getAdapterPosition()).getSlogan());
                                                    }
                                                }, 200);
                                            }
                                        }
            );

        }
    }

    /**
     * クリック連打制御時間(ミリ秒)
     */
    private static final long CLICK_DELAY = 1000;
    /**
     * 前回のクリックイベント実行時間
     */
    private static long mOldClickTime;

    /**
     * クリックイベントが実行可能か判断する。
     *
     * @return クリックイベントの実行可否 (true:可, false:否)
     */
    public static boolean isClickEvent() {
        // 現在時間を取得する
        long time = System.currentTimeMillis();

        // 一定時間経過していなければクリックイベント実行不可
        if (time - mOldClickTime < CLICK_DELAY) {
            return false;
        }

        // 一定時間経過したらクリックイベント実行可能
        mOldClickTime = time;
        return true;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        ViewHolder viewHolder = null;
        Log.d(LOG, "onCreateViewHolder " + viewType);
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.card_view_season_list_top, parent, false);
                viewHolder = new TopViewHolder(view);
                break;

            case 1:
            default:
                view = LayoutInflater.from(context).inflate(R.layout.card_view_season_list, parent, false);
                viewHolder = new CustomViewHolder(view);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        SeasonListItem item = seasonListItems.get(position);
        if (item == null) {
            return;
        }

        // Itemクリック
        switch (position) {
            case 0:
                ((TopViewHolder) holder).bind(context, item, position);
                break;

            case 1:
            default:
                ((CustomViewHolder) holder).bind(context, item, position);
                break;
        }
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
        void onClick(View view, int position, String year, String league, String slogan);
    }

//    // Buttonクリック
//    public void setOnImageButtonInfoClickListener(onImageButtonInfoClickListener listener) {
//        this.onImageButtonInfoClickListener = listener;
//    }

//    public interface onImageButtonInfoClickListener {
//        void onClick(View view, int position);
//    }

// ここでViewの中の要素を登録する。

    public class CustomViewHolder extends ViewHolder {
        final CardView linearLayout;
        final TextView textViewYear;
        final TextView textViewLeague;
        final TextView textViewRank;
        final TextView textViewSlogan;

        public CustomViewHolder(View itemView) {
            super(itemView);

            linearLayout = (CardView) itemView.findViewById(R.id.card_view_season_list);
            textViewYear = (TextView) itemView.findViewById(R.id.text_view_season);
            textViewLeague = (TextView) itemView.findViewById(R.id.text_view_league);
            textViewRank = (TextView) itemView.findViewById(R.id.text_view_rank);
            textViewSlogan = (TextView) itemView.findViewById(R.id.text_view_slogan);
        }

        @SuppressWarnings("deprecation")
        public void bind(Context context, @NonNull SeasonListItem seasonListItem, int position) {
            String year = seasonListItem.getYear();
            String league = seasonListItem.getLeague();
            String rank = seasonListItem.getRank();
            String slogan = seasonListItem.getSlogan();

            textViewYear.setText(year);
            textViewLeague.setText(league);
            textViewRank.setText(rank);
            textViewSlogan.setText(slogan);
        }
    }

    public class TopViewHolder extends ViewHolder {

        public static final int LAYOUT_ID = R.layout.card_view_season_list_top;

        final CardView linearLayout;
        final TextView textViewYear;
        final TextView textViewLeague;
        final TextView textViewRank;
        final TextView textViewSlogan;
        final TextView textViewChant;
        final TextView textViewChantInfo;

        public TopViewHolder(View itemView) {
            super(itemView);

            linearLayout = (CardView) itemView.findViewById(R.id.card_view_season_list_top);
            textViewYear = (TextView) itemView.findViewById(R.id.text_view_season);
            textViewLeague = (TextView) itemView.findViewById(R.id.text_view_league);
            textViewRank = (TextView) itemView.findViewById(R.id.text_view_rank);
            textViewSlogan = (TextView) itemView.findViewById(R.id.text_view_slogan);
            textViewChant = (TextView) itemView.findViewById(R.id.text_view_chant);
            textViewChantInfo = (TextView) itemView.findViewById(R.id.text_view_chant_readme);
        }

        public void bind(Context context, @NonNull SeasonListItem seasonListItem, int position) {
            String year = seasonListItem.getYear();
            String league = seasonListItem.getLeague();
            String rank = seasonListItem.getRank();
            String slogan = seasonListItem.getSlogan();

            textViewYear.setText(year);
            textViewLeague.setText(league);
            textViewRank.setText(rank);
            textViewSlogan.setText(slogan);

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                int color = context.getResources().getColor(R.color.colorControlHighlight, context.getTheme());
//                linearLayout.setBackgroundColor(color);
//            } else {
//                @SuppressWarnings("noinspection deprecation")
//                int color = context.getResources().getColor(R.color.colorControlHighlight);
//                linearLayout.setBackgroundColor(color);
//            }
        }

    }
}
