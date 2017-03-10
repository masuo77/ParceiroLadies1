package com.example.masuo.parceiroladiesrespectbook.PlayerList;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.masuo.parceiroladiesrespectbook.ParceiroDB.TeamData;
import com.example.masuo.parceiroladiesrespectbook.R;
import com.example.masuo.parceiroladiesrespectbook.SeasonList.SeasonListItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.example.masuo.parceiroladiesrespectbook.ParceiroDB.TeamData.getAllPlayersListItem;


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
    private String mSeason;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    // 順番の保存
    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mEditor;
    public static final String LIST_OF_SORTED_DATA_ID = "json_list_sorted_data_id";
    public final static String PREFERENCE_FILE = "preference_file";

    private List<PlayerListItem> listItems;
    private PlayerListRecyclerAdapter adapter;

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
    public static PlayerListFragment newInstance(Context context, String param1, String param2) {
        PlayerListFragment fragment = new PlayerListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        // 順番の保存
        mSharedPreferences = context.getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG, "onCreate");
        if (getArguments() != null) {
            mSeason = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // 並び替えメニュー
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(LOG, "onCreateView");
//        Toast.makeText(getActivity(), "onCreateView", Toast.LENGTH_SHORT).show();

        //setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_player_list, container, false);

        //TEST
//        RecyclerView rv = (RecyclerView) v.findViewById(R.id.player_list_recycler_view);
//        rv.setVisibility(View.GONE);

        createPlayerList(v);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        // 並び替えメニュー表示
        inflater.inflate(R.menu.menu_player_list, menu);
        super.onCreateOptionsMenu(menu, inflater);

        // NavigationDrawer
        //((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(LOG, "onBackpressed");
        switch (item.getItemId()) {
            case R.id.menu_reorder:
                // ボタンをタップした際の処理を記述
//                Toast.makeText(getActivity(), "Reorder", Toast.LENGTH_SHORT).show();

                final String[] items = {"背番号順", "ポジション順", "キャンセル"};
                new AlertDialog.Builder(getActivity())
                        .setTitle("並び替え")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // item_which pressed
                                switch (which) {
                                    case 0: // 背番号
                                        Collections.sort(listItems, new NumberComparator());
                                        onNoteListChanged(listItems);
//                                        adapter.notify();
                                        adapter.notifyDataSetChanged();
                                        adapter.notifyItemRangeChanged(0, listItems.size());
                                        break;
                                    case 1: // ポジション
                                        // ポジション順
                                        Collections.sort(listItems, new PositionComparator());
                                        onNoteListChanged(listItems);
//                                        adapter.notify();
                                        adapter.notifyDataSetChanged();
                                        adapter.notifyItemRangeChanged(0, listItems.size());
                                        break;
                                    case 2: // キャンセル
//                                        Toast.makeText(getActivity(), which + "selected", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        })
                        .show();
                break;
            case android.R.id.home:
                Log.d(LOG, "onBackpressed");
                getActivity().onBackPressed();
                break;
        }
        return true;
    }

    private void createPlayerList(View v) {
        Context context = v.getContext();

        // シーズン情報

        SeasonListItem seasonItem = TeamData.getOneSeasonListItem(context, mSeason);

        TextView textView = (TextView) v.findViewById(R.id.text_view_season);
        textView.setText(seasonItem.getYear());

        textView = (TextView) v.findViewById(R.id.text_view_league);
        textView.setText(seasonItem.getLeague());

        textView = (TextView) v.findViewById(R.id.text_view_rank);
        textView.setText(seasonItem.getRank());

        textView = (TextView) v.findViewById(R.id.text_view_slogan);
        textView.setText(seasonItem.getSlogan());

        // 選手リスト

//        final List<PlayerListItem> listItems = getAllPlayersListItem(context, mSeason);
//        final List<PlayerListItem> listItems = getAllPlayersData(context, mSeason);
        listItems = getAllPlayersData(context, mSeason);

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.player_list_recycler_view);
        assert recyclerView != null;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        recyclerView.addItemDecoration(new DividerItemDecoration(context));

//        final PlayerListRecyclerAdapter adapter = new PlayerListRecyclerAdapter(context, listItems);
        adapter = new PlayerListRecyclerAdapter(context, seasonItem, listItems);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemDecor = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                        0 /*ItemTouchHelper.RIGHT*/) {

                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        final int fromPos = viewHolder.getAdapterPosition();
                        final int toPos = target.getAdapterPosition();
                        adapter.notifyItemMoved(fromPos, toPos);

                        // リスト内の移動
                        PlayerListItem temp = listItems.get(fromPos);
                        listItems.remove(fromPos);
                        listItems.add(toPos, temp);

//                        Log.i(LOG, "pos " + fromPos + "->" + toPos + " " + listItems.get(toPos).getName());

                        onNoteListChanged(listItems);
                        return true;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        //final int fromPos = viewHolder.getAdapterPosition();
                        //datasource.remove(fromPos);
                        //adapter.notifyItemRemoved(fromPos);
                    }
                });
        itemDecor.attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new PlayerListRecyclerAdapter.onItemClickListener() {
            @Override
            public void onClick(View view, int position, String id, String name) {
//                Toast.makeText(getActivity(), Integer.toString(position) + " " + id + " " + name, Toast.LENGTH_SHORT).show();
//                Log.i(LOG, Integer.toString(position) + " " +  id + " " + name);

                if (mListener != null) {
                    Log.i(LOG, Integer.toString(position) + " " + mSeason);
                    mListener.onPlayerListFragmentInteraction(mSeason, id);
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

    @Override
    public void onResume() {
        super.onResume();

        Log.d(LOG, "PlayerListFragment/onResume");

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
        void onPlayerListFragmentInteraction(String year, String id);
    }


    //    @Override
    public void onNoteListChanged(List<PlayerListItem> playerListItems) {
        //after drag and drop operation, the new list of Customers is passed in here

        //create a List of Long to hold the Ids of the
        //Customers in the List
        List<Integer> listOfSortedPlayerId = new ArrayList<>();

        for (PlayerListItem playerListItem : playerListItems) {
            listOfSortedPlayerId.add(Integer.valueOf(playerListItem.getId()));
//            Log.i(LOG, "id=" + playerListItem.getId() + " name=" + playerListItem.getName());
        }

        //convert the List of Longs to a JSON string
        Gson gson = new Gson();
        String jsonListOfSortedPlayerIds = gson.toJson(listOfSortedPlayerId);

        //save to SharedPreference
        mEditor.putString(LIST_OF_SORTED_DATA_ID + mSeason, jsonListOfSortedPlayerIds).commit();
        mEditor.commit();
    }

    private List<PlayerListItem> getAllPlayersData(Context context, String season) {

        //Get the sample data
        List<PlayerListItem> playerListItems = getAllPlayersListItem(context, season);

        //create an empty array to hold the list of sorted Customers
        List<PlayerListItem> sortedPlayersListItems = new ArrayList<>();

        //get the JSON array of the ordered of sorted customers
        String jsonListOfSortedPlayerId = mSharedPreferences.getString(LIST_OF_SORTED_DATA_ID + season, "");

        //check for null
        if (!jsonListOfSortedPlayerId.isEmpty()) {

            //convert JSON array into a List<Long>
            Gson gson = new Gson();
            List<Integer> listOfSortedCustomersId =
                    gson.fromJson(jsonListOfSortedPlayerId,
                            new TypeToken<List<Integer>>() {
                            }.getType());

            //build sorted list
            if (listOfSortedCustomersId != null && listOfSortedCustomersId.size() > 0) {
                for (Integer id : listOfSortedCustomersId) {
                    for (PlayerListItem playerListItem : playerListItems) {
                        Long l = Long.valueOf(playerListItem.getId());
                        if (l.equals(Long.valueOf(id))) {
                            sortedPlayersListItems.add(playerListItem);
                            playerListItems.remove(playerListItem);
                            break;
                        }
                    }
                }
            }

            //if there are still customers that were not in the sorted list
            //maybe they were added after the last drag and drop
            //add them to the sorted list
            if (playerListItems.size() > 0) {
                sortedPlayersListItems.addAll(playerListItems);
            }

            Log.i(LOG, "Found Jason");

            return sortedPlayersListItems;
        } else {
            Log.i(LOG, "No Jason");

            return playerListItems;
        }
    }


//    private List<PlayerListItem> getAllPlayersDataPositionOrder(Context context, String season) {
//
//        //Get the sample data
//        List<PlayerListItem> playerListItems = getAllPlayersListItem(context, season);
//
//        //create an empty array to hold the list of sorted Customers
//        List<PlayerListItem> sortedPlayersListItems = new ArrayList<>();
//
//        //get the JSON array of the ordered of sorted customers
//        String jsonListOfSortedPlayerId = mSharedPreferences.getString(LIST_OF_SORTED_DATA_ID + season, "");
//
//        //check for null
//        if (!jsonListOfSortedPlayerId.isEmpty()) {
//
//            //convert JSON array into a List<Long>
//            Gson gson = new Gson();
//            List<Integer> listOfSortedCustomersId =
//                    gson.fromJson(jsonListOfSortedPlayerId,
//                            new TypeToken<List<Integer>>() {
//                            }.getType());
//
//            //build sorted list
//            if (listOfSortedCustomersId != null && listOfSortedCustomersId.size() > 0) {
//                for (Integer id : listOfSortedCustomersId) {
//                    for (PlayerListItem playerListItem : playerListItems) {
//                        Long l = Long.valueOf(playerListItem.getId());
//                        if (l.equals(Long.valueOf(id))) {
//                            sortedPlayersListItems.add(playerListItem);
//                            playerListItems.remove(playerListItem);
//                            break;
//                        }
//                    }
//                }
//            }
//
//            //if there are still customers that were not in the sorted list
//            //maybe they were added after the last drag and drop
//            //add them to the sorted list
//            if (playerListItems.size() > 0) {
//                sortedPlayersListItems.addAll(playerListItems);
//            }
//
//            Log.i(LOG, "Found Jason");
//
//            return sortedPlayersListItems;
//        } else {
//            Log.i(LOG, "No Jason");
//
//            return playerListItems;
//        }
//    }


    public class PositionComparator implements Comparator<PlayerListItem> {

        //比較メソッド（データクラスを比較して-1, 0, 1を返すように記述する）
        public int compare(PlayerListItem data1, PlayerListItem data2) {
            String position1 = data1.getPosition();
            String position2 = data2.getPosition();

            // GK, DF, MF, FW
            switch (position1) {
                case "GK":
                    switch (position2) {
                        case "GK":
                            return 0;
                        default:
                            return -1;
                    }
                case "DF":
                    switch (position2) {
                        case "GK":
                            return +1;
                        case "DF":
                            return 0;
                        default:
                            return -1;
                    }
                case "MF":
                    switch (position2) {
                        case "GK":
                        case "DF":
                            return +1;
                        case "MF":
                            return 0;
                        default:
                            return -1;
                    }
                case "FW":
                    switch (position2) {
                        case "GK":
                        case "DF":
                        case "MF":
                            return +1;
                        case "FW":
                            return 0;
                        default:
                            return -1;
                    }
            }
            return position1.charAt(0) - position2.charAt(0);
        }
    }

//    public class IDComparator implements Comparator<PlayerListItem> {
//
//        //比較メソッド（データクラスを比較して-1, 0, 1を返すように記述する）
//        public int compare(PlayerListItem data1, PlayerListItem data2) {
//            int id1 = Integer.valueOf(data1.getId());
//            int id2 = Integer.valueOf(data2.getId());
//            if (id1 > id2) {
//                return 1;
//            } else if (id1 == id2) {
//                return 0;
//            } else {
//                return -1;
//            }
//        }
//    }

    public class NumberComparator implements Comparator<PlayerListItem> {

        //比較メソッド（データクラスを比較して-1, 0, 1を返すように記述する）
        public int compare(PlayerListItem data1, PlayerListItem data2) {
            int number1 = Integer.valueOf(data1.getNumber());
            int number2 = Integer.valueOf(data2.getNumber());
            if (number1 > number2) {
                return 1;
            } else if (number1 == number2) {
                return 0;
            } else {
                return -1;
            }
        }
    }

}
