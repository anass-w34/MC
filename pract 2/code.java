androidmenfest.xml

  <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <!-- MainConstraintActivity -->
        <activity
            android:name=".MainConstraintActivity"
            android:exported="false" />

        <!-- MainRelativeActivity -->
        <activity
            android:name=".MainRelativeActivity"
            android:exported="false" />

        <!-- ConfirmationActivity -->
        <activity android:name=".ConfirmationActivity"
            android:exported="true" />


    ************************************************************

    ConformationActivity.java

    
    package com.example.a2a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity extends AppCompatActivity {

    private TextView textViewConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        // Initialize the TextView
        textViewConfirmation = findViewById(R.id.textViewConfirmation);

        // Get the data passed from MainActivity
        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        String email = intent.getStringExtra("EMAIL");
        String gender = intent.getStringExtra("GENDER");

        // Display the received data
        String confirmationMessage = "Name: " + name + "\nEmail: " + email + "\nGender: " + gender;
        textViewConfirmation.setText(confirmationMessage);
    }

    // Method to go back to the registration form
    public void goBackToForm(View view) {
        Intent intent = new Intent(ConfirmationActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
**********************************************************************
  MainActivity.java

  package com.example.a2a;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPassword;
    private RadioGroup radioGroupGender;
    private CheckBox checkBoxAgree;
    private ViewGroup formContainer;
    private Button buttonLinearLayout, buttonRelativeLayout, buttonConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        formContainer = findViewById(R.id.formContainer);  // Assuming it's a ViewGroup like LinearLayout
        buttonLinearLayout = findViewById(R.id.buttonLinearLayout);
        buttonRelativeLayout = findViewById(R.id.buttonRelativeLayout);
        buttonConstraintLayout = findViewById(R.id.buttonConstraintLayout);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        checkBoxAgree = findViewById(R.id.checkBoxAgree);
    }

    // Switch to LinearLayout
    public void switchToLinearLayout(View view) {
        formContainer.setVisibility(View.VISIBLE);

        // Ensure formContainer is a ViewGroup before calling removeAllViews
        if (formContainer instanceof ViewGroup) {
            ((ViewGroup) formContainer).removeAllViews();  // Correct usage
        }

        // Inflate the LinearLayout version of the registration form
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.activity_main, formContainer, true);  // Inflate into ViewGroup with 'true' to attach
    }

    // Switch to RelativeLayout
    public void switchToRelativeLayout(View view) {
        formContainer.setVisibility(View.VISIBLE);

        if (formContainer instanceof ViewGroup) {
            ((ViewGroup) formContainer).removeAllViews();  // Correct usage
        }

        // Inflate the RelativeLayout version of the registration form
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.activity_main_relative, formContainer, true);
    }

    // Switch to ConstraintLayout
    public void switchToConstraintLayout(View view) {
        formContainer.setVisibility(View.VISIBLE);

        if (formContainer instanceof ViewGroup) {
            ((ViewGroup) formContainer).removeAllViews();  // Correct usage
        }

        // Inflate the ConstraintLayout version of the registration form
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.activity_main_constraint, formContainer, true);
    }
}
********************************************************************
  MainConstraintActivity.java

  package com.example.a2a;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainConstraintActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_constraint);  // Set the layout

        // Corrected line to find the 'main' view (the ConstraintLayout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}

****************************************************************************

  MainRelativeActivity.java

  package com.example.a2a;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainRelativeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_relative);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
******************************************************************************************

  activity_confirmation.xml

  <?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ConfirmationActivity">

    <!-- TextView for showing the confirmation message -->
    <TextView
        android:id="@+id/textViewConfirmation"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Your details will appear here."
        android:textSize="18sp"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="50dp"/>

    <!-- Back Button to return to the registration form -->
    <Button
        android:id="@+id/buttonBack"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Back to Form"
        app:layout_constraintTop_toBottomOf="@id/textViewConfirmation"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="20dp"
        android:onClick="goBackToForm"/>

