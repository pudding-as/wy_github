package com.example.wyweixin;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    private RecyclerView rcvSticky;
    private List<String> list;
    private List<StickyData> mDataList = new ArrayList<>();
    private Context context;
    private adapter adapter;

    private Fragment mTab01 = new weixinFragment();
    private Fragment mTab02 = new friendFragment();
    private Fragment mTab03 = new contactFragment();
    private Fragment mTab04 = new settingsFragment();

    private LinearLayout mTabWeixin;
    private LinearLayout mTabFrd;
    private LinearLayout mTabAddress;
    private LinearLayout mTabSettings;

    private ImageButton mImgWeixin;
    private ImageButton mImgFrd;
    private ImageButton mImgAddress;
    private ImageButton mImgSettings;

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
        initFragment();
        setSelect(0);

        context = this;
        rcvSticky = findViewById(R.id.rcv_sticky);
        final TextView tvArea = findViewById(R.id.tv_sticky_header_view);
        list = new ArrayList<String>();
        initList();
        initData();

        adapter = new adapter(context,mDataList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        rcvSticky.setAdapter(adapter);
        rcvSticky.setLayoutManager(manager);
        rcvSticky.setHasFixedSize(true);
        rcvSticky.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rcvSticky.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                View stickyInfoView = recyclerView.findChildViewUnder(
                        tvArea.getMeasuredWidth() / 2, 5);

                if (stickyInfoView != null && stickyInfoView.getContentDescription() != null) {
                    tvArea.setText(String.valueOf(stickyInfoView.getContentDescription()));
                }

                View transInfoView = recyclerView.findChildViewUnder(
                        tvArea.getMeasuredWidth() / 2, tvArea.getMeasuredHeight() + 1);

                if (transInfoView != null && transInfoView.getTag() != null) {

                    int transViewStatus = (int) transInfoView.getTag();
                    int dealtY = transInfoView.getTop() - tvArea.getMeasuredHeight();

                    if (transViewStatus == adapter.HAS_STICKY_VIEW) {
                        if (transInfoView.getTop() > 0) {
                            tvArea.setTranslationY(dealtY);
                        } else {
                            tvArea.setTranslationY(0);
                        }
                    } else if (transViewStatus == adapter.NONE_STICKY_VIEW) {
                        tvArea.setTranslationY(0);
                    }
                }
            }
        });

    }

    private void initData() {
        for (int i = 0; i < list.size(); i++) {
            StickyData bean = new StickyData();
            String s = list.get(i);
            // area
            String area = s.substring(0, s.indexOf("|"));
            // team
            String team = s.substring(s.indexOf("|") + 1, s.length());

            bean.setArea(area);
            bean.setTeam(team);

            mDataList.add(bean);
        }
    }

    private void initList() {
        list.add("动漫|工作细胞");
        list.add("动漫|宝石之国");
        list.add("动漫|动物狂想曲");
        list.add("动漫|约定的梦幻岛");
        list.add("动漫|夏目友人帐");
        list.add("动漫|野良神");
        list.add("动漫|文豪野犬");
        list.add("动漫|罗小黑战记");
        list.add("小说|十二国记");
        list.add("小说|红楼梦");
        list.add("小说|三国演义");
        list.add("小说|西游记");
        list.add("小说|聊斋");
        list.add("电影|复仇者联盟");
        list.add("电影|蜘蛛侠");
        list.add("电影|钢铁侠");
    }

    private void setSelect(int i) {
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        //把图片设置为亮
        //设置内容区域
        switch(i){
            case 0:
                Log.d("setSelect","1");
                transaction.show(mTab01);
                mImgWeixin.setImageResource(R.drawable.tab_weixin_pressed);
                break;
            case 1:
                transaction.show(mTab02);
                mImgFrd.setImageResource(R.drawable.tab_find_frd_pressed);
                break;
            case 2:
                transaction.show(mTab03);
                mImgAddress.setImageResource(R.drawable.tab_address_pressed);
                break;
            case 3:
                transaction.show(mTab04);
                mImgSettings.setImageResource(R.drawable.tab_settings_pressed);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        transaction.hide(mTab01);
        transaction.hide(mTab02);
        transaction.hide(mTab03);
        transaction.hide(mTab04);
    }

    private void initEvent() {
        mTabWeixin.setOnClickListener(this);
        mTabFrd.setOnClickListener(this);
        mTabAddress.setOnClickListener(this);
        mTabSettings.setOnClickListener(this);
    }

    private void initFragment() {
        fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
//        transaction.add(R.id.id_content,mTab01);
        transaction.add(R.id.id_content,mTab02);
        transaction.add(R.id.id_content,mTab03);
        transaction.add(R.id.id_content,mTab04);
        transaction.commit();
    }

    private void initView(){
        mTabWeixin = (LinearLayout)findViewById(R.id.id_tab_weixin);
        mTabFrd = (LinearLayout)findViewById(R.id.id_tab_friend);
        mTabAddress = (LinearLayout)findViewById(R.id.id_tab_contect);
        mTabSettings = (LinearLayout)findViewById(R.id.id_tab_settings);

        mImgWeixin = (ImageButton)findViewById(R.id.id_tab_weixin_img);
        mImgFrd = (ImageButton)findViewById(R.id.id_tab_friend_img);
        mImgAddress = (ImageButton)findViewById(R.id.id_tab_contect_img);
        mImgSettings = (ImageButton)findViewById(R.id.id_tab_settings_img);
    }

    @Override
    public void onClick(View v) {
        resetImgs();
        switch (v.getId()){
            case R.id.id_tab_weixin:
                setSelect(0);
                break;
            case R.id.id_tab_friend:
                setSelect(1);
                break;
            case R.id.id_tab_contect:
                setSelect(2);
                break;
            case R.id.id_tab_settings:
                setSelect(3);
                break;
        }
    }

    public void resetImgs() {
        mImgWeixin.setImageResource(R.drawable.tab_weixin_normal);
        mImgFrd.setImageResource(R.drawable.tab_find_frd_normal);
        mImgAddress.setImageResource(R.drawable.tab_address_normal);
        mImgSettings.setImageResource(R.drawable.tab_settings_normal);
    }
}
