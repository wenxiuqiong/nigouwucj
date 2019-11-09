package com.example.thingfinding.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.thingfinding.R;

public class InputView extends FrameLayout {

    private int inputIcon;
    private String inputHint;
    private boolean isPassword;

    private View mView;
    private ImageView mIvIcon;
    private EditText mEtInput;


    public InputView(@NonNull Context context) {
        super(context);
        init(context,null);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs){
        if (attrs==null){return;}
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.inputview);
        //获取自定义属性
        inputIcon=array.getResourceId(R.styleable.inputview_input_icon,R.mipmap.timg);
        inputHint=array.getString(R.styleable.inputview_input_hint);
        isPassword=array.getBoolean(R.styleable.inputview_input_password,false);
        array.recycle();

        //绑定布局文件

        mView= LayoutInflater.from(context).inflate(R.layout.input_view,
                this, false);
        mIvIcon=mView.findViewById(R.id.iv_icon);
        mEtInput=mView.findViewById(R.id.ev_input);

        //布局关联属性
        mIvIcon.setImageResource(inputIcon);
        mEtInput.setHint(inputHint);
        mEtInput.setInputType(isPassword ?
                InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD :
                InputType.TYPE_CLASS_PHONE);
        getInputStr();
        addView(mView);

    }

    public String  getInputStr(){
        return  mEtInput.getText().toString().trim();
    }
}
