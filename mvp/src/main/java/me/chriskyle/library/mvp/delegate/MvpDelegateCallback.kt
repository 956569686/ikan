package me.chriskyle.library.mvp.delegate

import me.chriskyle.library.mvp.MvpPresenter
import me.chriskyle.library.mvp.MvpView

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
interface MvpDelegateCallback<V : MvpView, P : MvpPresenter<V>> {

    fun createPresenter(): P

    fun getPresenter(): P?

    fun setPresenter(p: P)

    val mvpView: V
}

