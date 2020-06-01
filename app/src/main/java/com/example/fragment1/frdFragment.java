package com.example.fragment1;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Fragment;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class frdFragment extends Fragment {

    private static final String TAG = frdFragment.class.getSimpleName();

    private RecyclerView recyclerView;
    private TextView tvArea;
    private List<String> mList = new ArrayList<>();
    private List<StickyData> mDataList = new ArrayList<>();
    private Context context;
    private adapter adapter;

    public frdFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.tab02, container, false);

        recyclerView=view.findViewById(R.id.recycleview);
        tvArea=view.findViewById(R.id.tv_sticky_header_view);

        initList();
        initData();
        initView();

        return view;
    }
    private void initList() {
        mList.add("动漫|工作细胞");
        mList.add("动漫|宝石之国");
        mList.add("动漫|动物狂想曲");
        mList.add("动漫|约定的梦幻岛");
        mList.add("动漫|夏目友人帐");
        mList.add("动漫|野良神");
        mList.add("动漫|文豪野犬");
        mList.add("动漫|罗小黑战记");
        mList.add("小说|十二国记");
        mList.add("小说|红楼梦");
        mList.add("小说|三国演义");
        mList.add("小说|西游记");
        mList.add("小说|聊斋");
        mList.add("电影|复仇者联盟");
        mList.add("电影|蜘蛛侠");
        mList.add("电影|钢铁侠");
        mList.add("戏剧|话剧");
        mList.add("戏剧|歌剧");
        mList.add("戏剧|舞剧");
        mList.add("戏剧|诗剧");
        mList.add("戏剧|歌舞剧");
        mList.add("戏剧|相声剧");
    }

    private void initData() {
        for (int i = 0; i < mList.size(); i++) {
            StickyData bean = new StickyData();

            String s = mList.get(i);
            // area
            String area = s.substring(0, s.indexOf("|"));
            // team
            String team = s.substring(s.indexOf("|") + 1, s.length());

            bean.setArea(area);
            bean.setTeam(team);

            mDataList.add(bean);
        }

        Log.d(TAG, "initData: " + mDataList.size());
    }

    private void initView() {
        context=this.getActivity();
        adapter=new adapter(context,mDataList);

        LinearLayoutManager manager=new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
}
