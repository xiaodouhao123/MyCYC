package com.atguigu.mycyc.view;

import android.content.Context;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.mycyc.R;


/**
 * Created by Administrator on 2016/8/10.
 */
public class NumberAddSubView extends LinearLayout implements View.OnClickListener {
    private Button btn_sub;
    private Button btn_add;
    private TextView tv_num;
    private Context mContext;
    /**
     * 设置默认值
     */
    private int value = 1;
    private int minValue = 1;
    private int maxValue = 10;
    private OnButtonClickListenter onButtonClickListenter;

    public void setOnButtonClickListenter(OnButtonClickListenter onButtonClickListenter) {
        this.onButtonClickListenter = onButtonClickListenter;
    }

    public int getValue() {
//        String val = tv_num.getText().toString();
//        if(!TextUtils.isEmpty(val)) {
//            value=Integer.parseInt(val);
//        }
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tv_num.setText(value + "");
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public NumberAddSubView(Context context) {
        this(context, null);
    }

    public NumberAddSubView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberAddSubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView(context);
        initListener();
        if (attrs != null) {
            TintTypedArray array = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.NumberAddSubView);
            int minValue = array.getInt(R.styleable.NumberAddSubView_minValue, 0);
            setMinValue(minValue);
            int maxValue = array.getInt(R.styleable.NumberAddSubView_maxValue, 0);
            setMaxValue(maxValue);
            int value = array.getInt(R.styleable.NumberAddSubView_value, 0);
            setValue(value);
        }
    }

    private void initListener() {
        btn_sub.setOnClickListener(this);
        btn_add.setOnClickListener(this);
    }

    private void initView(Context context) {
        //第三个参数把当前view加载到NumberAddSubView控件上
        View view = View.inflate(context, R.layout.number_add_sub_view, this);
        btn_sub = (Button) findViewById(R.id.btn_sub);
        btn_add = (Button) findViewById(R.id.btn_add);
        tv_num = (TextView) findViewById(R.id.tv_num);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sub:
                Toast.makeText(mContext, "减", Toast.LENGTH_SHORT).show();
                subNum();
                if (onButtonClickListenter != null) {
                    onButtonClickListenter.onButtonSubClick(v,value);
                }
                break;
            case R.id.btn_add:
                Toast.makeText(mContext, "加", Toast.LENGTH_SHORT).show();
                addNum();
                if (onButtonClickListenter != null) {
                    onButtonClickListenter.onButtonAddClick(v,value);
                }
                break;
        }
    }

    public interface OnButtonClickListenter {
        /**
         * 当增加按钮被点击的时候回调该方法
         *
         * @param view
         * @param value
         */
        public void onButtonAddClick(View view, int value);

        /**
         * 当减少按钮被点击的时候回调这个方法
         *
         * @param view
         * @param value
         */
        public void onButtonSubClick(View view, int value);
    }

    private void addNum() {
        if (value < maxValue) {
            value += 1;
            tv_num.setText(value + "");
        }


    }

    private void subNum() {
        if (value > minValue) {
            value -= 1;
            tv_num.setText(value + "");
        }

    }
}
