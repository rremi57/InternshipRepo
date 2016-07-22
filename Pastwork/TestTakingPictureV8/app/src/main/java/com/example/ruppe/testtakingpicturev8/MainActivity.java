package com.example.ruppe.testtakingpicturev8;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
// http://stackoverflow.com/questions/17674634/saving-and-reading-bitmaps-images-from-internal-memory-in-android //

public class MainActivity extends AppCompatActivity {
    private Button cameraButton;
    public Bitmap pictureObject;
    private Context context;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraButton = (Button) findViewById(R.id.cameraButton);
        imageView = (ImageView) findViewById(R.id.displayImage);
        context = this;


        configureButtons();

    }

    private final int CAMERA_REQUEST_CODE = 2222;

    private void configureButtons() {
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int myHeight;
        int bytes;
        // To Handle Camera Result
        if (data != null && requestCode == CAMERA_REQUEST_CODE) {

            Bitmap pictureObject = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(pictureObject);
             myHeight = pictureObject.getHeight();
            String myString = String.valueOf(myHeight);
            bytes = pictureObject.getByteCount();
            String myString2 = String.valueOf(bytes);
            Toast.makeText(MainActivity.this, myString, Toast.LENGTH_SHORT).show();
        }
        new ImageSaver(context).
                setFileName("myImage.png").
                setDirectoryName("FolderTest").
                save(pictureObject);
    }

    public class ImageSaver {

            private String directoryName;
            private String fileName;
            private Context context;

            public ImageSaver(Context context) {
                this.context = context;
            }

            public ImageSaver setFileName(String fileName) {
                this.fileName = fileName;
                return this;
            }

            public ImageSaver setDirectoryName(String directoryName) {
                this.directoryName = directoryName;
                return this;
            }

            public void save(Bitmap pictureObject) {
                FileOutputStream fileOutputStream = null;

                try {
                    fileOutputStream = new FileOutputStream(createFile());
                    Toast.makeText(MainActivity.this, "Before", Toast.LENGTH_SHORT).show();
                    pictureObject.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                    Toast.makeText(MainActivity.this, "Compressing& Writing", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (fileOutputStream != null) {
                            Toast.makeText(MainActivity.this, "FoS !=null", Toast.LENGTH_SHORT).show();
                            fileOutputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @NonNull
            private File createFile() {
                File directory = context.getDir(directoryName, Context.MODE_PRIVATE);
                Toast.makeText(MainActivity.this, "Creating", Toast.LENGTH_SHORT).show();
                return new File(directory, fileName);
            }

            public Bitmap load() {
                FileInputStream inputStream = null;
                try {
                    inputStream = new FileInputStream(createFile());
                    return BitmapFactory.decodeStream(inputStream);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        }
    }
