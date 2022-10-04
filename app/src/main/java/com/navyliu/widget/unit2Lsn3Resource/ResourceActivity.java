package com.navyliu.widget.unit2Lsn3Resource;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;

import com.navyliu.widget.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 资源文件
 * <p>
 * <p>
 * 注意事项：
 * 同一个类型，或同一文件夹下面的资源不可以使用相同的文件名，也就是说不能用文件扩展名来区别不同的文件，因为R.java中只保留资源的文件名而不管扩展名，所以如果有二个图片一个是icon.png另一个是icon.jpg，那么在R.java中只会有一个R.drawable.icon。另外一个则会无法访问到。
 * 资源文件的名字必须符合Java变量的命名规则，且不能有大写，只能是'[a-z][0-9]._'，否则会有编译错误，因为R.java中的变量Id要与资源中的文件一一对应，也就是说用资源文件名来作为Id的变量名，所以一定要符合Java变量的命名规则，另外它还不能有大写。
 * 除了SDK支持的folder外，不能再有子Folder，虽不会有编译错误，但是子Folder会被完全忽略，如在/res/layout下在建一个子Folder activity(/res/layout/acitivity/， 那么你在生成的R.java中是看不到activity和其内的内容的。
 * 对于资源文件的大小有限制，最好不要让单个文件大于1M，这是SDK文档说明的限制，但具体的我没有进行试验(据说2.2版本以后的可支持到10M，不知道是真的还是假的)
 * 所有/res下面的资源都能通过Resources()并提供Id来访问。
 */
public class ResourceActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = this.getClass().getName();
    private int anima_status = 0;
    private int animtor;

    // 定义控件
    private AppCompatButton attrBtn;
    private AppCompatTextView attrTxt;
    private AppCompatButton numberBtn;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource);


        findView();// 获取控件
        setOnclickListener();// 绑定监听事件
        getResource(); // 获取并设置资源文获取件
        initAssets(); // assets资源文件
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getResource() {
        attrTxt.setText(getResources().getString(R.string.app_name)); // 获取字符串资源文件，并设置显示
        attrTxt.setTextColor(getResources().getColor(R.color.colorPrimary)); // 获取颜色资源文件，并设置显示
        attrTxt.setTextSize(2, getResources().getInteger(R.integer.textsize20)); // 获取int类型的资源文件并应用
        attrTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_btn));// 获取drawable文件并应用
