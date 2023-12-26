package com.example.vaccination_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {

    Context mCtx;
    int layoutRes;
    List<User> userList;
    DatabaseHelper mDatabase;


    public UserAdapter(Context mCtx, int layoutRes, List<User> userList,
                       DatabaseHelper mDatabase) {
        super(mCtx, layoutRes, userList);
        this.mCtx = mCtx;
        this.layoutRes = layoutRes;
        this.userList = userList;
        this.mDatabase = mDatabase;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(layoutRes, null);
        //from list_layout_user
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewIC = view.findViewById(R.id.textViewIC);
        TextView textViewVaccine = view.findViewById(R.id.textViewVaccine);
        TextView textViewPhone = view.findViewById(R.id.textViewPhone);
        final User user = userList.get(position);

        textViewName.setText(user.getName());
        textViewIC.setText(String.valueOf(user.getIc()));
        textViewVaccine.setText(user.getVaccine());
        textViewPhone.setText(String.valueOf(user.getPhone()));

        view.findViewById(R.id.buttonDeleteUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUser(user);
            }
        });

        view.findViewById(R.id.buttonEditUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAnUser(user);
            }
        });
        return view;
    }

    private void updateAnUser (final User user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.dialog_update_user, null);
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        //from dialog_update employee.xml
        final EditText editTextName = view.findViewById(R.id.etName);
        final EditText editTextIC = view.findViewById(R.id.etIC);
        final EditText editTextPhone = view.findViewById(R.id.etPhone);
        final Spinner spinner = view.findViewById(R.id.spinnerVaccine);

        editTextName.setText(user.getName());
        editTextIC.setText(String.valueOf((user.getIc())));
        editTextPhone.setText(String.valueOf((user.getPhone())));

        view.findViewById(R.id.buttonUpdateUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
                String ic = editTextIC.getText().toString();
                String phone = editTextPhone.getText().toString();
                String vaccine = spinner.getSelectedItem().toString();

                if (name.isEmpty()) {
                    editTextName.setError("Name cant be empty");
                    editTextName.requestFocus();
                    return;
                }
                if (ic.isEmpty()) {
                    editTextIC.setError("IC cant be empty");
                    editTextIC.requestFocus();
                    return;
                }
                //calling the update method from database manager instance
                if (mDatabase.updateUser(user.getId(), name, ic, phone, vaccine)) {
                    Toast.makeText(mCtx, "User Updated", Toast.LENGTH_SHORT).show();
                    loadUserFromDatabaseAgain();
                }
                alertDialog.dismiss();
            }
        });
    }//update employee

    private void deleteUser(final User user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        builder.setTitle("Are you sure");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //calling the delete method from the database manager instance
                if (mDatabase.deleteUser(user.getId()))
                    loadUserFromDatabaseAgain();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void loadUserFromDatabaseAgain() {
        //calling the read method from database instance
        Cursor cursor = mDatabase.getAllUsers();

        userList.clear();
        if (cursor.moveToFirst()) {
            do {
                userList.add(new User(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                ));
            } while (cursor.moveToNext());
        }
        notifyDataSetChanged();
    }//load employees
}//employee adapter

