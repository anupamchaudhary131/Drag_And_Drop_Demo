package com.example.drag_and_drop_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    ImageView logo;
    String msg;
    private RelativeLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = (ImageView)findViewById(R.id.ImageView);

        logo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ClipData.Item item = new ClipData.Item((CharSequence)view.getTag());
                String[] mimeType = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(view.getTag().toString(), mimeType, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(logo);

                view.startDrag(dragData, myShadow, null, 0);
                return true;

            }
        });

        logo.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                switch(dragEvent.getAction()){
                    case DragEvent.ACTION_DRAG_STARTED:
                        layoutParams = (RelativeLayout.LayoutParams)view.getLayoutParams();
                        Log.v(msg, "Action is DragEvent.ACTION_DRAG_STARTED");
                        int x_cord = (int)dragEvent.getX();
                        int y_cord = (int)dragEvent.getY();

                        layoutParams.leftMargin = x_cord;
                        layoutParams.rightMargin = y_cord;
                        view.setLayoutParams(layoutParams);
                        break;


                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.v(msg, "Action is DragEvent.ACTION_DRAG_ENTERED");
                        x_cord = (int)dragEvent.getX();
                        y_cord = (int)dragEvent.getY();

                        layoutParams.leftMargin = x_cord;
                        layoutParams.topMargin = y_cord;
                        view.setLayoutParams(layoutParams);
                        break;


                    case DragEvent.ACTION_DRAG_EXITED:
                        Log.v(msg, "Action is DragEvent.ACTION_DRAG_EXITED");
                        x_cord = (int)dragEvent.getX();
                        y_cord = (int)dragEvent.getY();

                        layoutParams.leftMargin = x_cord;
                        layoutParams.topMargin = y_cord;
                        view.setLayoutParams(layoutParams);
                        break;


                    case DragEvent.ACTION_DRAG_LOCATION:
                        Log.v(msg, "Action is DragEvent.ACTION_DRAG_LOCATION");
                        x_cord = (int)dragEvent.getX();
                        y_cord = (int)dragEvent.getY();

                        layoutParams.leftMargin = x_cord;
                        layoutParams.topMargin = y_cord;
                        view.setLayoutParams(layoutParams);
                        break;


                    case DragEvent.ACTION_DRAG_ENDED:
                        Log.v(msg, "Action is DragEvent.ACTION_DRAG_ENDED");
                        break;


                    case DragEvent.ACTION_DROP:
                        Log.v(msg, "Action is DragEvent.ACTION_DRAG_DROP");
                        x_cord = (int)dragEvent.getX();
                        y_cord = (int)dragEvent.getY();

                        layoutParams.leftMargin = x_cord;
                        layoutParams.topMargin = y_cord;
                        view.setLayoutParams(layoutParams);
                        logo.setVisibility(View.VISIBLE);
                        break;
                }


                return true;
            }
        });

        logo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(logo);

                    logo.startDrag(data, shadowBuilder, logo, 0);
                    logo.setVisibility(View.VISIBLE);
                    return true;
                }
                else {
                    return true;
                }
            }
        });
    }
}