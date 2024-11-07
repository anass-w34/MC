activity_main.xml

  <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <!-- EditText to get input from the user -->
    <EditText
        android:id="@+id/editTextInput"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Enter text to write to file"
        android:inputType="text"/>

    <!-- Button to trigger writing data to a file -->
    <Button
        android:id="@+id/btnWriteToFile"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Write to File"/>

    <!-- Button to read data from the file -->
    <Button
        android:id="@+id/btnReadFromFile"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Read from File"
        android:layout_marginTop="16dp"/>

    <!-- Button to delete the file -->
    <Button
        android:id="@+id/btnDeleteFile"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Delete File"
        android:layout_marginTop="16dp"/>

    <!-- TextView to display file content after reading -->
    <TextView
        android:id="@+id/textViewFileContent"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="File content will appear here"
        android:textSize="16sp"/>
</LinearLayout>
********************************************************************
    main.java

    package com.example.myfileioapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnWriteToFile, btnReadFromFile, btnDeleteFile;
    EditText editTextInput;  // EditText to take input from user
    TextView textViewFileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        btnWriteToFile = findViewById(R.id.btnWriteToFile);
        btnReadFromFile = findViewById(R.id.btnReadFromFile);
        btnDeleteFile = findViewById(R.id.btnDeleteFile);
        editTextInput = findViewById(R.id.editTextInput);  // Reference to the EditText
        textViewFileContent = findViewById(R.id.textViewFileContent);

        // Set up click listeners
        btnWriteToFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = editTextInput.getText().toString();  // Get the input from EditText
                if (!userInput.isEmpty()) {
                    writeToFile(userInput);  // Write the input text to file
                } else {
                    Toast.makeText(MainActivity.this, "Please enter some text", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReadFromFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFromFile();  // Read content from the file
            }
        });

        btnDeleteFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFile();  // Delete the file
            }
        });
    }

    // Write data to a file in internal storage
    private void writeToFile(String data) {
        try {
            FileOutputStream fos = openFileOutput("myFile.txt", MODE_PRIVATE);
            fos.write(data.getBytes());  // Convert the string to bytes and write to the file
            fos.close();
            Toast.makeText(this, "File written successfully!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error writing to file.", Toast.LENGTH_SHORT).show();
        }
    }

    // Read data from the file in internal storage
    private void readFromFile() {
        try {
            FileInputStream fis = openFileInput("myFile.txt");
            int character;
            StringBuilder stringBuilder = new StringBuilder();
            while ((character = fis.read()) != -1) {
                stringBuilder.append((char) character);  // Append each character to StringBuilder
            }
            fis.close();
            textViewFileContent.setText(stringBuilder.toString());  // Display file content in TextView
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error reading from file.", Toast.LENGTH_SHORT).show();
        }
    }

    // Delete the file from internal storage
    private void deleteFile() {
        boolean isDeleted = deleteFile("myFile.txt");
        if (isDeleted) {
            Toast.makeText(this, "File deleted successfully!", Toast.LENGTH_SHORT).show();
            textViewFileContent.setText("");  // Clear the displayed content
        } else {
            Toast.makeText(this, "Error deleting the file.", Toast.LENGTH_SHORT).show();
        }
    }
}

