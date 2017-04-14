package com.example.dreamwork.activity.SelectPic;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dreamwork.R;
import com.example.dreamwork.util.T;
import com.example.dreamwork.util.V;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SelectPicActivity extends Activity implements OnClickListener {

    private Context mContext;


    /// 上传图片
    private LinearLayout selectPic;
    private SelectPicPopupWindow menuWindow;
    private LinearLayout showPic;
    private LinearLayout showPic1;
    private View SelView;

    //
    private String ptoDir = "/sdcard/worker/";
    private String uploadFile;
    private File dir = null;
    private static final File PHOTO_DIR = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera");
    private File mCurrentPhotoFile;//
    private String fileName;// 图片名称
    private static MediaActionReceiver actionReceiver;


    private static final int SCAN_MEDIA_START = 1;
    private static final int SCAN_MEDIA_FINISH = 2;
    private static final int SCAN_MEDIA_FILE = 3;
    private static final int SCAN_MEDIA_FILE_FINISH_INT = 4;
    private static final int SCAN_MEDIA_FILE_FAIL_INT = 5;
    ProgressDialog delLoadingDialog = null;
    private static final String SCAN_MEDIA_FILE_FINISH = "ACTION_MEDIA_SCANNER_SCAN_FILE_FINISH";
    private final int REQUEST_CODE = 1;
    private static final int PIC_REQUEST_CODE_WITH_DATA = 1; // 标识获取图片数据
    private static final int PIC_REQUEST_CODE_SELECT_CAMERA = 2; // 标识请求照相功能的activity
    private static final int PIC_Select_CODE_ImageFromLoacal = 3;// 标识请求相册取图功能的activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_add_remar_layout);


        InitView();
        actionReceiver = new MediaActionReceiver();
    }

    private void InitView() {
        // TODO Auto-generated method stub

        mContext = this;


        showPic = V.f(this, R.id.showPics);
        showPic1 = V.f(this, R.id.showPics1);
        SelView = LayoutInflater.from(mContext).inflate(R.layout.remark_sel_pic_item_layout, null);
        selectPic = V.f(SelView, R.id.selectPic);
        selectPic.setOnClickListener(this);
        showPic.addView(SelView);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {

            case R.id.selectPic:
                if (myPhotos.size() < 5) {
                    menuWindow = new SelectPicPopupWindow(SelectPicActivity.this, itemsOnClick);
                    menuWindow.setWindowTitleTv("上传图片");
                    // 显示窗口
                    menuWindow.showAtLocation(findViewById(R.id.main), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                } else {
                    T.showShort(mContext, "主人，最多只能上传5张照片哦！");
                }
                break;


            default:
                break;
        }
    }

    // 为弹出窗口实现监听类
    private OnClickListener itemsOnClick = new OnClickListener() {

        public void onClick(View v) {
            if (menuWindow != null) {
                menuWindow.dismiss();
            }

            switch (v.getId()) {
                case R.id.takePhotoBut:
                    try {
                        String status = Environment.getExternalStorageState();
                        if (status.equals(Environment.MEDIA_MOUNTED)) {// 判断是否有SD卡
                            PHOTO_DIR.mkdirs();// 创建照片的存储目录
                            fileName = getPhotoFileName();
                            mCurrentPhotoFile = new File(PHOTO_DIR, fileName);// 给新照的照片文件命名
                            Intent cIntent = getTakePickIntent(mCurrentPhotoFile);
                            startActivityForResult(cIntent, PIC_REQUEST_CODE_SELECT_CAMERA);
                        } else {
                            Toast.makeText(mContext, "没有SD卡", Toast.LENGTH_LONG).show();
                        }

                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(mContext, "", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.pickPhotoBut:
                    String status = Environment.getExternalStorageState();
                    if (status.equals(Environment.MEDIA_MOUNTED)) {// 判断是否有SD卡
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_PICK);
                        intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, PIC_Select_CODE_ImageFromLoacal);
                    } else {
                        Toast.makeText(mContext, "没有SD卡", Toast.LENGTH_LONG).show();
                    }
                    break;

                default:
                    break;
            }

        }

    };

    private List<UploadPicBean> myPhotos = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                // 调用Gallery返回的
                case PIC_REQUEST_CODE_WITH_DATA: {
                    final Bitmap photo = data.getParcelableExtra("data");

                    // 如果Junv文件夹不存在则创建文件夹
                    dir = new File(ptoDir);
                    if (!dir.exists()) {
                        dir.mkdirs();
                        Log.d("创建文件夹", "。。。。。。。。。。。成功");
                    }
                    // 为文件随机产生文件名
                    File tmpFile = new File(ptoDir, "upload.jpg");
                    uploadFile = ptoDir + "upload.jpg";

                    try {
                        // 将bitmap转为jpg文件保存
                        FileOutputStream fileOut = new FileOutputStream(tmpFile);
                        photo.compress(CompressFormat.JPEG, 100, fileOut);
                        fileOut.close();

                        UploadPicBean picBean = new UploadPicBean();
                        picBean.setPic(photo);
                        picBean.setBase64(Base64Test.GetImageStr(uploadFile));

                        boolean isHave = false;
                        for (int m = 0; m < myPhotos.size(); m++) {

                            if (picBean.getBase64().equals(myPhotos.get(m).getBase64())) {
                                isHave = true;
                            }
                        }
                        if (!isHave) {
                            myPhotos.add(picBean);
                        }

                    } catch (FileNotFoundException e) {

                        Log.d("File Saving", "fail.....");

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    try {
                        // uploadImg();
                        SetUpSelectedImage();

                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    break;
                }
                // 照相机程序返回的,再次调用图片剪辑程序去修剪图片
                case PIC_REQUEST_CODE_SELECT_CAMERA: {
                    try {
                        Uri fileUri = Uri.fromFile(mCurrentPhotoFile);

                        try {
                            IntentFilter intentFilter = new IntentFilter(Intent.ACTION_MEDIA_SCANNER_STARTED);
                            intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);
                            intentFilter.addDataScheme("file");
                            intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                            registerReceiver(actionReceiver, intentFilter);
                        } catch (RuntimeException e) {
                            e.printStackTrace();
                        }

                        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, fileUri));

                    } catch (Exception e) {
                        Toast.makeText(this, "获取图片异常，请重新尝试。", Toast.LENGTH_LONG).show();
                    }
                    break;
                }
                case PIC_Select_CODE_ImageFromLoacal:
                    Uri uri = data.getData();
                    boolean isSDCard = true;
                    Uri fileUri = null;
                    ContentResolver cr = SelectPicActivity.this.getContentResolver();
                    Cursor cursor = cr.query(uri, null, null, null, null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                        isSDCard = false;
                        fileName = cursor.getString(3);
                        mCurrentPhotoFile = new File(cursor.getString(1)); // 图片文件路径

                    } else {
                        Toast.makeText(mContext, "该文件不存在!", Toast.LENGTH_LONG).show();
                    }
                    if (mCurrentPhotoFile.exists()) {
                        fileUri = Uri.fromFile(mCurrentPhotoFile);
                        try {
                            IntentFilter intentFilter = new IntentFilter(Intent.ACTION_MEDIA_SCANNER_STARTED);
                            intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);
                            intentFilter.addDataScheme("file");
                            intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                            registerReceiver(actionReceiver, intentFilter);
                        } catch (RuntimeException e) {

                        }
                        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, fileUri));
                    } else {
                        Toast.makeText(mContext, "该文件不存在!!", Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        } else

        {
            switch (requestCode) {
                case REQUEST_CODE:
                    if (resultCode == 5) {
                        // nameText.setText(data.getStringExtra("namenew"));
                        // addText.setText(data.getStringExtra("addnew"));
                    }
                    break;
            }
        }

    }

    private void SetUpSelectedImage() {
        // TODO Auto-generated method stub

        showPic.removeAllViews();
        showPic1.removeAllViews();

        if (myPhotos.size() > 0) {

            if (myPhotos.size() < 3) {
                for (int i = 0; i < myPhotos.size(); i++) {
                    View view = LayoutInflater.from(mContext).inflate(R.layout.remark_pic_item_layout, null);

                    ImageView image = (ImageView) view.findViewById(R.id.remarkPic);
                    image.setImageBitmap(myPhotos.get(i).getPic());

                    final TextView delText = (TextView) view.findViewById(R.id.delPic);
                    final int position = i;
                    delText.setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            // showPic.removeViewAt(position);
                            myPhotos.remove(position);
                            SetUpSelectedImage();
                        }
                    });
                    showPic.addView(view);
                }
                showPic.addView(SelView);
            } else {
                for (int i = 0; i < 3; i++) {
                    View view = LayoutInflater.from(mContext).inflate(R.layout.remark_pic_item_layout, null);

                    ImageView image = (ImageView) view.findViewById(R.id.remarkPic);
                    image.setImageBitmap(myPhotos.get(i).getPic());

                    final TextView delText = (TextView) view.findViewById(R.id.delPic);
                    final int position = i;
                    delText.setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            myPhotos.remove(position);
                            SetUpSelectedImage();
                        }
                    });
                    showPic.addView(view);
                }

                for (int i = 3; i < myPhotos.size(); i++) {
                    View view = LayoutInflater.from(mContext).inflate(R.layout.remark_pic_item_layout, null);

                    ImageView image = (ImageView) view.findViewById(R.id.remarkPic);
                    image.setImageBitmap(myPhotos.get(i).getPic());

                    final TextView delText = (TextView) view.findViewById(R.id.delPic);
                    final int position = i;
                    delText.setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            // showPic.removeViewAt(position);
                            myPhotos.remove(position);
                            SetUpSelectedImage();
                        }
                    });
                    showPic1.addView(view);
                }

                showPic1.addView(SelView);

            }

        } else {
            showPic.addView(SelView);
        }

    }

    // 用当前时间给取得的图片命名
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

    public static Intent getTakePickIntent(File f) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        return intent;
    }

    public class ScanMediaThread extends Thread {
        private int scanCount = 5;
        private int interval = 50;
        private Context cx = null;

        public ScanMediaThread(Context context, int count, int i) {
            scanCount = count;
            interval = i;
            cx = context;
            this.setName(System.currentTimeMillis() + "_ScanMediaThread");
        }

        @Override
        public void run() {
            if (this.cx == null)
                return;
            try {
                int j = 0;
                Uri imgUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver cr = mContext.getContentResolver();

                Cursor cursor;
                for (j = 0; j < this.scanCount; j++) {
                    Thread.sleep(this.interval);
                    cursor = cr.query(imgUri, null,
                            MediaStore.Images.Media.DISPLAY_NAME + "='" + mCurrentPhotoFile.getName() + "'", null,
                            null);
                    if (cursor != null && cursor.getCount() > 0) {
                        mHandler.sendEmptyMessage(SCAN_MEDIA_FILE_FINISH_INT);
                        break;
                    }
                }
                if (j == this.scanCount) {
                    mHandler.sendEmptyMessage(SCAN_MEDIA_FILE_FAIL_INT);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SCAN_MEDIA_START:
                    delLoadingDialog = onCreateDialogByResId(R.string.loading);
                    delLoadingDialog.show();
                    break;
                case SCAN_MEDIA_FINISH:
                    galleryPhoto();
                    break;

                case SCAN_MEDIA_FILE:
                    delLoadingDialog = onCreateDialogByResId(R.string.loading);
                    delLoadingDialog.show();
                    ScanMediaThread sthread = new ScanMediaThread(mContext, 40, 300);
                    sthread.run();
                    break;

                case SCAN_MEDIA_FILE_FINISH_INT:
                    galleryPhoto();
                    break;

                case SCAN_MEDIA_FILE_FAIL_INT:
                    if (delLoadingDialog != null && delLoadingDialog.isShowing()) {
                        delLoadingDialog.dismiss();
                    }

                    try {
                        unregisterReceiver(actionReceiver);
                    } catch (Exception e) {
                    }

                    Toast.makeText(mContext, "no take photo", Toast.LENGTH_LONG).show();
                    break;

            }
        }
    };

    /**
     * 定义receiver接收其他线程的广播
     */
    public class MediaActionReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Intent.ACTION_MEDIA_SCANNER_STARTED.equals(action)) {
                mHandler.sendEmptyMessage(SCAN_MEDIA_START);
            }
            if (Intent.ACTION_MEDIA_SCANNER_FINISHED.equals(action)) {
                mHandler.sendEmptyMessage(SCAN_MEDIA_FINISH);
            }
            if (Intent.ACTION_MEDIA_SCANNER_SCAN_FILE.equals(action)) {
                mHandler.sendEmptyMessage(SCAN_MEDIA_FILE);
            }
        }
    }

    /**
     * 根据资源ID获得ProgressDialog对象
     *
     * @param resId
     * @return
     */
    protected ProgressDialog onCreateDialogByResId(int resId) {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage(getResources().getText(resId));
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);
        return dialog;
    }

    private void galleryPhoto() {
        try {
            long id = 0;
            Uri imgUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            ContentResolver cr = mContext.getContentResolver();

            Cursor cursor = cr.query(imgUri, null,
                    MediaStore.Images.Media.DISPLAY_NAME + "='" + mCurrentPhotoFile.getName() + "'", null, null);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToLast();
                id = cursor.getLong(0);

                Uri uri = ContentUris.withAppendedId(imgUri, id);//
                Uri.fromFile(mCurrentPhotoFile);

                // 启动gallery去剪辑这个照片
                final Intent intent = getCropImageIntent(uri);
                if (intent != null) {
                    startActivityForResult(intent, PIC_REQUEST_CODE_WITH_DATA);
                }

            }

            if (delLoadingDialog != null && delLoadingDialog.isShowing()) {
                delLoadingDialog.dismiss();
            }

            try {
                unregisterReceiver(actionReceiver);
            } catch (Exception e) {
            }
        } catch (Exception ee) {
            // TODO Auto-generated catch block

        }
    }

    /**
     * Constructs an intent for image cropping. 调用图片剪辑程序
     */
    public static Intent getCropImageIntent(Uri photoUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setData(photoUri);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 80);
        intent.putExtra("outputY", 80);
        intent.putExtra("return-data", true);
        return intent;
    }


}
