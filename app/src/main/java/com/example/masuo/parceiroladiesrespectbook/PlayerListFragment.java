package com.example.masuo.parceiroladiesrespectbook;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlayerListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlayerListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerListFragment extends Fragment {
    private static final String LOG = "PlayerListFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "season";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mSeason;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PlayerListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayerListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayerListFragment newInstance(int param1, String param2) {
        PlayerListFragment fragment = new PlayerListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG, "onCreate");
        if (getArguments() != null) {
            mSeason = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(LOG, "onCreateView");

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_player_list, container, false);

        TextView textView = (TextView)v.findViewById(R.id.text_view_year);

        textView.setText(String.valueOf(mSeason));

        createPlayerList(v);

        return v;
    }

    private void createPlayerList(View v) {
        Context context = v.getContext();

        ParceiroDBAdapter parceiroDBAdapter = new ParceiroDBAdapter(context);

        parceiroDBAdapter.open();

        Cursor c = parceiroDBAdapter.getAllPlayers(mSeason);

        ArrayList<PlayerListItem> listItems = new ArrayList<>();

        if (c.moveToFirst()) {
            do {

                PlayerListItem item = new PlayerListItem();
                item.setImageRes(R.mipmap.ic_launcher);
                item.setId(c.getInt(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_ID)));
                item.setName(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NAME)));
                item.setNumber(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NUMBER)));
                item.setPosition(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_POSITION)));

                // 追加情報
                List<String> infoList = new ArrayList<>();

                if (!TextUtils.isEmpty(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NOTE)))) {
                    infoList.add(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NOTE)));
                }

                int joinSeason = c.getInt(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_JOINING_SEASON));
                int leaveSeason = c.getInt(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_LEAVING_SEASON));

                if (joinSeason == mSeason) {
                    infoList.add("新加入");
                }
                if (leaveSeason == mSeason) {
                    infoList.add("退団");
                }

//                if (!TextUtils.isEmpty(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_JOINING_COMMENT)))) {
//                    infoList.add("新加入");
//                }
//                if (!TextUtils.isEmpty(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_LEAVING_COMMENT)))) {
//                    infoList.add("退団");
//                }

                int i = 0;
                for (String s : infoList
                        ) {
                    if (!TextUtils.isEmpty(s)) {
                        switch (i) {
                            case 0:
                                item.setNote(s);
                                break;
                            case 1:
                                item.setJoin(s);
                                break;
                            case 2:
                            default:
                                item.setLeaving(s);
                                break;
                        }
                        i++;
                    }
                }

                listItems.add(item);

//                nameList.add(item.getName());

            } while (c.moveToNext());

        }

        c.close();
        parceiroDBAdapter.close();

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.player_list_recycler_view);
        assert recyclerView != null;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        //recyclerView.addItemDecoration(new DividerItemDecoration(this));

        PlayerListRecyclerAdapter adapter = new PlayerListRecyclerAdapter(context, listItems);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new PlayerListRecyclerAdapter.onItemClickListener() {
            @Override
            public void onClick(View view, int position, int id, String name) {
                Toast.makeText(getActivity(), Integer.toString(position) + " " +  id + " " + name, Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnImageButtonInfoClickListener(new PlayerListRecyclerAdapter.onImageButtonInfoClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getActivity(), "Click " + Integer.toString(position), Toast.LENGTH_SHORT).show();

                if (mListener != null) {
                    mListener.onPlayerListFragmentInteraction(mSeason, position);
                }

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onSeasonListFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
        void onPlayerListFragmentInteraction(int year, int id);
    }
}
