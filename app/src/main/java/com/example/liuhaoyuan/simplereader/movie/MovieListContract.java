package com.example.liuhaoyuan.simplereader.movie;

import com.example.liuhaoyuan.simplereader.base.BaseModel;
import com.example.liuhaoyuan.simplereader.base.BasePresenter;
import com.example.liuhaoyuan.simplereader.base.BaseView;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public interface MovieListContract {
    public interface View extends BaseView{
    }

    public interface Model extends BaseModel{

    }

    public abstract class Presenter extends BasePresenter<View,Model>{
    }
}
