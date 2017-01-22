package com.example.masuo.parceiroladiesrespectbook;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlayerInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlayerInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerInfoFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameteidrs
    private int mSeason;
    private int mId;

    private OnFragmentInteractionListener mListener;

    public PlayerInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayerInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayerInfoFragment newInstance(int param1, int param2) {
        PlayerInfoFragment fragment = new PlayerInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSeason = getArguments().getInt(ARG_PARAM1);
            mId = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_player_info, container, false);

        createPlayerInfo(v);


        return v;
    }

    private void createPlayerInfo(View v) {
        Context context = v.getContext();

//        LinearLayout cardLinear = (LinearLayout)v.findViewById(R.id.card_view_player_info);
//        cardLinear.removeAllViews();
//
//        LayoutInflater inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.player_info_frame_layout);
//        CardView cardView = (CardView) linearLayout.findViewById(R.id.card_view_player_info);

        ParceiroDBAdapter parceiroDBAdapter = new ParceiroDBAdapter(context);

        parceiroDBAdapter.open();

        Cursor c = parceiroDBAdapter.getPlayer(mSeason, mId);

        CustomViewHolder holder = new CustomViewHolder(linearLayout);

        if (c.moveToFirst()) {
//            do {
//            c.move(mId);

            PlayerInfoItem item = new PlayerInfoItem();
//                item.setImageRes(R.mipmap.ic_launcher);

            item.setName(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NAME)));
            item.setYomi(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_YOMI)));
            item.setYomi_j(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_YOMI_J)));
            item.setBirthday(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_BIRTHDAY)));
            item.setHeight(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_HEIGHT)));
            item.setWeight(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_WEIGHT)));
            item.setBlood(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_BLOOD)));
            item.setHome(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_HOMETOWN)));
            item.setCareer(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_CAREER)));
            item.setNumber(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NUMBER)));
            item.setPosition(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_POSITION)));
            item.setSeason_note(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NOTE)));
            item.setJoining_season(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_JOINING_SEASON)));
            item.setJoining_announced_at(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_JOINING_ANNOUNCED_AT)));
            item.setJoining_comment(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_JOINING_COMMENT)));
            item.setJoining_note(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_JOINING_NOTE)));
            item.setLeaving_season(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_LEAVING_SEASON)));
            item.setLeaving_announced_at(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_LEAVING_ANNOUNCED_AT)));
            item.setLeaving_comment(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_LEAVING_COMMENT)));
            item.setLeaving_note(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_LEAVING_NOTE)));
            item.setAfter_leaving(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_AFTER_LEAVING)));

            String name = item.getName();
            String yomi = item.getYomi();
            String yomi_j = item.getYomi_j();
            String birthday = item.getBirthday();
            String height = item.getHeight();
            String weight = item.getWeight();
            String blood = item.getBlood();
            String home = item.getHome();
            String career = item.getCareer();
            String number = item.getNumber();
            String position2 = item.getPosition();
            String season_note = item.getSeason_note();
            String joining_season = item.getJoining_season();
            String joining_announced_at = item.getJoining_announced_at();
            String joining_comment = item.getJoining_comment();
            String joining_note = item.getJoining_note();
            String leaving_season = item.getLeaving_season();
            String leaving_announced_at = item.getLeaving_announced_at();
            String leaving_comment = item.getLeaving_comment();
            String leaving_note = item.getLeaving_note();
            String after_leaving = item.getAfter_leaving();

            holder.name.setText(name);
            holder.yomi.setText(yomi);
            holder.yomi_j.setText(yomi_j);
            holder.birthday.setText(birthday);
            holder.height.setText(height);
            holder.weight.setText(weight);
            holder.blood.setText(blood);
            holder.home.setText(home);
            holder.career.setText(career);
            holder.number.setText(number);
            holder.position.setText(position2);
            holder.season_note.setText(season_note);
            holder.joining_season.setText(joining_season);
            holder.joining_announced_at.setText(joining_announced_at);
            holder.joining_comment.setText(joining_comment);
            holder.joining_note.setText(joining_note);
            holder.leaving_season.setText(leaving_season);
            holder.leaving_announced_at.setText(leaving_announced_at);
            holder.leaving_comment.setText(leaving_comment);
            holder.leaving_note.setText(leaving_note);
            holder.after_leaving.setText(after_leaving);

        }

        c.close();
        parceiroDBAdapter.close();

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    static class CustomViewHolder {
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

        public CustomViewHolder(LinearLayout layout) {

//            super(itemView);
            linearLayout = (CardView) layout.findViewById(R.id.card_view_player_info);
            name = (TextView) layout.findViewById(R.id.text_view_player_info_name);
            yomi = (TextView) layout.findViewById(R.id.text_view_player_info_yomi);
            yomi_j = (TextView) layout.findViewById(R.id.text_view_player_info_yomi_j);
            birthday = (TextView) layout.findViewById(R.id.text_view_player_info_birthday);
            height = (TextView) layout.findViewById(R.id.text_view_player_info_height);
            weight = (TextView) layout.findViewById(R.id.text_view_player_info_weight);
            blood = (TextView) layout.findViewById(R.id.text_view_player_info_blood);
            home = (TextView) layout.findViewById(R.id.text_view_player_info_home);
            career = (TextView) layout.findViewById(R.id.text_view_player_info_career);

            number = (TextView) layout.findViewById(R.id.text_view_player_info_number);
            position = (TextView) layout.findViewById(R.id.text_view_player_info_position);
            season_note = (TextView) layout.findViewById(R.id.text_view_player_info_season_note);
            joining_season = (TextView) layout.findViewById(R.id.text_view_player_info_joining_season);
            joining_announced_at = (TextView) layout.findViewById(R.id.text_view_player_info_joining_announced_at);
            joining_comment = (TextView) layout.findViewById(R.id.text_view_player_info_joining_comment);
            joining_note = (TextView) layout.findViewById(R.id.text_view_player_info_joining_note);
            leaving_season = (TextView) layout.findViewById(R.id.text_view_player_info_leaving_season);
            leaving_announced_at = (TextView) layout.findViewById(R.id.text_view_player_info_leaving_announced_at);
            leaving_comment = (TextView) layout.findViewById(R.id.text_view_player_info_leaving_comment);
            leaving_note = (TextView) layout.findViewById(R.id.text_view_player_info_leaving_note);
            after_leaving = (TextView) layout.findViewById(R.id.text_view_player_info_after_leaving);
        }
    }

}
