package com.example.weiduapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weiduapp.R;
import com.example.weiduapp.bean.BannerBean;
import com.example.weiduapp.bean.HomeItemShopBean;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

public class HomeShopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements HomehotItemAdapter.onClickitem,HomestyleItemAdapter.onClickitem,HomeliveItemAdapter.onClickitem{

    private List<BannerBean.ResultBean> blist;
    private List<HomeItemShopBean.ResultBean.listBean> slist;
    private Context context;


    public HomeShopAdapter(Context context) {
        this.context = context;
        slist = new ArrayList<>();
        blist = new ArrayList<>();
    }

    public void setBlist(List<BannerBean.ResultBean> blist) {
        this.blist = blist;
        notifyDataSetChanged();
    }

    public void setSlist(List<HomeItemShopBean.ResultBean.listBean> slist) {
        this.slist = slist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (getItemViewType(viewType)==0){
            view = LayoutInflater.from(context).inflate(R.layout.home_icon_lun, parent, false);
            BannerHolder bannerHolder = new BannerHolder(view);
            return bannerHolder;
        }else if (getItemViewType(viewType)==1){
            view =  LayoutInflater.from(context).inflate(R.layout.homehotshopitem,parent,false);
            hotShopHolder hotShopHolder = new hotShopHolder(view);
            return hotShopHolder;
        }else if (getItemViewType(viewType)==2){
            view =  LayoutInflater.from(context).inflate(R.layout.homestyleshopitem,parent,false);
            styleShopHolder styleShopHolder = new styleShopHolder(view);
            return styleShopHolder;
        }else{
            view =  LayoutInflater.from(context).inflate(R.layout.homeliveshopitem,parent,false);
            liveShopHolder liveShopHolder = new liveShopHolder(view);
            return liveShopHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position)==0){
            if (blist!=null){
                //((ViewHolder_lun)holder).vp_lun.setImagesUrl(list);
                if (!blist.isEmpty()) {
                    ((BannerHolder)holder).banner.setData(blist, null);
                    ((BannerHolder)holder).banner.loadImage(new XBanner.XBannerAdapter() {
                        @Override
                        public void loadBanner(XBanner banner, Object model, View view, int position) {
                            Glide.with(context).load(blist.get(position).getImageUrl()).into((ImageView) view);
                        }
                    });
                    ((BannerHolder)holder).banner.setPageChangeDuration(1000);
                    ((BannerHolder)holder).banner.setPageTransformer(Transformer.Default);
                }
            }
        }else if (getItemViewType(position)==1){


            ((hotShopHolder)holder).title.setText(slist.get(position-1).name);
            ((hotShopHolder)holder).rv.setLayoutManager(new GridLayoutManager(context,3));
            HomehotItemAdapter homehotItemAdapter = new HomehotItemAdapter(context, slist.get(position - 1).commodityList);
            homehotItemAdapter.setClickitem(this);
            ((hotShopHolder)holder).rv.setAdapter(homehotItemAdapter);

            ((hotShopHolder)holder).most_shop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickitem!=null){
                        clickitem.getName(slist.get(position-1).id);
                    }
                }
            });

        }else if (getItemViewType(position)==2){


            ((styleShopHolder)holder).title.setText(slist.get(position-1).name);
            ((styleShopHolder)holder).rv.setLayoutManager(new LinearLayoutManager(context));
            HomestyleItemAdapter homestyleItemAdapter = new HomestyleItemAdapter(context, slist.get(position - 1).commodityList);
            homestyleItemAdapter.setClickitem(this);
            ((styleShopHolder)holder).rv.setAdapter(homestyleItemAdapter);
            ((styleShopHolder)holder).most_shop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickitem!=null){
                        clickitem.getName(slist.get(position-1).id);
                    }
                }
            });

        }else{

            ((liveShopHolder)holder).title.setText(slist.get(position-1).name);
            ((liveShopHolder)holder).rv.setLayoutManager(new GridLayoutManager(context,2));
            HomeliveItemAdapter homeliveItemAdapter = new HomeliveItemAdapter(context, slist.get(position - 1).commodityList);
            homeliveItemAdapter.setClickitem(this);
            ((liveShopHolder)holder).rv.setAdapter(homeliveItemAdapter);

            ((liveShopHolder)holder).most_shop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickitem!=null){
                        clickitem.getName(slist.get(position-1).id);
                    }
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return slist.size()==0?0:slist.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return 0;
        }else if (position==1&&1002==slist.get(position-1).id){
            return 1;
        }else if (position==2&&1003==slist.get(position-1).id){
            return 2;
        }else{
            return 3;
        }
    }



    class BannerHolder extends RecyclerView.ViewHolder{
        private XBanner banner;
        public BannerHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.vp_lun);
        }
    }

    public class hotShopHolder extends RecyclerView.ViewHolder {
        private RecyclerView rv;
        private TextView title;
        private ImageView most_shop;
        public hotShopHolder(View itemView) {
            super(itemView);
            rv = itemView.findViewById(R.id.rv);
            title = itemView.findViewById(R.id.hot);
            most_shop = itemView.findViewById(R.id.most_shop);

        }
    }

    public class styleShopHolder extends RecyclerView.ViewHolder {
        private RecyclerView rv;
        private TextView title;
        private ImageView most_shop;
        public styleShopHolder(View itemView) {
            super(itemView);
            rv = itemView.findViewById(R.id.rv);
            title = itemView.findViewById(R.id.hot);
            most_shop = itemView.findViewById(R.id.most_shop);
        }
    }

    public class liveShopHolder extends RecyclerView.ViewHolder {
        private RecyclerView rv;
        private TextView title;
        private ImageView most_shop;
        public liveShopHolder(View itemView) {
            super(itemView);
            rv = itemView.findViewById(R.id.rv);
            title = itemView.findViewById(R.id.hot);
            most_shop = itemView.findViewById(R.id.most_shop);
        }
    }

    private onClickitem clickitem;
    public void setClickitem(onClickitem clickitem) {
        this.clickitem = clickitem;
    }
    public interface onClickitem{
        void getName(int id);
    }



    @Override
    public void getName(int id) {

        if (clickitemitem!=null){
            clickitemitem.getitemName(id);
        }


    }
    private onClickitemitem clickitemitem;

    public void setonClickitemitem(onClickitemitem clickitemitem) {
        this.clickitemitem = clickitemitem;
    }

    public interface onClickitemitem{
        void getitemName(int id);
    }


}
