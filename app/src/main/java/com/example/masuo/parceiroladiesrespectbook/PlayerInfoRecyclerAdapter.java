package com.example.masuo.parceiroladiesrespectbook;

import android.content.Context;
import android.graphics.Typeface;
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

public class PlayerInfoRecyclerAdapter extends RecyclerView.Adapter<PlayerInfoRecyclerAdapter.CustomViewHolder> {
    private Context context;
    private List<PlayerInfoItem> playerInfoItems;
    private onItemClickListener listener;
    private onImageButtonInfoClickListener onImageButtonInfoClickListener;

    public PlayerInfoRecyclerAdapter(Context context, List<PlayerInfoItem> playerInfoItems) {
        this.context = context;
        this.playerInfoItems = playerInfoItems;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_player_info, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {

        String name = playerInfoItems.get(position).getName();
        String yomi = playerInfoItems.get(position).getYomi();
        String yomi_j = playerInfoItems.get(position).getYomi_j();
        String birthday = playerInfoItems.get(position).getBirthday();
        String height = playerInfoItems.get(position).getHeight();
        String weight = playerInfoItems.get(position).getWeigth();
        String blood = playerInfoItems.get(position).getBlood();
        String career = playerInfoItems.get(position).getCareer();
        String number = playerInfoItems.get(position).getNumber();
        String position2 = playerInfoItems.get(position).getPosition();
        String season_note = playerInfoItems.get(position).getSeason_note();
        String joining_season = playerInfoItems.get(position).getJoining_season();
        String joining_announced_at = playerInfoItems.get(position).getJoining_announced_at();
        String joining_comment = playerInfoItems.get(position).getJoining_comment();
        String leaving_season = playerInfoItems.get(position).getLeaving_season();
        String leaving_announced_at = playerInfoItems.get(position).getLeaving_announced_at();
        String leaving_comment = playerInfoItems.get(position).getLeaving_comment();
        String leaving_note = playerInfoItems.get(position).getLeaving_note();
        String after_leaving = playerInfoItems.get(position).getAfter_leaving();

        holder.name.setText(name);
        holder.yomi.setText(yomi);
        holder.yomi_j.setText(yomi_j);
        holder.birthday.setText(birthday);
        holder.height.setText(height);
        holder.weight.setText(weight);
        holder.blood.setText(blood);
        holder.career.setText(career);
        holder.number.setText(number);
        holder.position.setText(position2);
        holder.season_note.setText(season_note);
        holder.joining_season.setText(joining_season);
        holder.joining_announced_at.setText(joining_announced_at);
        holder.joining_comment.setText(joining_comment);
        holder.leaving_season.setText(leaving_season);
        holder.leaving_announced_at.setText(leaving_announced_at);
        holder.leaving_comment.setText(leaving_comment);
        holder.leaving_note.setText(leaving_note);
        holder.after_leaving.setText(after_leaving);
//
//        holder.textViewNumber.setText(number);
//        Typeface face = Typeface.createFromAsset(context.getAssets(), "ParNum2016.ttf");
//        holder.textViewNumber.setTypeface(face);
//

//        // Itemクリック
//        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.onClick(view, holder.getAdapterPosition(), playerInfoItems.get(holder.getAdapterPosition()).getName());
//            }
//        });
//
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
        return playerInfoItems.size();
    }

    // Itemクリック
    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public interface onItemClickListener {
        void onClick(View view, int position, String name);
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
        final CardView linearLayout;
        final TextView name;
        final TextView yomi;
        final TextView yomi_j;
        final TextView birthday;
        final TextView height;
        final TextView weight;
        final TextView blood;

        final TextView home;
        final TextView career;

        final TextView number;
        final TextView position;
        final TextView season_note;

        final TextView joining_season;
        final TextView joining_announced_at;
        final TextView joining_comment;
        final TextView joining_note;

        final TextView leaving_season;
        final TextView leaving_announced_at;
        final TextView leaving_comment;
        final TextView leaving_note;
        final TextView after_leaving;

        public CustomViewHolder(View itemView) {
            super(itemView);
            linearLayout = (CardView) itemView.findViewById(R.id.card_view_player_info);
            name = (TextView) itemView.findViewById(R.id.text_view_player_info_name);
            yomi = (TextView) itemView.findViewById(R.id.text_view_player_info_yomi);
            yomi_j = (TextView) itemView.findViewById(R.id.text_view_player_info_yomi_j);
            birthday = (TextView) itemView.findViewById(R.id.text_view_player_info_birthday);
            height = (TextView) itemView.findViewById(R.id.text_view_player_info_height);
            weight = (TextView) itemView.findViewById(R.id.text_view_player_info_weight);
            blood = (TextView) itemView.findViewById(R.id.text_view_player_info_blood);
            home = (TextView) itemView.findViewById(R.id.text_view_player_info_home);
            career = (TextView) itemView.findViewById(R.id.text_view_player_info_career);

            number = (TextView) itemView.findViewById(R.id.text_view_player_info_number);
            position = (TextView) itemView.findViewById(R.id.text_view_player_info_position);
            season_note = (TextView) itemView.findViewById(R.id.text_view_player_info_season_note);
            joining_season = (TextView) itemView.findViewById(R.id.text_view_player_info_joining_season);
            joining_announced_at = (TextView) itemView.findViewById(R.id.text_view_player_info_joining_announced_at);
            joining_comment = (TextView) itemView.findViewById(R.id.text_view_player_info_joining_comment);
            joining_note = (TextView) itemView.findViewById(R.id.text_view_player_info_joining_note);
            leaving_season = (TextView) itemView.findViewById(R.id.text_view_player_info_leaving_season);
            leaving_announced_at = (TextView) itemView.findViewById(R.id.text_view_player_info_leaving_announced_at);
            leaving_comment = (TextView) itemView.findViewById(R.id.text_view_player_info_leaving_comment);
            leaving_note = (TextView) itemView.findViewById(R.id.text_view_player_info_leaving_note);
            after_leaving = (TextView) itemView.findViewById(R.id.text_view_player_info_after_leaving);
        }
    }
}