</androidx.constraintlayout.widget.ConstraintLayout>
***********************************************************

  activity_main.xml

  <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <!-- Button to switch layout to LinearLayout -->
    <Button
        android:id="@+id/buttonLinearLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Use LinearLayout"
        android:onClick="switchToLinearLayout" />

    <!-- Button to switch layout to RelativeLayout -->
    <Button
        android:id="@+id/buttonRelativeLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Use RelativeLayout"
        android:onClick="switchToRelativeLayout" />

    <!-- Button to switch layout to ConstraintLayout -->
    <Button
        android:id="@+id/buttonConstraintLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Use ConstraintLayout"
        android:onClick="switchToConstraintLayout" />

    <!-- Registration form (initially inside LinearLayout) -->
    <LinearLayout
        android:id="@+id/formContainer"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:layout_marginTop="20dp">

        <!-- Name Field -->
        <EditText
            android:id="@+id/editTextName"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Full Name"
            android:importantForAccessibility="yes"/>

        <!-- Email Field -->
        <EditText
            android:id="@+id/editTextEmail"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Email Address"
            android:inputType="textEmailAddress"
            android:importantForAccessibility="yes"/>

        <!-- Password Field -->
        <EditText
            android:id="@+id/editTextPassword"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Password"
            android:inputType="textPassword"
            android:importantForAccessibility="yes"/>

        <!-- Gender Selection (RadioButtons inside RadioGroup) -->
        <RadioGroup
            android:id="@+id/radioGroupGender"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal">
            <RadioButton
                android:id="@+id/radioButtonMale"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Male" />
            <RadioButton
                android:id="@+id/radioButtonFemale"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Female" />
        </RadioGroup>

        <!-- Agree to Terms Checkbox -->
        <CheckBox
            android:id="@+id/checkBoxAgree"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="I Agree to the Terms" />

        <!-- Submit Button -->
        <Button
            android:id="@+id/buttonSubmit"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Register"
            android:importantForAccessibility="yes" />

    </LinearLayout>

</LinearLayout>

  **************************************************************

  activity_main_constraint.xml

  <?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp"
    android:id="@+id/main">  <!-- Add ID here -->

    <!-- Registration Form in ConstraintLayout -->
    <!-- Full Name Input -->
    <EditText
        android:id="@+id/editTextName"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Full Name"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:importantForAccessibility="yes"
        android:padding="12dp"
        android:focusable="true"
        android:contentDescription="Enter full name"/>

    <!-- Email Input -->
    <EditText
        android:id="@+id/editTextEmail"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Email Address"
        android:inputType="textEmailAddress"
        app:layout_constraintTop_toBottomOf="@id/editTextName"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:importantForAccessibility="yes"
        android:padding="12dp"
        android:contentDescription="Enter email address"/>

    <!-- Password Input -->
    <EditText
        android:id="@+id/editTextPassword"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Password"
        android:inputType="textPassword"
        app:layout_constraintTop_toBottomOf="@id/editTextEmail"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:importantForAccessibility="yes"
        android:padding="12dp"
        android:contentDescription="Enter password"/>

    <!-- Gender Radio Group -->
    <RadioGroup
        android:id="@+id/radioGroupGender"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        app:layout_constraintTop_toBottomOf="@id/editTextPassword"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:importantForAccessibility="yes">

        <!-- Male RadioButton -->
        <RadioButton
            android:id="@+id/radioButtonMale"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Male"
            android:contentDescription="Select Male" />

        <!-- Female RadioButton -->
        <RadioButton
            android:id="@+id/radioButtonFemale"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Female"
            android:contentDescription="Select Female" />
    </RadioGroup>

    <!-- Terms Agreement Checkbox -->
    <CheckBox
        android:id="@+id/checkBoxAgree"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="I Agree to the Terms"
        app:layout_constraintTop_toBottomOf="@id/radioGroupGender"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:importantForAccessibility="yes"
        android:contentDescription="Agree to terms checkbox"/>

    <!-- Submit Button -->
    <Button
        android:id="@+id/buttonSubmit"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Register"
        app:layout_constraintTop_toBottomOf="@id/checkBoxAgree"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:importantForAccessibility="yes"
        android:contentDescription="Submit the registration form"
        android:layout_marginTop="16dp" />

</androidx.constraintlayout.widget.ConstraintLayout>
******************************************************************

  activity_main_relative.xml

  <?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <!-- Registration Form in RelativeLayout -->
    <EditText
        android:id="@+id/editTextName"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Full Name"
        android:importantForAccessibility="yes" />

    <EditText
        android:id="@+id/editTextEmail"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Email Address"
        android:inputType="textEmailAddress"
        android:layout_below="@id/editTextName"
        android:importantForAccessibility="yes" />

    <EditText
        android:id="@+id/editTextPassword"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Password"
        android:inputType="textPassword"
        android:layout_below="@id/editTextEmail"
        android:importantForAccessibility="yes" />

    <RadioGroup
        android:id="@+id/radioGroupGender"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:layout_below="@id/editTextPassword">
        <RadioButton
            android:id="@+id/radioButtonMale"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Male" />
        <RadioButton
            android:id="@+id/radioButtonFemale"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Female" />
    </RadioGroup>

    <CheckBox
        android:id="@+id/checkBoxAgree"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="I Agree to the Terms"
        android:layout_below="@id/radioGroupGender" />

    <Button
        android:id="@+id/buttonSubmit"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Register"
        android:layout_below="@id/checkBoxAgree"
        android:importantForAccessibility="yes" />
</RelativeLayout>
*******************************************************************8
