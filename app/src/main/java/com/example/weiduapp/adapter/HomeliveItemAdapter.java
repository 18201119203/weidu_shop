package com.example.weiduapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.weiduapp.R;
import com.example.weiduapp.bean.HomeItemShopBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;

public class HomeliveItemAdapter extends RecyclerView.Adapter<HomeliveItemAdapter.ViewHolder> {

    private List<HomeItemShopBean.ResultBean.listBean.CommodityListBean> list;
    private Context context;

    public HomeliveItemAdapter(Context context, List<HomeItemShopBean.ResultBean.listBean.CommodityListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.homeliveshopitemitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Uri uri = Uri.parse(String.valueOf(list.get(position).masterPic));
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        holder.image.setController(controller);

        holder.name.setText(list.get(position).commodityName);
        holder.price.setText("¥:" + list.get(position).price);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickitem!=null){
                    clickitem.getName(list.get(position).commodityId);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView image;
        private TextView name;
        private TextView price;

        public ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
        }
    }

    private onClickitem clickitem;
    public void setClickitem(onClickitem clickitem) {
        this.clickitem = clickitem;
    }
    public interface onClickitem{
        void getName(int id);
    }
}
