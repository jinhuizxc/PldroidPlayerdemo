package com.example.jinhui.pldroidplayerdemo.presenter;

import android.content.Context;

import com.example.jinhui.pldroidplayerdemo.api.ApiManager;
import com.example.jinhui.pldroidplayerdemo.base.BasePresenterImpl;
import com.example.jinhui.pldroidplayerdemo.bean.NeteastVideoSummary;
import com.example.jinhui.pldroidplayerdemo.contract.VideoContract;
import com.example.jinhui.pldroidplayerdemo.global.Config;
import com.example.jinhui.pldroidplayerdemo.util.CacheUtil;
import com.google.gson.Gson;


import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by mvp on 2016/9/8.
 */

public class VideoPresenter extends BasePresenterImpl implements VideoContract.IVideoPresenter{
    private VideoContract.IVideoFragment mVideoFragment;
    private CacheUtil mCacheUtil;
    private Gson gson = new Gson();
    private Context context;

    public VideoPresenter(Context context, VideoContract.IVideoFragment mVideoFragment) {
        this.mVideoFragment = mVideoFragment;
        mCacheUtil = CacheUtil.get(context);
        this.context = context;
    }


    @Override
    public void getVideoData(String id, int startPage) {
        mVideoFragment.showProgressDialog();
        Subscription subscribe = ApiManager.getInstence().getVideoService().getVideoList(id,startPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NeteastVideoSummary>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mVideoFragment.hidProgressDialog();
                        mVideoFragment.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(NeteastVideoSummary videoData) {
                        mVideoFragment.hidProgressDialog();
                        mCacheUtil.put(Config.VIDEO, gson.toJson(videoData));
                        mVideoFragment.updateVideoData(videoData);
                    }
                });
        addSubscription(subscribe);
    }
}
