package com.tarena.app.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tarena.app.R;
import com.tarena.app.entity.Store;
import com.tarena.app.manager.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class StoreDetailsActivity extends AppCompatActivity {

    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.iv_store_pic)
    Banner banner;
    @BindView(R.id.tv_store_title)
    TextView tvStoreTitle;
    @BindView(R.id.tv_store_score)
    TextView tvStoreScore;
    @BindView(R.id.tv_store_money)
    TextView tvStoreMoney;
    @BindView(R.id.tv_store_uploader)
    TextView tvStoreUploader;
    @BindView(R.id.tv_store_des)
    TextView tvStoreDes;


    Store store;
    AlertDialog exitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details);
        ButterKnife.bind(this);
        store = (Store) getIntent().getSerializableExtra("store");
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(store.getImagePaths());
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
//        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        setData();

        setData();
    }

    @OnClick(R.id.tv_cancel)
    public void back(){
        StoreDetailsActivity.this.finish();
    }

    @OnClick(R.id.tv_delete)
    public void delete(){
        getExitDialog().show();
    }


    private void setData(){
        tvStoreTitle.setText(store.getTitle());
        tvStoreDes.setText(store.getDes());
        tvStoreMoney.setText(store.getMoney()+"金币");
        tvStoreScore.setText(store.getScore()+"积分");
        tvStoreUploader.setText(store.getUser().getNick()+"老师");


    }


    private AlertDialog getExitDialog() {
        if (exitDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("您确定要删除该商品吗？");
            builder.setCancelable(false);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                   store.delete(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            StoreDetailsActivity.this.finish();
                        }
                   });
                }
            });
            builder.setNegativeButton("取消", null);

            exitDialog = builder.create();
        }

        return exitDialog;
    }
}
