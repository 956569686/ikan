package me.chriskyle.library.mvp

import android.support.annotation.UiThread

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
interface MvpPresenter<in V : MvpView> {

    @UiThread
    fun attachView(mvpView: V)

    @UiThread
    fun detachView()
}
