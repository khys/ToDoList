package com.example.kazuki.todolist;

import android.util.Log;
import android.util.LongSparseArray;

import java.util.ArrayList;
import java.util.List;

public class MyContent {

    public static final List<MyItem> ITEMS = new ArrayList<>();
    public static final LongSparseArray<MyItem> ITEM_MAP = new LongSparseArray<>();

    static {
        //addItem(createMyItem(0, "いつかやろう", R.id.nav_all));
        //addItem(createMyItem(1, "そのうちやろう", R.id.nav_all));
        //addItem(createMyItem(2, "今日中にやるべきこと", R.id.nav_day));
        //addItem(createMyItem(3, "今月中にやるべきこと", R.id.nav_month));
    }

    public static void addItem(MyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static MyItem createMyItem(long id, String content, int label) {
        return new MyItem(id, content, label);
    }

    public static class MyItem {
        public final long id;
        public final String content;
        public final int label;

        public MyItem(long id, String content, int label) {
            this.id = id;
            this.content = content;
            this.label = label;
        }

        @Override
        public String toString() {
            return content;
        }
    }

    public static List<MyItem> getList(int label) {
        List<MyItem> list = new ArrayList<>();
        Log.d("label", String.valueOf(label));

        if (label == R.id.nav_all) {
            return ITEMS;
        }
        for (MyItem item : ITEMS) {
            if (item.label == label) {
                list.add(item);
            }
        }
        return list;
    }
}
