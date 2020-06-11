package com.example.gongxiang.Users.UseFile;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.example.gongxiang.R;
import com.example.gongxiang.bean.Userfile;
import com.example.gongxiang.util.Constant;
import com.example.gongxiang.util.UsersfileDbHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;


public class UserFile extends AppCompatActivity implements View.OnClickListener {
    //个人资料页面

    private ImageView headchange=null;
    private TextView nicheng;
    private TextView xingbie;
    private LinearLayout mypossion;
    private LinearLayout sex;
    private LinearLayout names;
    private Button ok;
    private Button alter;
    private String name;
    private Bitmap bitmap;
    private LinearLayout head;
    LinearLayout changepassword;
    ImageButton userfile_back;
    TextView usernames;
    String username = Constant.username;
    byte[] picture;

    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";
    private static final String CROP_IMAGE_FILE_NAME = "bala_crop.jpg";
    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;

    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
    private static int output_X = 480;
    private static int output_Y = 480;
    //改变头像的标记位
    private int new_icon=0xa3;
    private String mExtStorDir;
    private Uri mUriPath;

    private final int PERMISSION_READ_AND_CAMERA =0;//读和相机权限
    private final int PERMISSION_READ =1;//读取权限

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userfile);
        Intent mIntent = getIntent();
        findView();
        usernames.setText(username);
