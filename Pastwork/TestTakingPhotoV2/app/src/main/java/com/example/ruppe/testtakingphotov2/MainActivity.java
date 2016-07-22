package com.example.ruppe.testtakingphotov2;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;


public class MainActivity extends AppCompatActivity {
    private Bitmap bitmapimage;
    private ImageView imageView;
    private final int PHOTO_SUCCESS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.displayImage);
        takePicture();
        //  File i = createImageFile();
        saveToInternalStorage(bitmapimage);

    }


    private static final int CAMERA_REQUEST = 1888; // field

    private void takePicture(){
        Intent cameraIntent = new  Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, PHOTO_SUCCESS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(MainActivity.this, "OnActivityResultStep1", Toast.LENGTH_SHORT).show();
        if (requestCode == PHOTO_SUCCESS) {
            Toast.makeText(MainActivity.this, "IF", Toast.LENGTH_SHORT).show();
            Bitmap bitmapImage = (Bitmap) data.getExtras().get("data");//this is your bitmap image and now you can do whatever you want with this
            Toast.makeText(MainActivity.this, "new bm image", Toast.LENGTH_SHORT).show();
            imageView.setImageBitmap(bitmapImage); //for example I put bmp in an ImageView
            Toast.makeText(MainActivity.this, "imageview got bm", Toast.LENGTH_SHORT).show();
        }
    }




    private String saveToInternalStorage(Bitmap bitmapImage) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, "profile.jpg");

        FileOutputStream fos = null;
        try {
            Toast.makeText(MainActivity.this, "Tentative of storing", Toast.LENGTH_SHORT).show();
            fos = new FileOutputStream(mypath);
            Toast.makeText(MainActivity.this, "FileoutPutStream", Toast.LENGTH_SHORT).show();
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            Toast.makeText(MainActivity.this, "Tentative of storing", Toast.LENGTH_SHORT).show();
            fos.close();
            Toast.makeText(MainActivity.this, "Storing succes", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directory.getAbsolutePath();
    }
}

