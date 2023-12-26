package com.example.vaccination_app;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class validation  {



    public boolean checkfieldvalue(TextInputEditText textInputEditText,
                                           TextInputLayout textInputLayout) {


        if (textInputEditText.getText().toString().isEmpty()) {
            textInputEditText.setError("This field cannot be empty!");//set error message
            textInputEditText.setFocusable(true);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
            return true;
        }
    }
}