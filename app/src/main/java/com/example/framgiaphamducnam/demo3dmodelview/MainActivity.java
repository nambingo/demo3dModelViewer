package com.example.framgiaphamducnam.demo3dmodelview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.example.framgiaphamducnam.demo3dmodelview.services.ExampleSceneLoader;
import com.example.framgiaphamducnam.demo3dmodelview.services.SceneLoader;
import com.example.framgiaphamducnam.util.Utils;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    //private float[] backgroundColor = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
    private float[] backgroundColor = new float[]{0.2f, 0.2f, 0.2f, 1.0f};
    private SceneLoader scene;
    private Handler handler;
    private String paramAssetDir;
    private String paramAssetFilename;
    private String paramFilename;
    private boolean immersiveMode = true;
    ModelSurfaceView gLView;
    private float[] mEyePoint = {0, 6, 0};
    boolean isClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main_2);
        gLView = findViewById(R.id.glView);
        gLView = new ModelSurfaceView(this);
        setContentView(gLView);
        paramAssetDir = "models";
        paramAssetFilename = "baby.obj";
        paramFilename = null;

        if (paramFilename == null && paramAssetFilename == null) {
            scene = new ExampleSceneLoader(this);
        } else {
            scene = new SceneLoader(this);
        }
        scene.init();
        scene.stopLight();
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setGravity(Gravity.CENTER_VERTICAL);

        final Button leftBtn = new Button(this);
        final Button rightBtn = new Button(this);
        final Button headBtn = new Button(this);
        final Button footBtn = new Button(this);
        setBackround(headBtn, "Head");
        setBackround(leftBtn, "Left");
        setBackround(rightBtn, "Right");
        setBackround(footBtn, "Foot");

        ll.addView(headBtn);
        ll.addView(leftBtn);
        ll.addView(rightBtn);
        ll.addView(footBtn);
        this.addContentView(ll,new
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.FILL_PARENT));
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftBtn.setEnabled(false);
                rightBtn.setEnabled(false);
                headBtn.setEnabled(false);
                footBtn.setEnabled(false);
                isClick = !isClick;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        leftBtn.setEnabled(true);
                        if (!isClick){
                            headBtn.setEnabled(true);
                            rightBtn.setEnabled(true);
                            footBtn.setEnabled(true);
                        }
                    }
                },2000);
                scene.leftClickListener(isClick);

            }
        });
        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftBtn.setEnabled(false);
                rightBtn.setEnabled(false);
                headBtn.setEnabled(false);
                footBtn.setEnabled(false);
                isClick = !isClick;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rightBtn.setEnabled(true);
                        if (!isClick){
                            headBtn.setEnabled(true);
                            leftBtn.setEnabled(true);
                            footBtn.setEnabled(true);
                        }
                    }
                },2000);
                scene.rightClickListener(isClick);
            }
        });
        headBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClick = !isClick;
                leftBtn.setEnabled(false);
                rightBtn.setEnabled(false);
                headBtn.setEnabled(false);
                footBtn.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        headBtn.setEnabled(true);
                        if (!isClick) {
                            leftBtn.setEnabled(true);
                            rightBtn.setEnabled(true);
                            footBtn.setEnabled(true);
                        }
                    }
                },0);
                scene.headClickListener(isClick);
            }
        });
        footBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClick = !isClick;
                footBtn.setEnabled(false);
                leftBtn.setEnabled(false);
                rightBtn.setEnabled(false);
                headBtn.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        footBtn.setEnabled(true);
                        if (!isClick) {
                            leftBtn.setEnabled(true);
                            rightBtn.setEnabled(true);
                            headBtn.setEnabled(true);
                        }
                    }
                },0);
                scene.footClickListener(isClick);
            }
        });
        Utils.printTouchCapabilities(getPackageManager());
    }

    private void setBackround(Button button, String text){
        //final int sdk = android.os.Build.VERSION.SDK_INT;
        //if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
        //    button.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.bg_setup_button) );
        //} else {
        //    button.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_setup_button));
        //}
        button.setText(text);
        button.setAllCaps(false);

    }
    public float[] getBackgroundColor(){
        return backgroundColor;
    }

    public SceneLoader getScene() {
        return scene;
    }

    public File getParamFile() {
        return getParamFilename() != null ? new File(getParamFilename()) : null;
    }

    public String getParamAssetDir() {
        return paramAssetDir;
    }

    public String getParamAssetFilename() {
        return paramAssetFilename;
    }

    public String getParamFilename() {
        return paramFilename;
    }

    public ModelSurfaceView getgLView() {
        return gLView;
    }
}
