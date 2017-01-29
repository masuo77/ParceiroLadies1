package com.example.masuo.parceiroladiesrespectbook;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
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
 * {@link PlayerInfoLeavingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlayerInfoLeavingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerInfoLeavingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private PlayerInfoItem playerInfoItem;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PlayerInfoLeavingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayerInfoLeavingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayerInfoLeavingFragment newInstance(PlayerInfoItem param1, String param2) {
        PlayerInfoLeavingFragment fragment = new PlayerInfoLeavingFragment();
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

        View v = inflater.inflate(R.layout.fragment_player_info_leaving, container, false);

        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.player_info_leaving_layout);

        PlayerInfoLeavingFragment.CustomViewHolder holder = new PlayerInfoLeavingFragment.CustomViewHolder(linearLayout);

        PlayerInfoItem item = playerInfoItem;

        String leaving_season = item.getLeaving_season() + "シーズン";
        String leaving_announced_at = item.getLeaving_announced_at();

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date = format.parse(leaving_announced_at);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年M月d日");
            String str2 = sdf1.format(date);
            leaving_announced_at = str2;
        }
        catch (ParseException e)
        {
        }

        String leaving_comment = item.getLeaving_comment();
        String leaving_note = item.getLeaving_note();
        String after_leaving = item.getAfter_leaving();

        holder.leaving_season.setText(leaving_season);
        holder.leaving_announced_at.setText(leaving_announced_at);
        holder.leaving_comment.setText(leaving_comment);
        holder.leaving_note.setText(leaving_note);
        holder.after_leaving.setText(after_leaving);

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
        final TextView leaving_season;
        final TextView leaving_announced_at;
        final TextView leaving_comment;
        final TextView leaving_note;
        final TextView after_leaving;

        public CustomViewHolder(LinearLayout layout) {
            leaving_season = (TextView) layout.findViewById(R.id.text_view_player_info_leaving_season);
            leaving_announced_at = (TextView) layout.findViewById(R.id.text_view_player_info_leaving_announced_at);
            leaving_comment = (TextView) layout.findViewById(R.id.text_view_player_info_leaving_comment);
            leaving_note = (TextView) layout.findViewById(R.id.text_view_player_info_leaving_note);
            after_leaving = (TextView) layout.findViewById(R.id.text_view_player_info_after_leaving);
        }
    }


}
