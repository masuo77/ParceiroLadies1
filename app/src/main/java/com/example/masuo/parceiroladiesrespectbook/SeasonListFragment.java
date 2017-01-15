package com.example.masuo.parceiroladiesrespectbook;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SeasonListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SeasonListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeasonListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SeasonListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SeasonListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SeasonListFragment newInstance(String param1, String param2) {
        SeasonListFragment fragment = new SeasonListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_season_list, container, false);

        createSeasonList(v);

        return v;
    }

    private void createSeasonList(View v) {
        Context context = v.getContext();

        ParceiroDBAdapter parceiroDBAdapter = new ParceiroDBAdapter(context);

        parceiroDBAdapter.open();

        Cursor c = parceiroDBAdapter.getSeasonList();

        ArrayList<SeasonListItem> listItems = new ArrayList<>();

        if (c.moveToFirst()) {
            do {
                SeasonListItem item = new SeasonListItem();
                item.setYear(c.getString(c.getColumnIndex(PlayerContract.SeasonTable.COL_YEAR)));
                item.setLeague(c.getString(c.getColumnIndex(PlayerContract.SeasonTable.COL_LEAGUE)));
                item.setRank(c.getString(c.getColumnIndex(PlayerContract.SeasonTable.COL_RANK)));

                listItems.add(item);

            } while (c.moveToNext());

        }

        c.close();
        parceiroDBAdapter.close();

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.season_list_recycler_view);

        assert recyclerView != null;
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

//        recyclerView.addItemDecoration(new DividerItemDecoration(this));

        final SeasonListRecyclerAdapter adapter = new SeasonListRecyclerAdapter(context, listItems);
        recyclerView.setAdapter(adapter);

        //
//        adapter.setClickListener(this);

        adapter.setOnItemClickListener(new SeasonListRecyclerAdapter.onItemClickListener() {
            @Override
            public void onClick(View view, int position, final String year) {
                if (mListener != null) {
                    mListener.onFragmentInteraction(year);
                }
            }
        });

        adapter.setOnImageButtonInfoClickListener(new SeasonListRecyclerAdapter.onImageButtonInfoClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getActivity(), "Click " + Integer.toString(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

//    @Override
//    public void itemClicked(View view, int position)
//    {
//        Toast.makeText(getActivity(), "ClickClick " + Integer.toString(position), Toast.LENGTH_SHORT).show();
//    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String year) {
        if (mListener != null) {
            mListener.onFragmentInteraction(year);
        }
    }

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
        void onFragmentInteraction(String year);

    }


}
