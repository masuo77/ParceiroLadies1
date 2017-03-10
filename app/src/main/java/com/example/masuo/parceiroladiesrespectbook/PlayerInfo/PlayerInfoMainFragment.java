package com.example.masuo.parceiroladiesrespectbook.PlayerInfo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.masuo.parceiroladiesrespectbook.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlayerInfoMainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlayerInfoMainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerInfoMainFragment extends Fragment {
    private static final String LOG = "PlayerInfoMainFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private PlayerInfoItem playerInfoItem;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PlayerInfoMainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayerInfoMainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayerInfoMainFragment newInstance(PlayerInfoItem param1, String param2) {
        Log.i(LOG, "newInstance");

        PlayerInfoMainFragment fragment = new PlayerInfoMainFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG, "onCreate");
        if (getArguments() != null) {
            playerInfoItem = (PlayerInfoItem) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(LOG, "onCreateView");

        View v = inflater.inflate(R.layout.fragment_player_info_main, container, false);

        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.player_info_main_layout);

        CustomViewHolder holder = new CustomViewHolder(linearLayout);

        PlayerInfoItem item = playerInfoItem;

        String birthday = item.getBirthday();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date = format.parse(birthday);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年M月d日");
            String str2 = sdf1.format(date);
            birthday = str2;
        } catch (ParseException e) {
        }


        String height = item.getHeight() + "cm";
        String weight = item.getWeight() + "Kg";
        String blood = item.getBlood();
        String home = item.getHome();
        String career = item.getCareer();

        holder.birthday.setText(birthday);
        holder.height.setText(height);
        holder.weight.setText(weight);
        holder.blood.setText(blood);
        holder.home.setText(home);
        holder.career.setText(career);

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
        Log.i(LOG, "onAttach");
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
        Log.i(LOG, "onDetach");
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
        final TextView birthday;
        final TextView height;
        final TextView weight;
        final TextView blood;
        final TextView home;
        final TextView career;

        public CustomViewHolder(LinearLayout layout) {
            birthday = (TextView) layout.findViewById(R.id.text_view_player_info_birthday);
            height = (TextView) layout.findViewById(R.id.text_view_player_info_height);
            weight = (TextView) layout.findViewById(R.id.text_view_player_info_weight);
            blood = (TextView) layout.findViewById(R.id.text_view_player_info_blood);
            home = (TextView) layout.findViewById(R.id.text_view_player_info_home);
            career = (TextView) layout.findViewById(R.id.text_view_player_info_career);
        }
    }
}