//        nicheng.setText(mIntent.getStringExtra("name"));
//        xingbie.setText(mIntent.getStringExtra("sex"));
//        if (mIntent != null && mIntent.getParcelableExtra("Head") != null) {
//            bitmap = getIntent().getParcelableExtra("Head");
//            headchange.setImageBitmap(bitmap);
//        }
        UsersfileDbHelper dbHelper = new UsersfileDbHelper(getApplicationContext(), UsersfileDbHelper.DB_NAME,null,1);
        LinkedList<Userfile> userfiles = dbHelper.readUserfile(username);
        if(userfiles != null) {
            for (Userfile userfile : userfiles) {
                usernames.setText(userfile.getStuName());
                nicheng.setText(userfile.getName());
                xingbie.setText(userfile.getSex());
//                picture=userfile.getPicture();
//                Bitmap img = BitmapFactory.decodeByteArray(picture, 0, picture.length);
//                headchange.setImageBitmap(img);
            }
        }

    }

    private void findView() {
        mExtStorDir = Environment.getExternalStorageDirectory().toString();
        head = findViewById(R.id.head);
        names=findViewById(R.id.name);
        headchange = findViewById(R.id.headchange);
        nicheng = findViewById(R.id.nicheng);
        xingbie = findViewById(R.id.xingbie);
        sex = findViewById(R.id.sex);
        mypossion = findViewById(R.id.mypossion);
        ok = findViewById(R.id.ok);
        userfile_back=findViewById(R.id.userfile_back);
        usernames=findViewById(R.id.usernames);
        changepassword=findViewById(R.id.changepassword);

        head.setOnClickListener(this);
        mypossion.setOnClickListener(this);
        sex.setOnClickListener(this);
        ok.setOnClickListener(this);
        names.setOnClickListener(this);
        userfile_back.setOnClickListener(this);
        changepassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head:
//                checkReadPermission();
                break;
            case R.id.mypossion:
                Intent intent1 = new Intent(UserFile.this, MyPossion.class);
                startActivity(intent1);
                break;
            case R.id.sex:
                Intent Sex = new Intent(UserFile.this, SelectSex.class);
                startActivityForResult(Sex, 4);
                break;
            case R.id.ok:
            case R.id.userfile_back:
                Intent backIntent = new Intent();//回传的Intent
                Bundle bundle = new Bundle();
                bundle.putString("nicheng", nicheng.getText().toString().trim());
                bundle.putString("xingbie", xingbie.getText().toString().trim());
                backIntent.putExtras(bundle);
                headchange.setDrawingCacheEnabled(Boolean.TRUE);
                backIntent.putExtra("head", headchange.getDrawingCache());
                setResult(1, backIntent);

                UsersfileDbHelper dbHelper = new UsersfileDbHelper(getApplicationContext(),UsersfileDbHelper.DB_NAME,null,1);
                Userfile userfile = new Userfile();
//                //把图片先转化成bitmap格式
//                BitmapDrawable drawable = (BitmapDrawable) headchange.getDrawable();
//                Bitmap bitmap = drawable.getBitmap();
//                //二进制数组输出流
//                ByteArrayOutputStream byStream = new ByteArrayOutputStream();
//                //将图片压缩成质量为100的PNG格式图片
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byStream);
//                //把输出流转换为二进制数组
//                byte[] byteArray = byStream.toByteArray();
//                userfile.setPicture(byteArray);
                userfile.setStuNumber(username);
                userfile.setStuName(username);
                userfile.setSex(xingbie.getText().toString());
                userfile.setName(nicheng.getText().toString());
                dbHelper.saveUserfile(userfile);
                Toast.makeText(getApplicationContext(),"用户信息保存成功!",Toast.LENGTH_SHORT).show();

                finish();
                break;
            case R.id.name:
                Intent Alter = new Intent(UserFile.this, AlterUserFile.class);
                startActivityForResult(Alter, 0);
                break;
            case R.id.changepassword:
                Intent modif = new Intent(UserFile.this,ModifyPwdActivity.class);
                startActivity(modif);
                break;
        }
    }

    // 从本地相册选取图片作为头像
    private void choseHeadImageFromGallery() {
        Intent intentFromGallery = new Intent(Intent.ACTION_PICK, null);
        intentFromGallery.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
    }

    // 启动手机相机拍摄照片作为头像
    private void choseHeadImageFromCameraCapture() {
        String savePath = mExtStorDir;
        Intent intent = null;
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            //设定拍照存放到自己指定的目录,可以先建好
            File file = new File(savePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            Uri pictureUri;
            File pictureFile = new File(savePath, IMAGE_FILE_NAME);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                pictureUri = FileProvider.getUriForFile(this, getPackageName()+".fileProvider", pictureFile);
            } else {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                pictureUri = Uri.fromFile(pictureFile);
            }
            if (intent != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        pictureUri);
                startActivityForResult(intent, CODE_CAMERA_REQUEST);
            }
        }
    }

    public Uri getImageContentUri(File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[] { MediaStore.Images.Media._ID },
                MediaStore.Images.Media.DATA + "=? ",
                new String[] { filePath }, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 6) {
            Bundle bundle1 = data.getExtras();
            String text = null;
            if (bundle1 != null)
                text = bundle1.getString("name");
            Log.d("text", text);
            nicheng.setText(text);
        }
        if (requestCode == 4 && resultCode == 3) {
            Bundle bundle2 = data.getExtras();
            String xing = null;
            if (bundle2 != null)
                xing = bundle2.getString("sex");
            Log.d("text", xing);
            xingbie.setText(xing);
        }
        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                cropRawPhoto(data.getData());
                break;
            case CODE_CAMERA_REQUEST:
                if (hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
                    cropRawPhoto(getImageContentUri(tempFile));
                } else {
                    Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
                            .show();
                }
                break;
            case CODE_RESULT_REQUEST:
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(mUriPath));
                    setImageToHeadView(data,bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void checkStoragePermission() {
        int result = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int resultCAMERA = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_DENIED||resultCAMERA == PackageManager.PERMISSION_DENIED) {
            String[] permissions = {/*Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ,*/Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_READ_AND_CAMERA);
        } else {
            choseHeadImageFromCameraCapture();
        }
    }

    private void checkReadPermission() {
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permission==PackageManager.PERMISSION_DENIED){
            String[] permissions ={Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this,permissions, PERMISSION_READ);
        }else {
            choseHeadImageFromGallery();
        }
    }

    //权限申请回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_READ_AND_CAMERA:
                for (int i=0;i<grantResults.length;i++){
                    if (grantResults[i]==PackageManager.PERMISSION_DENIED){
                        Toast.makeText(this, "why ??????", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                choseHeadImageFromCameraCapture();
                break;
            case PERMISSION_READ:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    choseHeadImageFromGallery();
                }
                break;
        }
    }

    /**
     * 裁剪原始的图片
     */

    public void cropRawPhoto(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", true);
        String mLinshi = System.currentTimeMillis() + CROP_IMAGE_FILE_NAME;
        File mFile = new File(mExtStorDir, mLinshi);
        mUriPath = Uri.parse("file://" + mFile.getAbsolutePath());
        //将裁剪好的图输出到所建文件中
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUriPath);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("return-data", false);
        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */

    private void setImageToHeadView(Intent intent,Bitmap b) {
        try {
            if (intent != null) {
                headchange.setImageBitmap(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */

    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }

    private Bitmap imageZoom(Bitmap bitMap) {
        //图片允许最大空间   单位：KB
        double maxSize =1000.00;
        //将bitmap放至数组中，意在bitmap的大小（与实际读取的原文件要大）
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitMap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        //将字节换成KB
        double mid = b.length/1024;
        //判断bitmap占用空间是否大于允许最大空间  如果大于则压缩 小于则不压缩
        if (mid > maxSize) {
            //获取bitmap大小 是允许最大大小的多少倍
            double i = mid / maxSize;
            //开始压缩  此处用到平方根 将宽带和高度压缩掉对应的平方根倍 （1.保持刻度和高度和原bitmap比率一致，压缩后也达到了最大大小占用空间的大小）
            bitMap = zoomImage(bitMap, bitMap.getWidth() / Math.sqrt(i),
                    bitMap.getHeight() / Math.sqrt(i));
        }
        return bitMap;
    }

    /***
     * 图片的缩放方法
     *
     * @param bgimage
     *            ：源图片资源
     * @param newWidth
     *            ：缩放后宽度
     * @param newHeight
     *            ：缩放后高度
     * @return
     */

    public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
                                   double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap;
    }

}


