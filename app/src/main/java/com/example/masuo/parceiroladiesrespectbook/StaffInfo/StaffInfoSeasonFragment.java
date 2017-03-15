package com.example.masuo.parceiroladiesrespectbook.StaffInfo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.masuo.parceiroladiesrespectbook.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StaffInfoSeasonFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StaffInfoSeasonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StaffInfoSeasonFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    private StaffInfoItem staffInfoItem;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public StaffInfoSeasonFragment() {
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
    public static StaffInfoSeasonFragment newInstance(StaffInfoItem param1, String param2) {
        StaffInfoSeasonFragment fragment = new StaffInfoSeasonFragment();
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
            staffInfoItem = (StaffInfoItem) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_staff_info_season, container, false);

        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.staff_info_season_layout);

        StaffInfoSeasonFragment.CustomViewHolder holder = new StaffInfoSeasonFragment.CustomViewHolder(linearLayout);

        StaffInfoItem item = staffInfoItem;

        String comment_season = item.getSeason();
        holder.comment_season.setText(comment_season);

        String comment = item.getSeasonComment();
        holder.comment.setText(comment);

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

        final TextView comment_season;
        final TextView comment;

        public CustomViewHolder(LinearLayout layout) {

//            super(itemView);
//            season_note = (TextView) layout.findViewById(R.id.text_view_player_info_season_note);
            comment_season = (TextView) layout.findViewById(R.id.text_view_staff_comment_season);
            comment = (TextView) layout.findViewById(R.id.text_view_staff_comment);

        }
    }

}
