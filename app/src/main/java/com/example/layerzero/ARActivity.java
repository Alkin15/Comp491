package com.example.layerzero;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class ARActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;
    ArFragment arFragment;
    ModelRenderable balloonRenderable;
    Switch arSwitch;
    Boolean switchState;

    @Override
    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!checkIsSupportedDeviceOrFinish(this)) {
            return;
        }

        setContentView(R.layout.activity_ar);
        arSwitch = findViewById(R.id.switch1);
        arSwitch.setChecked(true);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);

        //TODO: credit the owner of the asset
        ModelRenderable.builder().setSource(this, Uri.parse("Balloon.sfb"))
                .build().thenAccept(renderable -> balloonRenderable = renderable)
                .exceptionally(throwable -> {
                    Log.e(TAG, "Unable to load Renderable.", throwable);
                    Toast toast = Toast.makeText(this, "Unable to load renderable", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return null;
        });

        arFragment.setOnTapArPlaneListener((HitResult hitresult, Plane plane, MotionEvent motionevent) -> {
            if (balloonRenderable == null){
                return;
            }
            Anchor anchor = hitresult.createAnchor();
            AnchorNode anchorNode = new AnchorNode(anchor);

            anchorNode.setParent(arFragment.getArSceneView().getScene());
//            anchorNode.setRenderable(balloonRenderable);
            TransformableNode balloon = new TransformableNode(arFragment.getTransformationSystem());

            // Scaling down the balloon object
            balloon.setLocalScale(new Vector3(0.01f, 0.01f, 0.01f));
            balloon.setParent(anchorNode);
            balloon.setRenderable(balloonRenderable);
            balloon.select();
        });

        arSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be true if the switch is in the On position
//                if (isChecked) {
                    arSwitch.setChecked(true);
                    Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(i);
//                }
            }
        });

    }

    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Log.e(TAG, "Sceneform requires Android N or later");
            Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
            activity.finish();
            return false;
        }
        String openGlVersionString = ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE)).getDeviceConfigurationInfo().getGlEsVersion();
        if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
            Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG).show();
            activity.finish();
            return false;
        }
        return true;
    }

}