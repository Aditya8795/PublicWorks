package com.vishnu.aditya.sanitationreview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;


public class CaptureImages extends ActionBarActivity {

    private static final int CAMERA_REQUEST = 1888;
    int currentimage = 0;
    int savedimages = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_images);

        // set image onclick listeners
        this.findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                currentimage = 1;
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
        this.findViewById(R.id.imageView2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                currentimage = 2;
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
        this.findViewById(R.id.imageView3).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                currentimage = 3;
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
        this.findViewById(R.id.imageView4).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                currentimage = 4;
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
    }

    public void capture(View v){
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            savedimages++;
            switch (currentimage){
                case 1:
                    ImageView imageView = (ImageView) this.findViewById(R.id.imageView);
                    Bitmap temp = (Bitmap) data.getExtras().get("data");
                    imageView.setImageBitmap(temp);
                    break;
                case 2:
                    ImageView imageView2 = (ImageView) this.findViewById(R.id.imageView2);
                    Bitmap temp2 = (Bitmap) data.getExtras().get("data");
                    imageView2.setImageBitmap(temp2);
                    break;
                case 3:
                    ImageView imageView3 = (ImageView) this.findViewById(R.id.imageView3);
                    Bitmap temp3 = (Bitmap) data.getExtras().get("data");
                    imageView3.setImageBitmap(temp3);
                    break;
                case 4:
                    ImageView imageView4 = (ImageView) this.findViewById(R.id.imageView4);
                    Bitmap temp4 = (Bitmap) data.getExtras().get("data");
                    imageView4.setImageBitmap(temp4);
                    break;
            }
        }
        else{
            Toast.makeText(this,"Failed to take image",Toast.LENGTH_SHORT).show();
        }
    }

    public void sendImage(View v) {
        Toast.makeText(this, "Sending " + Integer.toString(savedimages) + " number of images", Toast.LENGTH_LONG).show();

        // Get image - 1 in form of string
        ImageView image1 = (ImageView) findViewById(R.id.imageView);
        Bitmap bitmap;
        if (image1.getDrawable() instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) image1.getDrawable()).getBitmap();
        } else {
            Drawable d = image1.getDrawable();
            bitmap = Bitmap.createBitmap(d.getIntrinsicWidth(), d.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }
        ByteArrayOutputStream bytebybyte = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytebybyte);
        byte[] imageBytes = bytebybyte.toByteArray();
        String encodedImage1 = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        // Get image - 2 in form of string
        ImageView image2 = (ImageView) findViewById(R.id.imageView2);
        if (image2.getDrawable() instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) image2.getDrawable()).getBitmap();
        } else {
            Drawable d = image2.getDrawable();
            bitmap = Bitmap.createBitmap(d.getIntrinsicWidth(), d.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }
        ByteArrayOutputStream bytebybyte2 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytebybyte2);
        imageBytes = bytebybyte2.toByteArray();
        String encodedImage2 = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        // Get image - 3 in form of string
        ImageView image3 = (ImageView) findViewById(R.id.imageView3);
        if (image3.getDrawable() instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) image3.getDrawable()).getBitmap();
        } else {
            Drawable d = image3.getDrawable();
            bitmap = Bitmap.createBitmap(d.getIntrinsicWidth(), d.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }
        ByteArrayOutputStream bytebybyte3 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytebybyte3);
        imageBytes = bytebybyte3.toByteArray();
        String encodedImage3 = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        // Get image - 4 in form of string
        ImageView image4 = (ImageView) findViewById(R.id.imageView4);
        if (image4.getDrawable() instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) image4.getDrawable()).getBitmap();
        } else {
            Drawable d = image4.getDrawable();
            bitmap = Bitmap.createBitmap(d.getIntrinsicWidth(), d.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }
        ByteArrayOutputStream bytebybyte4 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytebybyte4);
        imageBytes = bytebybyte4.toByteArray();
        String encodedImage4 = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        Log.i("image1",encodedImage1);
        new SendImages().execute(encodedImage1,encodedImage2,encodedImage3,encodedImage4);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_capture_images, menu);
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
}
