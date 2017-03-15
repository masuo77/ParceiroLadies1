package com.example.masuo.parceiroladiesrespectbook.ParceiroDB;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;

import com.example.masuo.parceiroladiesrespectbook.StaffInfo.StaffInfoItem;
import com.example.masuo.parceiroladiesrespectbook.StaffList.StaffListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Masuo on 2017/01/25.
 */

public class StaffData {


    public static List<StaffListItem> getAllStaffListItem(Context context, String season) {

        ParceiroDBAdapter parceiroDBAdapter = new ParceiroDBAdapter(context);

        parceiroDBAdapter.open();

        Cursor c = parceiroDBAdapter.getAllStaff(season);

        List<StaffListItem> listItems = new ArrayList<>();

        if (c.moveToFirst()) {
            do {

                StaffListItem item = new StaffListItem();

                item.setId(c.getString(c.getColumnIndex(PlayerContract.StaffInfoTable.COL_ID)));
                item.setName(c.getString(c.getColumnIndex(PlayerContract.StaffInfoTable.COL_NAME)));
                item.setPost(c.getString(c.getColumnIndex(PlayerContract.StaffInfoTable.COL_POST)));
                item.setSeason(c.getString(c.getColumnIndex(PlayerContract.StaffInfoTable.COL_SEASON)));
                String face = c.getString(c.getColumnIndex(PlayerContract.StaffInfoTable.COL_LATEST_FACE));
                if (TextUtils.isEmpty(face)) {
                    face = c.getString(c.getColumnIndex(PlayerContract.StaffInfoTable.COL_FACE));
                }
                item.setFace(face);

                listItems.add(item);

            } while (c.moveToNext());

        }

        c.close();
        parceiroDBAdapter.close();

        return listItems;
    }


    public static StaffInfoItem getStaffInfo(Context context, String season, String id) {

        ParceiroDBAdapter parceiroDBAdapter = new ParceiroDBAdapter(context);
        parceiroDBAdapter.open();
        Cursor c = parceiroDBAdapter.getStaff(season, id);

        StaffInfoItem item = new StaffInfoItem();

        if (c.moveToFirst()) {

            // item.setImageRes(R.mipmap.ic_launcher);
            item.setName(c.getString(c.getColumnIndex(PlayerContract.StaffInfoTable.COL_NAME)));
            item.setYomi(c.getString(c.getColumnIndex(PlayerContract.StaffInfoTable.COL_YOMI)));
            item.setYomi_j(c.getString(c.getColumnIndex(PlayerContract.StaffInfoTable.COL_YOMI_J)));
            item.setBirthday(c.getString(c.getColumnIndex(PlayerContract.StaffInfoTable.COL_BIRTHDAY)));
            item.setHeight(c.getString(c.getColumnIndex(PlayerContract.StaffInfoTable.COL_HEIGHT)));
            item.setWeight(c.getString(c.getColumnIndex(PlayerContract.StaffInfoTable.COL_WEIGHT)));
            item.setBlood(c.getString(c.getColumnIndex(PlayerContract.StaffInfoTable.COL_BLOOD)));
            item.setHome(c.getString(c.getColumnIndex(PlayerContract.StaffInfoTable.COL_HOMETOWN)));
            item.setCareer(c.getString(c.getColumnIndex(PlayerContract.StaffInfoTable.COL_CAREER)));
            String face = c.getString(c.getColumnIndex(PlayerContract.StaffInfoTable.COL_LATEST_FACE));
            if (TextUtils.isEmpty(face)) {
                face = c.getString(c.getColumnIndex(PlayerContract.StaffInfoTable.COL_FACE));
            }
            item.setFace(face);

            item.setPost(c.getString(c.getColumnIndex(PlayerContract.StaffInfoTable.COL_POST)));
            item.setSeasonComment(c.getString(c.getColumnIndex(PlayerContract.StaffInfoTable.COL_COMMENT)));

            item.setSeason(season);
        }

        c.close();
        parceiroDBAdapter.close();

        return item;
    }
}