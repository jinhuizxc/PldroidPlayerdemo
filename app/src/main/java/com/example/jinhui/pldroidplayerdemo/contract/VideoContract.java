package com.example.jinhui.pldroidplayerdemo.contract;


import com.example.jinhui.pldroidplayerdemo.base.BasePresenter;
import com.example.jinhui.pldroidplayerdemo.base.IBaseFragment;
import com.example.jinhui.pldroidplayerdemo.bean.NeteastVideoSummary;

/**
 * Created by mvp on 2016/9/12.
 */

public interface VideoContract {

    public interface IVideoPresenter extends BasePresenter {
        /**
         * 获取最新的日报数据
         *
         * @return
         */
        void getVideoData(String id, int startPage);
    }

    public interface IVideoFragment extends IBaseFragment {

        void updateVideoData(NeteastVideoSummary videoData);
    }
}
