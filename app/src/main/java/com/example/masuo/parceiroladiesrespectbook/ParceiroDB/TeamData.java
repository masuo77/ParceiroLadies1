package com.example.masuo.parceiroladiesrespectbook.ParceiroDB;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;

import com.example.masuo.parceiroladiesrespectbook.PlayerInfo.PlayerInfoItem;
import com.example.masuo.parceiroladiesrespectbook.PlayerList.PlayerListItem;
import com.example.masuo.parceiroladiesrespectbook.SeasonList.SeasonListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Masuo on 2017/01/25.
 */

public class TeamData {


    public static List<SeasonListItem> getAllSeasonListItem(Context context) {

        ParceiroDBAdapter parceiroDBAdapter = new ParceiroDBAdapter(context);

        parceiroDBAdapter.open();

        Cursor c = parceiroDBAdapter.getAllSeasonList();

        List<SeasonListItem> listItems = new ArrayList<>();

        if (c.moveToFirst()) {
            do {
                SeasonListItem item = new SeasonListItem();
                item.setYear(c.getString(c.getColumnIndex(PlayerContract.SeasonTable.COL_SEASON)));
                item.setLeague(c.getString(c.getColumnIndex(PlayerContract.SeasonTable.COL_LEAGUE)));
                item.setRank(c.getString(c.getColumnIndex(PlayerContract.SeasonTable.COL_RANK)));
                item.setSlogan(c.getString(c.getColumnIndex(PlayerContract.SeasonTable.COL_SLOGAN)));

                listItems.add(item);

            } while (c.moveToNext());

        }

        c.close();
        parceiroDBAdapter.close();

        return listItems;
    }

    public static SeasonListItem getOneSeasonListItem(Context context, String year) {

        ParceiroDBAdapter parceiroDBAdapter = new ParceiroDBAdapter(context);

        parceiroDBAdapter.open();

        Cursor c = parceiroDBAdapter.getOneSeasonData(year);

        SeasonListItem item = new SeasonListItem();
        if (c.moveToFirst()) {
            item.setYear(c.getString(c.getColumnIndex(PlayerContract.SeasonTable.COL_SEASON)));
            item.setLeague(c.getString(c.getColumnIndex(PlayerContract.SeasonTable.COL_LEAGUE)));
            item.setRank(c.getString(c.getColumnIndex(PlayerContract.SeasonTable.COL_RANK)));
            item.setSlogan(c.getString(c.getColumnIndex(PlayerContract.SeasonTable.COL_SLOGAN)));
            item.setNumber_font(c.getString(c.getColumnIndex(PlayerContract.SeasonTable.COL_NUMBER_FONT)));
        }

        c.close();
        parceiroDBAdapter.close();

        return item;
    }

    public static List<PlayerListItem> getAllPlayersListItem(Context context, String season) {

        ParceiroDBAdapter parceiroDBAdapter = new ParceiroDBAdapter(context);

        parceiroDBAdapter.open();

        Cursor c = parceiroDBAdapter.getAllPlayers(season);

        List<PlayerListItem> listItems = new ArrayList<>();

        if (c.moveToFirst()) {
            do {

                PlayerListItem item = new PlayerListItem();
//                item.setImageRes(R.mipmap.ic_launcher);
                item.setId(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_ID)));
                item.setName(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NAME)));
                item.setNumber(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NUMBER)));
                item.setPosition(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_POSITION)));
                item.setSeason(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_SEASON)));
                String face = c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_LATEST_FACE));
                if (TextUtils.isEmpty(face)) {
                    face = c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_FACE));
                }
                item.setFace(face);

                // 追加情報
                List<String> infoList = new ArrayList<>();

                String captain = c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NOTE));
                if (!TextUtils.isEmpty(captain)) {
                    infoList.add(captain);
                }

                String joinStatus = c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_JOINING_STATUS));
                if (!TextUtils.isEmpty(joinStatus)) {
                    infoList.add(joinStatus);
                }
                String leaveStatus = c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_LEAVING_STATUS));
                if (!TextUtils.isEmpty(leaveStatus)) {
                    infoList.add(leaveStatus);
                }

//                String joinSeason = c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_JOINING_SEASON));
//                String leaveSeason = c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_LEAVING_SEASON));
//                if (!TextUtils.isEmpty(joinSeason)) {
//                    if (joinSeason.equals(season)) {
//                        infoList.add("新加入");
//                    }
//                }
//                if (!TextUtils.isEmpty(leaveSeason)) {
//                    if (leaveSeason.equals(season)) {
//                        infoList.add("退団");
//                    }
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

            } while (c.moveToNext());

        }

        c.close();
        parceiroDBAdapter.close();

        return listItems;
    }


    public static PlayerInfoItem getPlayerInfo(Context context, String season, String id) {

        ParceiroDBAdapter parceiroDBAdapter = new ParceiroDBAdapter(context);
        parceiroDBAdapter.open();
        Cursor c = parceiroDBAdapter.getPlayer(season, id);

        PlayerInfoItem item = new PlayerInfoItem();

        if (c.moveToFirst()) {

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
            String face = c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_LATEST_FACE));
            if (TextUtils.isEmpty(face)) {
                face = c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_FACE));
            }
            item.setFace(face);
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

        return item;
    }
}