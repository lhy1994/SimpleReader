package com.example.liuhaoyuan.simplereader.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by liuhaoyuan on 17/4/23.
 */

public abstract class BasePresenter {
    protected CompositeDisposable mCompositeDisposable;

    public void addDisposable(Disposable disposable){
        if (mCompositeDisposable==null){
            mCompositeDisposable=new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    public void clearDisposable(){
        if (mCompositeDisposable!=null){
            mCompositeDisposable.clear();
        }
    }

}