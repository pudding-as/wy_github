package com.example.wyweixin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.myviewhoder> {

    private static final String TAG = adapter.class.getSimpleName();
    private List<StickyData> list;
    private List<String> listChrid;
    private Context context;
    private View inflater;
    private int expandedPosition = -1;
    public static final int FIRST_STICKY_VIEW = 1;
    public static final int HAS_STICKY_VIEW = 2;
    public static final int NONE_STICKY_VIEW = 3;

    public adapter(Context context,List<StickyData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public myviewhoder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context).inflate(R.layout.tab01,parent,false);
        myviewhoder myviewhoder = new myviewhoder(inflater);
        return myviewhoder;
    }

    @Override
    public void onBindViewHolder(final myviewhoder holder, int position) {
        listChrid = new ArrayList<String>();
        initData();

        StickyData stickyData = list.get(position);
        holder.tvTeam.setText(stickyData.getTeam());
        holder.tvTeamChild.setText(listChrid.get(position));
        if (position == 0) {
            holder.tvArea.setVisibility(View.VISIBLE);
            holder.tvArea.setText(stickyData.area);
            holder.itemView.setTag(FIRST_STICKY_VIEW);
        }
        else {
            if (!TextUtils.equals(stickyData.area, list.get(position - 1).area)) {
                holder.tvArea.setVisibility(View.VISIBLE);
                holder.tvArea.setText(stickyData.area);
                holder.itemView.setTag(HAS_STICKY_VIEW);
            }
            else {
                holder.tvArea.setVisibility(View.GONE);
                holder.itemView.setTag(NONE_STICKY_VIEW);
            }
        }
        holder.itemView.setContentDescription(stickyData.area);
        final boolean isExpanded = position == expandedPosition;
        holder.rlChild.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.rlParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder != null) {
                    holder.rlChild.setVisibility(View.GONE);
                    notifyItemChanged(expandedPosition);
                }
                expandedPosition = isExpanded ? -1 : holder.getAdapterPosition();
//                holder = isExpanded ? null : holder;
                notifyItemChanged(holder.getAdapterPosition());
            }
        });
    }

    private void initData() {
        listChrid.add("本作将体内的细胞世界描述成和人类社会一样有着复杂的社会分工。 每个类型的细胞都有着自己相应的工作，每个细胞都在自己的岗位上兢兢业业，而一旦有病毒或者细菌入侵细胞们就会团结起来共同作战");
        listChrid.add("以遥远的未来为舞台，地上的生物沉入了海底，被微生物吃掉成为无机物体。由于长时间的结晶变成宝石生命体的存在。那个拥有宝石身体的28人，与袭击他们打算将其作为装饰品的月人展开了一场又一场的战斗。 ");
        listChrid.add("肉食兽和草食兽共存的世界。在食肉被视为重罪的情况下 ，全寄宿制的名门高中·却里顿学园发生了学生被吞噬的“食杀事件”。在充斥着不安的校园里，戏剧部的怪人·灰狼雷格西过着与其“庞大身躯”和“锐利尖牙”相反的安静生活。");
        listChrid.add("仰慕的母亲并非亲生母亲。一起生活的他们并非兄弟。Grace=Field House是没有父母的孩子们居住的地方。虽然没有血缘关系，但妈妈和38个兄弟都度过了幸福的每一天，这是不可替代的家。但是，他们的日常在某一天突然宣告结束……");
        listChrid.add("夏目玲子之孙——夏目贵志从祖母的遗物中得到了由契约书所做成的“友人帐”。唯一继承了玲子血统的他，做出了一个重要的决定：将玲子夺过来的妖怪们的名字一一归还。");
        listChrid.add("在此岸与彼岸的交界处，八百万神明和服侍他们的死灵——神器，以及被称为妖的魑魅魍魉一同生活着。某天，普通的女初中生一岐日和意外遇到了居无定所、没有工作、自称是“神”的青年夜斗。怀抱“受万民景仰”这个伟大愿望的他，只好只身在此岸与彼岸间徘徊，为五元的香油钱折腰，接受上至斩妖除魔，下至修东修西的各类委托。");
        listChrid.add("白虎与黑兽——中岛敦与芥川龙之介并肩作战，在与弗朗西斯·F的决战中得胜，与自大国袭来的“组合（Guild）”之间的巨大异能战争宣告结束。“武装侦探社”与“港口黑手党”在此战中缔结的休战协定仍在继续，而从他们引发的毁灭危机中幸存下来的横滨，今天也在路边上演名为日常的物语。");
        listChrid.add("猫妖盗取天明珠被谛听发现，被打回原形重伤而逃。在流落街头的时候被罗小白带回了家，起名罗小黑。故事就这样开始了");
        listChrid.add("中岛阳子是个无论从哪方面看都非常平凡的女高中生。但有一天，随着一位名叫“景麒”的金发青年的出现，从未见过的异形怪兽向她袭击而来。景麒称阳子为“主人”，并将她带入了从未听说过的异世界。");
        listChrid.add("1");
        listChrid.add("2");
        listChrid.add("3");
        listChrid.add("4");
        listChrid.add("5");
        listChrid.add("6");
        listChrid.add("7");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class myviewhoder extends RecyclerView.ViewHolder{
        RelativeLayout rlParent, rlChild;
        TextView  tvTeamChild;
        TextView tvArea, tvTeam;
        public myviewhoder(View itemView) {
            super(itemView);
            tvArea = itemView.findViewById(R.id.tv_sticky_header_view);
            tvTeam = itemView.findViewById(R.id.tv_team);
            rlParent = itemView.findViewById(R.id.rl_parent);
            rlChild = itemView.findViewById(R.id.rl_child);
            tvTeamChild = itemView.findViewById(R.id.tv_team_child);
        }
    }
}
