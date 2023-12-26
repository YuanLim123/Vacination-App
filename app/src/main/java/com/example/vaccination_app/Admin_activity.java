package com.example.vaccination_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class Admin_activity extends AppCompatActivity {

    List<User> UserList;
    ListView listView;
    DatabaseHelper mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        mDatabase = new DatabaseHelper(this);
        UserList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.mylistview);
        loadUserFromDatabase();
        Searching();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_admin_activity);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void loadUserFromDatabase() {
        //we are here using the DatabaseManager instance to get all users

        Cursor cursor = mDatabase.getAllUsers();

        if (cursor.moveToFirst()) {
            do {
                UserList.add(new User(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                ));
            } while (cursor.moveToNext());


            //passing the database manager instance this time to the adapter
            UserAdapter adapter =  new UserAdapter(this, R.layout.list_layout_user, UserList,mDatabase);
            listView.setAdapter(adapter);
        }
    }

    private void Searching(){
        SearchView searchview = (SearchView) findViewById(R.id.UserListSearchView);

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                ArrayList<User> filteredUser = new  ArrayList<User>();
                for(User user: UserList)
                {
                    if(user.getName().toLowerCase().contains(s.toLowerCase()))
                    {
                        filteredUser.add(user);
                    }
                }
                UserAdapter adapter1 = new UserAdapter(Admin_activity.this, R.layout.list_layout_user ,filteredUser, mDatabase);
                listView.setAdapter(adapter1);
                return true;
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}