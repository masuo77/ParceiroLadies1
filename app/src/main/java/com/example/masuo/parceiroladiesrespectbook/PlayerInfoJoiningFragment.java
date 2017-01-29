package com.example.masuo.parceiroladiesrespectbook;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlayerInfoJoiningFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlayerInfoJoiningFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerInfoJoiningFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    private PlayerInfoItem playerInfoItem;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PlayerInfoJoiningFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayerInfoJoiningFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayerInfoJoiningFragment newInstance(PlayerInfoItem param1, String param2) {
        PlayerInfoJoiningFragment fragment = new PlayerInfoJoiningFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            playerInfoItem = (PlayerInfoItem)getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_player_info_joining, container, false);

        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.player_info_joining_layout);

        PlayerInfoJoiningFragment.CustomViewHolder holder = new PlayerInfoJoiningFragment.CustomViewHolder(linearLayout);

        PlayerInfoItem item = playerInfoItem;

//        String season_note = item.getSeason_note();
        String joining_season = item.getJoining_season() + "シーズン";
        String joining_announced_at = item.getJoining_announced_at();

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date = format.parse(joining_announced_at);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年M月d日");
            String str2 = sdf1.format(date);
            joining_announced_at = str2;
        }
        catch (ParseException e)
        {
        }

        String joining_comment = item.getJoining_comment();
        String joining_note = item.getJoining_note();
//        holder.season_note.setText(season_note);
        holder.joining_season.setText(joining_season);
        holder.joining_announced_at.setText(joining_announced_at);
        holder.joining_comment.setText(joining_comment);
        holder.joining_note.setText(joining_note);
        if (TextUtils.isEmpty(joining_note))
        {
            holder.joining_note_label.setText("");
        }

        // Inflate the layout for this fragment
        return v;
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
//        final TextView season_note;

        final TextView joining_season;
        final TextView joining_announced_at;
        final TextView joining_comment;
        final TextView joining_note;

        final TextView joining_note_label;


        public CustomViewHolder(LinearLayout layout) {

//            super(itemView);
//            season_note = (TextView) layout.findViewById(R.id.text_view_player_info_season_note);
            joining_season = (TextView) layout.findViewById(R.id.text_view_player_info_joining_season);
            joining_announced_at = (TextView) layout.findViewById(R.id.text_view_player_info_joining_announced_at);
            joining_comment = (TextView) layout.findViewById(R.id.text_view_player_info_joining_comment);
            joining_note = (TextView) layout.findViewById(R.id.text_view_player_info_joining_note);

            joining_note_label = (TextView) layout.findViewById(R.id.tv_joining_note_label);

        }
    }

}
