****************in res--drawable ---copy aste a pic named as sample_image*********************

  activity_main.xml

  <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <!-- ImageView to display the image -->
    <ImageView
        android:id="@+id/imageView"
        android:layout_width="300dp"
        android:layout_height="300dp"
        android:layout_gravity="center"
        android:src="@drawable/sample_image"
        android:contentDescription="Image"
        android:layout_marginBottom="32dp"/>

    <!-- Button to perform Move Animation -->
    <Button
        android:id="@+id/btnMove"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Move Image"
        android:layout_marginTop="16dp"/>

    <!-- Button to perform Rotate Animation -->
    <Button
        android:id="@+id/btnRotate"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Rotate Image"
        android:layout_marginTop="16dp"/>

    <!-- Button to perform Expand Animation -->
    <Button
        android:id="@+id/btnExpand"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Expand Image"
        android:layout_marginTop="16dp"/>

    <!-- Buttons to apply filters -->
    <Button
        android:id="@+id/btnBrightness"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Apply Brightness"
        android:layout_marginTop="32dp"/>

    <Button
        android:id="@+id/btnDarkness"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Apply Darkness"
        android:layout_marginTop="16dp"/>

    <Button
        android:id="@+id/btnGrayscale"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Apply Grayscale"
        android:layout_marginTop="16dp"/>
</LinearLayout>
*************************************************************************
  main.java

  package com.example.myimageeffectsapp;

import android.animation.ObjectAnimator;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button btnMove, btnRotate, btnExpand, btnBrightness, btnDarkness, btnGrayscale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Views
        imageView = findViewById(R.id.imageView);
        btnMove = findViewById(R.id.btnMove);
        btnRotate = findViewById(R.id.btnRotate);
        btnExpand = findViewById(R.id.btnExpand);
        btnBrightness = findViewById(R.id.btnBrightness);
        btnDarkness = findViewById(R.id.btnDarkness);
        btnGrayscale = findViewById(R.id.btnGrayscale);

        // Set up animation listeners
        btnMove.setOnClickListener(v -> moveImage());
        btnRotate.setOnClickListener(v -> rotateImage());
        btnExpand.setOnClickListener(v -> expandImage());

        // Set up filter listeners
        btnBrightness.setOnClickListener(v -> applyBrightness());
        btnDarkness.setOnClickListener(v -> applyDarkness());
        btnGrayscale.setOnClickListener(v -> applyGrayscale());
    }

    // Animation: Move Image
    private void moveImage() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "translationX", 0f, 500f);
        animator.setDuration(1000);
        animator.start();
    }

    // Animation: Rotate Image
    private void rotateImage() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        animator.setDuration(1000);
        animator.start();
    }

    // Animation: Expand Image
    private void expandImage() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 2f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(imageView, "scaleY", 1f, 2f);
        animatorX.setDuration(1000);
        animatorY.setDuration(1000);
        animatorX.start();
        animatorY.start();
    }

    // Apply Brightness Filter
    private void applyBrightness() {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setScale(1.5f, 1.5f, 1.5f, 1);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
        imageView.setColorFilter(filter);
        Toast.makeText(this, "Applied Brightness", Toast.LENGTH_SHORT).show();
    }

    // Apply Darkness Filter
    private void applyDarkness() {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setScale(0.5f, 0.5f, 0.5f, 1);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
        imageView.setColorFilter(filter);
        Toast.makeText(this, "Applied Darkness", Toast.LENGTH_SHORT).show();
    }

    // Apply Grayscale Filter
    private void applyGrayscale() {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);  // Set saturation to 0 for grayscale
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
        imageView.setColorFilter(filter);
        Toast.makeText(this, "Applied Grayscale", Toast.LENGTH_SHORT).show();
    }
}
