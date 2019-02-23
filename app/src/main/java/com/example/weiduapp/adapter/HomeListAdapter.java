package com.example.weiduapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.weiduapp.R;
import com.example.weiduapp.bean.BannerBean;
import com.example.weiduapp.bean.HomeListBean;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

public class HomeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private HomeListBean.Arraylist arraylist;
    private List<HomeListBean.Arraylist.hot> listHot;
    private List<HomeListBean.Arraylist.style> listStyle;
    private List<HomeListBean.Arraylist.live> listLive;
    private final int BANNER = 0;
    private final int HOT = 1;
    private final int STYLE = 2;
    private final int LIVE = 3;
    private List<String> list;

    public HomeListAdapter(Context context) {
        this.context = context;
    }

    public void setList(HomeListBean.Arraylist list, List<HomeListBean.Arraylist.hot> listHot, List<HomeListBean.Arraylist.live> listLive , List<HomeListBean.Arraylist.style> listStyle) {
        this.arraylist = list;
        this.listHot = listHot;
        this.listStyle = listStyle;
        this.listLive = listLive;
        notifyDataSetChanged();
    }

    public void setListBanner(List<BannerBean.ResultBean> listBanner) {

        list = new ArrayList();
        for (BannerBean.ResultBean l : listBanner) {
            list.add(l.getImageUrl());
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        View view;
        if (getItemViewType(viewType)==BANNER){
            view =  LayoutInflater.from(context).inflate(R.layout.home_icon_lun,parent,false);
            ViewHolder_lun viewHolder_lun = new ViewHolder_lun(view);
            return viewHolder_lun;
        }else if (getItemViewType(viewType)==HOT){
            view =  LayoutInflater.from(context).inflate(R.layout.home_hot,parent,false);
            ViewHolder_hot viewHolder_hot = new ViewHolder_hot(view);
            return viewHolder_hot;
        }else if (getItemViewType(viewType)==STYLE){
            view =  LayoutInflater.from(context).inflate(R.layout.home_style,parent,false);
            ViewHolder_style holder_style = new ViewHolder_style(view);
            return holder_style;
        }else{
            view =  LayoutInflater.from(context).inflate(R.layout.home_live,parent,false);
            ViewHolder_live holder_live = new ViewHolder_live(view);
            return holder_live;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int positions) {


        //设置图片圆角角度
        RoundedCorners roundedCorners= new RoundedCorners(10);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options=RequestOptions.bitmapTransform(roundedCorners).override(300, 300);

        if (getItemViewType(positions)==BANNER){
            if (list!=null){
                //((ViewHolder_lun)holder).vp_lun.setImagesUrl(list);
                if (!list.isEmpty()) {
                    ((ViewHolder_lun)holder).banner.setData(list, null);
                    ((ViewHolder_lun)holder).banner.loadImage(new XBanner.XBannerAdapter() {
                        @Override
                        public void loadBanner(XBanner banner, Object model, View view, int position) {
                            Glide.with(context).load(list.get(position)).into((ImageView) view);
                        }
                    });
                    ((ViewHolder_lun)holder).banner.setPageChangeDuration(1000);
                    ((ViewHolder_lun)holder).banner.setPageTransformer(Transformer.Default);
                }
            }
        }else if (getItemViewType(positions)==HOT){


            Glide.with(context).load(listHot.get(0).commodityList.get(0).masterPic).apply(options).into(((ViewHolder_hot)holder).image1);
            Glide.with(context).load(listHot.get(0).commodityList.get(1).masterPic).apply(options).into(((ViewHolder_hot)holder).image2);
            Glide.with(context).load(listHot.get(0).commodityList.get(2).masterPic).apply(options).into(((ViewHolder_hot)holder).image3);

            ((ViewHolder_hot)holder).name1.setText(listHot.get(0).commodityList.get(0).commodityName);
            ((ViewHolder_hot)holder).name2.setText(listHot.get(0).commodityList.get(1).commodityName);
            ((ViewHolder_hot)holder).name3.setText(listHot.get(0).commodityList.get(2).commodityName);

            ((ViewHolder_hot)holder).price1.setText("￥"+listHot.get(0).commodityList.get(0).price);
            ((ViewHolder_hot)holder).price2.setText("￥"+listHot.get(0).commodityList.get(1).price+"");
            ((ViewHolder_hot)holder).price3.setText("￥"+listHot.get(0).commodityList.get(2).price+"");


        }else if (getItemViewType(positions)==STYLE){


            Glide.with(context).load(listStyle.get(0).commodityList.get(0).masterPic).apply(options).into(((ViewHolder_style)holder).image1);
            Glide.with(context).load(listStyle.get(0).commodityList.get(1).masterPic).apply(options).into(((ViewHolder_style)holder).image2);

            ((ViewHolder_style)holder).name1.setText(listStyle.get(0).commodityList.get(0).commodityName);
            ((ViewHolder_style)holder).name2.setText(listStyle.get(0).commodityList.get(1).commodityName);

            ((ViewHolder_style)holder).price1.setText("￥"+listStyle.get(0).commodityList.get(0).price+"");
            ((ViewHolder_style)holder).price2.setText("￥"+listStyle.get(0).commodityList.get(1).price+"");



        }else{

            Glide.with(context).load(listLive.get(0).commodityList.get(0).masterPic).apply(options).into(((ViewHolder_live)holder).image1);
            Glide.with(context).load(listLive.get(0).commodityList.get(1).masterPic).apply(options).into(((ViewHolder_live)holder).image2);
            Glide.with(context).load(listLive.get(0).commodityList.get(2).masterPic).apply(options).into(((ViewHolder_live)holder).image3);
            Glide.with(context).load(listLive.get(0).commodityList.get(3).masterPic).apply(options).into(((ViewHolder_live)holder).image4);

            ((ViewHolder_live)holder).name1.setText(listLive.get(0).commodityList.get(0).commodityName);
            ((ViewHolder_live)holder).name2.setText(listLive.get(0).commodityList.get(1).commodityName);
            ((ViewHolder_live)holder).name3.setText(listLive.get(0).commodityList.get(2).commodityName);
            ((ViewHolder_live)holder).name4.setText(listLive.get(0).commodityList.get(3).commodityName);

            ((ViewHolder_live)holder).price1.setText("￥"+listLive.get(0).commodityList.get(0).price+"");
            ((ViewHolder_live)holder).price2.setText("￥"+listLive.get(0).commodityList.get(1).price+"");
            ((ViewHolder_live)holder).price3.setText("￥"+listLive.get(0).commodityList.get(2).price+"");
            ((ViewHolder_live)holder).price4.setText("￥"+listLive.get(0).commodityList.get(3).price+"");


        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onclickLister!=null){
                    if (positions==1){
                        onclickLister.onclivk(listHot.get(0).id);
                    }else if (positions==2){
                        onclickLister.onclivk(listStyle.get(0).id);
                    }else if (positions==3){
                        onclickLister.onclivk(listLive.get(0).id);
                    }
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return arraylist==null?0:4;
    }

    @Override
    public int getItemViewType(int position) {

        if (position==0){
            return BANNER;
        }else if (position==1&&1002==listHot.get(position-1).id){
            return HOT;
        }else if (position==2&&1003==listStyle.get(position-2).id){
            return STYLE;
        }else{
            return LIVE;
        }

    }


    class ViewHolder_lun extends RecyclerView.ViewHolder{

        XBanner banner;
        //FlyBanner vp_lun;

        public ViewHolder_lun(View itemView) {
            super(itemView);

            banner = itemView.findViewById(R.id.vp_lun);

        }

    }

    class ViewHolder_hot extends RecyclerView.ViewHolder{

        ImageView image1,image2,image3;
        TextView name1,name2,name3;
        TextView price1,price2,price3;

        public ViewHolder_hot(View itemView) {
            super(itemView);

            image1 = itemView.findViewById(R.id.image);
            image2 = itemView.findViewById(R.id.image2);
            image3 = itemView.findViewById(R.id.image3);
            name1 = itemView.findViewById(R.id.name);
            name2 = itemView.findViewById(R.id.name2);
            name3 = itemView.findViewById(R.id.name3);
            price1 = itemView.findViewById(R.id.price);
            price2 = itemView.findViewById(R.id.price2);
            price3 = itemView.findViewById(R.id.price3);

        }
    }

    class ViewHolder_style extends RecyclerView.ViewHolder{

        ImageView image1,image2;
        TextView name1,name2;
        TextView price1,price2;

        public ViewHolder_style(View itemView) {
            super(itemView);
            image1 = itemView.findViewById(R.id.image);
            image2 = itemView.findViewById(R.id.image2);
            name1 = itemView.findViewById(R.id.name);
            name2 = itemView.findViewById(R.id.name2);
            price1 = itemView.findViewById(R.id.price);
            price2 = itemView.findViewById(R.id.price2);
        }
    }

    class ViewHolder_live extends RecyclerView.ViewHolder{

        ImageView image1,image2,image3,image4;
        TextView name1,name2,name3,name4;
        TextView price1,price2,price3,price4;

        public ViewHolder_live(View itemView) {
            super(itemView);

            image1 = itemView.findViewById(R.id.image);
            image2 = itemView.findViewById(R.id.image2);
            image3 = itemView.findViewById(R.id.image3);
            image4 = itemView.findViewById(R.id.image4);
            name1 = itemView.findViewById(R.id.name);
            name2 = itemView.findViewById(R.id.name2);
            name3 = itemView.findViewById(R.id.name3);
            name4 = itemView.findViewById(R.id.name4);
            price1 = itemView.findViewById(R.id.price);
            price2 = itemView.findViewById(R.id.price2);
            price3 = itemView.findViewById(R.id.price3);
            price4 = itemView.findViewById(R.id.price4);
        }
    }


    private onClickLister onclickLister;
    public void initOnClick(onClickLister onclickLister){
        this.onclickLister = onclickLister;
    }

    public interface onClickLister{
        void onclivk(int positions);
    }

}
