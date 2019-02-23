package com.example.weiduapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.weiduapp.R;
import com.example.weiduapp.bean.ShopBase;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class ShopAdapter extends XRecyclerView.Adapter<ShopAdapter.ViewHolder> {

    private Context context;
    private List<ShopBase.DataBean> list;

    public ShopAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<ShopBase.DataBean> list) {
        if (list!=null){
            this.list = list;
            notifyDataSetChanged();
        }

    }
    public void addList(List<ShopBase.DataBean> list) {
        if (list.size()!=0){
            this.list.addAll(list);
            notifyDataSetChanged();
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.homeliveshopitemitem,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.name.setText(list.get(position).commodityName);
        holder.price.setText(list.get(position).price);

        //设置图片圆角角度
        RoundedCorners roundedCorners= new RoundedCorners(10);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options=RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
        Glide.with(context).load(list.get(position).masterPic).apply(options).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getshopitemId!=null){
                    getshopitemId.getitemid(list.get(position).commodityId);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        TextView price;

        public ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);

        }
    }

    private getShopitemId getshopitemId;

    public interface getShopitemId{
        void getitemid(String id);
    }

    public void getItemId(getShopitemId getshopitemId){
        this.getshopitemId = getshopitemId;
    }



}