//        R.drawable.bg_btn)
//        <!-- 定义当button 处于pressed 状态时的形态。 -->
//        <!--            1、Corners：定义圆角  corners    定义圆角-->
//        <!--            android:radius="dimension"      全部的圆角半径-->
//        <!--            android:topLeftRadius="dimension"   左上角的圆角半径-->
//        <!--            android:topRightRadius="dimension"  右上角的圆角半径-->
//        <!--            android:bottomLeftRadius="dimension"    左下角的圆角半径-->
//        <!--            android:bottomRightRadius="dimension"     右下角的圆角半径-->
//        <!--            2、solid：指定内部填充色-->
//        <!--            3、gradient：定义渐变色，可以定义两色渐变和三色渐变，及渐变样式gradient-->
//        <!--            android:type="linear""radial""sweep"    共有3中渐变类型，线性渐变（默认）放射渐变扫描式渐变-->
//        <!--            android:angle="integer"     渐变角度，必须为45的倍数，0为从左到右，90为从上到下-->
//        <!--            android:centerX="float"     渐变中心X的相当位置，范围为0～1-->
//        <!--            android:centerY="float"     渐变中心Y的相当位置，范围为0～1-->
//        <!--            android:startColor="color"   渐变开始点的颜色-->
//        <!--            android:centerColor="color"  渐变中间点的颜色，在开始与结束点之间-->
//        <!--            android:endColor="color"    渐变结束点的颜色-->
//        <!--            android:gradientRadius="float"  渐变的半径，只有当渐变类型为radial时才能使用-->
//        <!--            android:useLevel="true""false"   使用LevelListDrawable时就要设置为true。设为false时才有渐变效果-->
//        <!--            4、stroke：描边属性，可以定义描边的宽度，颜色，虚实线等stroke-->
//        <!--            android:width="dimension"   描边的宽度-->
//        <!--            android:color="color"   描边的颜色-->
//        <!--            以下两个属性设置虚线-->
//        <!--            android:dashWidth="dimension"   虚线的宽度，值为0时是实线-->
//        <!--            android:dashGap="dimension"       虚线的间隔-->
//        <!--            5、size和padding-->
//        <!--            6、Shape的属性（rectangle (矩形)、oval（椭圆）、line(线形)、ring（环形））-->
//        <!--            android:innerRadius         尺寸，内环的半径。-->
//        <!--            android:thickness           尺寸，环的厚度-->
//        <!--            android:innerRadiusRatio    浮点型，以环的宽度比率来表示内环的半径，-->
//        <!--            例如，如果android:innerRadiusRatio，表示内环半径等于环的宽度除以5，这个值是可以被覆盖的，默认为9.-->
//        <!--            android:thicknessRatio      浮点型，以环的宽度比率来表示环的厚度，例如，如果android:thicknessRatio="2"，-->
//        <!--            那么环的厚度就等于环的宽度除以2。这个值是可以被android:thickness覆盖的，默认值是3.-->
//        <!--            android:useLevel            boolean值，如果当做是LevelListDrawable使用时值为true，否则为false.-->
        attrTxt.setBackground(getResources().getDrawable(R.drawable.bg_btn, null)); // 获取drawable资源文件并应用
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.gift); // 获取raw文件并应用
        mediaPlayer.start();
    }

    /**
     * 绑定监听事件
     */
    private void setOnclickListener() {
        attrBtn.setOnClickListener(this);
    }

    /**
     * 获取控件
     */
    @SuppressLint("WrongViewCast")
    private void findView() {
        attrBtn = (AppCompatButton) this.findViewById(R.id.btn_attr);
        attrTxt = (AppCompatTextView) this.findViewById(R.id.txt_attr);
        numberBtn = (AppCompatButton) this.findViewById(R.id.btn_number);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 补间动画
            case R.id.btn_attr: // 四种补间动画组合
                attrAnimator();
                break;
            case R.id.btn_attr_alpha: // 补间动画之透明度
                attrAlphaAnimator();
                break;
            case R.id.btn_attr_rotate: // 补间动画之旋转
                attrRotateAnimator();
                break;
            case R.id.btn_attr_scale: // 补间动画之缩放
                attrScaleAnimator();
                break;
            case R.id.btn_attr_translate: // 补间动画之平移
                attrTranslateAnimator();
                break;
            // 属性动画
            case R.id.porperty_value_animator: // 属性动画的数值变化
                porpertyValueAnimator();
                break;
            case R.id.porperty_object_animator: // 属性动画之对象变化
                porpertyObjectAnimator();
                break;
        }
    }

    /**
     * 属性动画
     * 属性动画完善补间动画的控件位置bug，补间动画不管控件动画执行到什么位置，控件的实际位置，可操作（点击/c触摸/长按等有效）的位置还是原始位置
     */
    private void porpertyObjectAnimator() {
//        ObjectAnimator.ofInt():返回int类型的值
//        ObjectAnimator.ofFloat: 返回float类型的值
//        第一个参数是控件
//        第二个参数是属性名称，属性名称包括alpha（alpha通道，主要控制透明度）
//            translationX：控制x方向平移，值的意思和补间动画一致
//            translationY：控制Y方向的平移，值的意思和补间动画一致
//            rotation:旋转，正数表述顺时针，负数表述逆时针
//            scaleX：X轴上的缩放（缩小放大）、
//            scaleY：Y轴上的缩放
//        第三个参数，表述动画轨迹点，是可变长度类型（...)
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(numberBtn, "alpha", 0.3f, 1.0f, 0.3f, 1.0f, 0.3f, 1.0f);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(numberBtn, "translationX", 0f, 400f, -50f, 270f, -150f, 100f, 0f);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(numberBtn, "translationY", 0f, 400f, -50f, 270f, -150f, 100f, 0f);
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(numberBtn, "rotation", 0f, 360f, -180f, 270f, 0f);
        ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(numberBtn, "scaleX", 1f, 3f, 2f, 1f);
        ObjectAnimator objectAnimator5 = ObjectAnimator.ofFloat(numberBtn, "scaleY", 1f, 3f, 2f, 1f);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 动画更新监听，可以获取动画进度
                int value = (int) (100000 * Float.parseFloat(animation.getAnimatedValue().toString()));
                numberBtn.setText(value + "");
            }
        });
        // 设置动画集合
        AnimatorSet animatorSet = new AnimatorSet();
        // 动画集合的播放规则
        // 第一个方法是play：开始播放
        // ，with表述同时进行，before：with的动画before的动画之前后执行，after：with的动画在after的动画之后执行
        // with、before、after的位置可以是无序的，但为了好理解，建议after->with->before的顺序书写
        animatorSet.play(objectAnimator)
                .with(objectAnimator1)
                .before(objectAnimator4)
                .after(objectAnimator5)
                .with(objectAnimator2)
                .with(objectAnimator3);
        animatorSet.setDuration(10000);
        animatorSet.start();
    }

    private void porpertyValueAnimator() {
//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1.0f);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 10000);
        valueAnimator.setDuration(10000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.d(TAG, "onAnimationUpdate: =====" + animation.getAnimatedValue());
                int value = (int) (100000 * Float.parseFloat(animation.getAnimatedValue().toString()));
                numberBtn.setText(value + "");
            }
        });
        valueAnimator.start();
    }


    /**
     * 补间动画
     */
    private void attrAlphaAnimator() {
        int alpha;
        if (anima_status == 1) {
            alpha = R.anim.alpha_hide;
            anima_status = 0;
        } else {
            alpha = R.anim.alpha_show;
            anima_status = 1;
        }
        Animation animation = AnimationUtils.loadAnimation(this, alpha);
//        animation.setFillAfter(true);
        attrTxt.startAnimation(animation);
    }

    private void attrScaleAnimator() {
        int scale;
        if (anima_status == 1) {
            scale = R.anim.scale_max;
            anima_status = 0;
        } else {
            scale = R.anim.scale_min;
            anima_status = 1;
        }
        Animation animation = AnimationUtils.loadAnimation(this, scale);
        animation.setFillAfter(true);
        attrTxt.startAnimation(animation);
    }

    private void attrRotateAnimator() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        attrTxt.startAnimation(animation);
    }

    private void attrAnimator() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim);
        attrTxt.startAnimation(animation);
    }

    private void attrTranslateAnimator() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate);
        attrTxt.startAnimation(animation);
    }


    /**
     * assets资源文件
     * 开始
     */
    private void initAssets() {
        loadWebView();// 登录网页
        getFileDirOfAsset(); // 获取assets下的资源文件名和文件夹
        readFileOfAsset(); // 读取assets下的资源文件
        showImgOfAsset(); // 显示assets下的图片资源文件
//        playMeiderOfAsset(); // 播放assets下的音频文件
    }

    /**
     * 首先，获取通过openFd()的方法获取asset目录下指定文件的AssetFileDescriptor对象。
     * 其次，通过MediaPlayer对象的 setDataSource ( FileDescriptorfd, longoffset, long length)方法加载音乐文件。
     * 最后，调用prepare方法准备音乐，start方法开始播放音乐。
     */
    private void playMeiderOfAsset() {
        try {
            final MediaPlayer mPlayer = new MediaPlayer();
            AssetFileDescriptor afd = getAssets().openFd("baiguiyexing.mp3");
            mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mPlayer.prepareAsync();
            mPlayer.setLooping(true);
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showImgOfAsset() {
        AppCompatImageView imageView = (AppCompatImageView) this.findViewById(R.id.image);
        try {
            InputStream is = getAssets().open("navyliu.png");
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readFileOfAsset() {
        AppCompatTextView readTxt = (AppCompatTextView) this.findViewById(R.id.txt_asset_file_read);
        try {
            InputStream is = getResources().getAssets().open("index.html");
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = "";
            String result = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            readTxt.setText(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 获取assets下的资源文件名和文件夹
    private void getFileDirOfAsset() {
        try {
            AppCompatTextView fileDirTxt = (AppCompatTextView) this.findViewById(R.id.txt_asset_file);
            String[] fileNames = getAssets().list("");
            String file_str = "";
            for (String fileName : fileNames) {
                file_str += fileName + "，";
            }
            fileDirTxt.setText(file_str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 登录网页
    private void loadWebView() {
        WebView webView = (WebView) this.findViewById(R.id.webview);
//        这里的路径:file:///android_asset/：就相当于assets目录，后面的才是assets下的资源文件路径
        webView.loadUrl("file:///android_asset/index.html");
    }

}
