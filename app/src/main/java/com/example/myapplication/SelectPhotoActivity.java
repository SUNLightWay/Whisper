package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.module.UserInfo;
import com.example.myapplication.service.ServiceImpl.UserServiceImpl;
import com.example.myapplication.service.UserService;
import com.example.myapplication.util.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SelectPhotoActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView text_take_photo;
    private TextView text_pick_photo;
    private TextView text_cancle;
    private ImageView head_icon;
    private File mFile;
    private Bitmap mBitmap;
    String path = "";
    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;
    public static final int CUT_PHOTO = 3;
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectphoto);
        initview();
    }

    private void initview() {

        text_cancle=(TextView)findViewById(R.id.text_cancle);
        text_take_photo=(TextView)findViewById(R.id.text_take_photo);
        text_pick_photo=(TextView)findViewById(R.id.text_pick_photo);

        head_icon=findViewById(R.id.head_icon);

        text_cancle.setOnClickListener(this);
        text_take_photo.setOnClickListener(this);
        text_pick_photo.setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                }
            }
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.text_cancle:
                //取消
                finish();
                break;
            case R.id.text_take_photo:
                pickImageFromCamera();

                break;
            case R.id.text_pick_photo:
                pickImageFromAlbum();

                break;
        }
    }
    //从相册选择
    private void pickImageFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    //拍照
    private void pickImageFromCamera() {
        File outputImage=new File(Environment.getExternalStorageDirectory(),
                "output_image.jpg");//创建File对象，用于存储拍照后的图片，获取sd卡根目录
        try{
            if(outputImage.exists()){
                outputImage.delete();
            }
            outputImage.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        imageUri=Uri.fromFile(outputImage);//File对象转化为Uri对象
        Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent,TAKE_PHOTO); //启动相机程序

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TAKE_PHOTO:
                    cropPhoto(imageUri);
                    break;
                case CHOOSE_PHOTO:
                    if (data == null || data.getData() == null) {
                        return;
                    }
                    try {
                        Uri uri = data.getData();
                        cropPhoto(uri);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    break;
                case CUT_PHOTO:
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {

                        //获取传递过来的用户名
                        Intent intent=getIntent();
                        String IdUser=intent.getStringExtra("idUser");

                        UserServiceImpl userService=new UserServiceImpl();
                        UserInfo userInfo= userService.findUserByID(IdUser);

                        //在这里获得了剪裁后的Bitmap对象，可以用于上传
                        Bitmap image = bundle.getParcelable("data");
                        Log.e("我是裁剪后",""+image);
                        //保存在数据库中
                        userInfo.setHeadshot(Utils.imageToByte(image));
                        userInfo.save();
                        //显示在ImageView中
                        head_icon.setImageBitmap(BitmapFactory.decodeByteArray(userInfo.getHeadshot(),0,userInfo.getHeadshot().length));


                        //更换成功后回到个人信息资料页
                        Intent intent1=new Intent(SelectPhotoActivity.this,EditPersonActivity.class);
                        startActivity(intent1);
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode,resultCode,data);

    }


    /*
    public String saveImage(String name, Bitmap bmp) {
        File appDir = new File(Environment.getExternalStorageDirectory().getPath());
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = name + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


     */


    /**
     * 裁剪图片
     */
    private void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CUT_PHOTO);
    }


}
