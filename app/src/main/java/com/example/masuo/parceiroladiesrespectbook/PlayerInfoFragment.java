package com.example.masuo.parceiroladiesrespectbook;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlayerInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlayerInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerInfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "season";
    private static final String ARG_PARAM2 = "id";

    // TODO: Rename and change types of parameters
    private String mSeason;
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
    public static PlayerInfoFragment newInstance(String param1, int param2) {
        PlayerInfoFragment fragment = new PlayerInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSeason = getArguments().getString(ARG_PARAM1);
            mId = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_player_info, container, false);

        createPlayerInfo(v);

//        Context context = v.getContext();
//
//        ArrayList<PlayerInfoItem> listItems = new ArrayList<>();
//        PlayerInfoItem item = new PlayerInfoItem();
//        listItems.add(item);
//
//        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.player_info_recycler_view);
//        assert recyclerView != null;
//        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
////        recyclerView.addItemDecoration(new DividerItemDecoration(this));
//
//        PlayerInfoRecyclerAdapter adapter = new PlayerInfoRecyclerAdapter(context, listItems);
//        recyclerView.setAdapter(adapter);

        return v;
    }

    private void createPlayerInfo(View v) {
        Context context = v.getContext();

        ParceiroDBAdapter parceiroDBAdapter = new ParceiroDBAdapter(context);

        parceiroDBAdapter.open();

        Cursor c = parceiroDBAdapter.getAllPlayers(mSeason);

        ArrayList<PlayerInfoItem> listItems = new ArrayList<>();
//        final List<String> nameList = new ArrayList<>();

        if (c.moveToFirst()) {
            do {

                PlayerInfoItem item = new PlayerInfoItem();
//                item.setImageRes(R.mipmap.ic_launcher);

                item.setName(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NAME)));
                item.setYomi(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_YOMI)));
                item.setYomi_j(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_YOMI_J)));
                item.setBirthday(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_BIRTHDAY)));
                item.setHeight(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_HEIGHT)));
                item.setWeigth(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_WEIGHT)));
                item.setBlood(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_BLOOD)));
                item.setHome(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_HOMETOWN)));
                item.setCareer(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_CAREER)));
                item.setNumber(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NUMBER)));
                item.setPosition(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_POSITION)));
//                item.setSeason_note(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_SEASON_NOTE)));
                item.setJoining_season(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_JOINING_SEASON)));
                item.setJoining_announced_at(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_JOINING_ANNOUNCED_AT)));
                item.setJoining_comment(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_JOINING_COMMENT)));
                item.setJoining_note(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_JOINING_NOTE)));
                item.setLeaving_season(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_LEAVING_SEASON)));
                item.setLeaving_announced_at(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_LEAVING_ANNOUNCED_AT)));
                item.setLeaving_comment(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_LEAVING_COMMENT)));
                item.setLeaving_note(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_LEAVING_NOTE)));
                item.setAfter_leaving(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_AFTER_LEAVING)));

                listItems.add(item);

//                nameList.add(item.getName());

            } while (c.moveToNext());

        }

        c.close();
        parceiroDBAdapter.close();

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.player_info_recycler_view);
        assert recyclerView != null;
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this));

        PlayerInfoRecyclerAdapter adapter = new PlayerInfoRecyclerAdapter(context, listItems);
        recyclerView.setAdapter(adapter);

        recyclerView.scrollToPosition(mId);

//        adapter.setOnItemClickListener(new PlayerListRecyclerAdapter.onItemClickListener() {
//            @Override
//            public void onClick(View view, int position, String name) {
//                Toast.makeText(getActivity(), Integer.toString(position) + " " + name, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        adapter.setOnImageButtonInfoClickListener(new PlayerListRecyclerAdapter.onImageButtonInfoClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                Toast.makeText(getActivity(), "Click " + Integer.toString(position), Toast.LENGTH_SHORT).show();
//
//                if (mListener != null) {
//                    mListener.onFragmentInteraction(position);
//                }
//
//            }
//        });
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
}
