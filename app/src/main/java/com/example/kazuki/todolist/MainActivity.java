package com.example.kazuki.todolist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.example.kazuki.todolist.MyDbContract.MyDbEntry;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        ItemFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.getMenu().add(
                Menu.NONE, getResources().getIdentifier("assignment", "id", getPackageName()),
                Menu.NONE, R.string.assignment)
                .setIcon(R.drawable.assignment);
        navigationView.setNavigationItemSelectedListener(this);
        //onNavigationItemSelected((MenuItem) findViewById(R.id.nav_all));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Bundle arguments = new Bundle();
        arguments.putInt(ItemFragment.ARG_ITEM_ID, item.getItemId());
        Log.d("itemId", String.valueOf(item.getItemId()));
        ItemFragment fragment = new ItemFragment();
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction(MyContent.MyItem item) {

    }

    public void connectDb() {
        String[] projection = {
                BaseColumns._ID,
                MyDbEntry.COLUMN_NAME_TITLE,
                MyDbEntry.COLUMN_NAME_SUBTITLE
        };

        MyDbHelper mDbHelper = new MyDbHelper(getApplicationContext());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        insertItem(db, "いつかやろう", R.id.nav_all);
        insertItem(db, "そのうちやろう", R.id.nav_all);
        insertItem(db, "今日中にやるべきこと", R.id.nav_day);
        insertItem(db, "今月中にやるべきこと", R.id.nav_month);

        Cursor cursor = db.query(MyDbEntry.TABLE_NAME, projection,
                null, null, null, null, null);
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(MyDbEntry._ID));
            String item = cursor.getString(cursor.getColumnIndexOrThrow(MyDbEntry.COLUMN_NAME_TITLE));
            int label = cursor.getInt(cursor.getColumnIndexOrThrow(MyDbEntry.COLUMN_NAME_SUBTITLE));
            MyContent.addItem(MyContent.createMyItem(itemId, item, label));
        }
        cursor.close();
    }

    public long insertItem(SQLiteDatabase db, String item, int label) {
        long newRowId;
        ContentValues values = new ContentValues();
        values.put(MyDbEntry.COLUMN_NAME_TITLE, item);
        values.put(MyDbEntry.COLUMN_NAME_SUBTITLE, label);
        newRowId = db.insert(MyDbEntry.TABLE_NAME, null, values);
        return newRowId;
    }
}
