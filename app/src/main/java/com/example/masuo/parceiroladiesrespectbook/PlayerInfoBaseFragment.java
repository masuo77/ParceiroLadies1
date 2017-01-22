package com.example.masuo.parceiroladiesrespectbook;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlayerInfoBaseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlayerInfoBaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerInfoBaseFragment extends Fragment {
    private static final String LOG = "PlayerInfoBaseFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mSeason;
    private int mId;

    private OnFragmentInteractionListener mListener;

    private PlayerInfoItem playerInfoItem;

    public PlayerInfoBaseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayerInfoBaseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayerInfoBaseFragment newInstance(int param1, int param2) {
        Log.i(LOG, "newInstance");
        PlayerInfoBaseFragment fragment = new PlayerInfoBaseFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG, "onCreate");
        if (getArguments() != null) {
            mSeason = getArguments().getInt(ARG_PARAM1);
            mId = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(LOG, "onCreateView");

        View view = inflater.inflate(R.layout.fragment_player_info_base, null);

        Context context = view.getContext();

        ParceiroDBAdapter parceiroDBAdapter = new ParceiroDBAdapter(context);
        parceiroDBAdapter.open();
        Cursor c = parceiroDBAdapter.getPlayer(mSeason, mId);

        PlayerInfoItem item = new PlayerInfoItem();

        if (c.moveToFirst()) {
            Log.i(LOG, "set database");

            // item.setImageRes(R.mipmap.ic_launcher);
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
        }

        c.close();
        parceiroDBAdapter.close();

        LinearLayout layout = (LinearLayout) view.findViewById(R.id.player_info_layout);

        TextView name = (TextView) layout.findViewById(R.id.tv_name);
        TextView yomi = (TextView) layout.findViewById(R.id.tv_yomi);
        TextView yomi_j = (TextView) layout.findViewById(R.id.tv_yomi_j);
        TextView number = (TextView) layout.findViewById(R.id.tv_number);
        TextView position = (TextView) layout.findViewById(R.id.tv_position);

        name.setText(item.getName());
        yomi.setText(item.getYomi() + " / " + item.getYomi_j());
        yomi_j.setText(item.getYomi_j());
        number.setText(item.getNumber());

        Typeface face = Typeface.createFromAsset(context.getAssets(), "ParNum2016.ttf");
        number.setTypeface(face);


        position.setText(item.getPosition());

        Boolean leaving = false;
        if (!TextUtils.isEmpty(item.getLeaving_season()))
        {
            leaving = true;
        }

//        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentManager manager = this.getChildFragmentManager();
        PlayerInfoFragmentPagerAdapter adapter = new PlayerInfoFragmentPagerAdapter(manager, item, leaving);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
        //inflater.inflate(R.layout.fragment_player_info_base, container, false);
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




}
