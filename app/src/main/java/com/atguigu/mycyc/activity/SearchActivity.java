package com.atguigu.mycyc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.mycyc.R;
import com.atguigu.mycyc.utils.CacheUtils;
import com.atguigu.mycyc.utils.Constant;
import com.atguigu.mycyc.view.MyScrollListView;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends Activity {

    @Bind(R.id.search_header_back)
    ImageView searchHeaderBack;
    @Bind(R.id.search_header_search)
    TextView searchHeaderSearch;
    @Bind(R.id.search_header_edit)
    EditText searchHeaderEdit;
    @Bind(R.id.flowLayout)
    TagFlowLayout flowLayout;
    @Bind(R.id.hot_keys)
    LinearLayout hotKeys;
    @Bind(R.id.user_keys)
    MyScrollListView userKeys;
    @Bind(R.id.clearBtn)
    Button clearBtn;
    @Bind(R.id.user_content)
    LinearLayout userContent;
    //保存数据的集合
    private List<String> list=new ArrayList<>();
    private String filePath = Environment.getExternalStorageDirectory() + "/cyc";;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        //读取本地数据到list集合
        CacheUtils.readTxtFile(filePath,list);
        //设置litview
        adapter = new ArrayAdapter<String>(this, R.layout.text_view, list);
        userKeys.setAdapter(adapter);
    }


    @OnClick({R.id.search_header_back, R.id.search_header_search,R.id.clearBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_header_back://返回
                finish();
                break;
            case R.id.search_header_search://跳转到newactivity
                jumpToNewACtivity();
                break;
            case R.id.clearBtn://清除历史记录
                File file = new File(filePath);
                if(file.exists()) {
                    file.delete();
                    list.clear();
                }
                adapter.notifyDataSetChanged();
                break;
        }
    }

    private void jumpToNewACtivity() {
        String search = searchHeaderEdit.getText().toString();
        if(!TextUtils.isEmpty(search)) {//不为空
            //保存搜索数据
            list.add(search);
            //同步到本地
            CacheUtils.writeTxtFile(filePath,list);
        }
        Intent intent = new Intent(this,NewActivity.class);
        //需要携带数据过去搜索
        intent.putExtra(Constant.SEARCH,search);
        startActivity(intent);
        finish();
    }
}
