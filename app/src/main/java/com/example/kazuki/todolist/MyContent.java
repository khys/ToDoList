package com.example.kazuki.todolist;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class MyContent {

    public static final List<DummyItem> ITEMS = new ArrayList<>();
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<>();

    static {
        addItem(createDummyItem(0, "いつかやろう", R.id.nav_all));
        addItem(createDummyItem(1, "そのうちやろう", R.id.nav_all));
        addItem(createDummyItem(2, "今日中にやるべきこと", R.id.nav_day));
        addItem(createDummyItem(3, "今月中にやるべきこと", R.id.nav_month));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position, String content, int label) {
        return new DummyItem(String.valueOf(position), content, label);
    }

    public static class DummyItem {
        public final String id;
        public final String content;
        public final int label;

        public DummyItem(String id, String content, int label) {
            this.id = id;
            this.content = content;
            this.label = label;
        }

        @Override
        public String toString() {
            return content;
        }
    }

    public static List<DummyItem> getList(int label) {
        List<DummyItem> list = new ArrayList<>();
        Log.d("label", String.valueOf(label));

        if (label == R.id.nav_all) {
            return ITEMS;
        }
        for (DummyItem item : ITEMS) {
            if (item.label == label) {
                list.add(item);
            }
        }
        return list;
    }
}
