package me.chriskyle.ikan.presentation.module.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import me.chriskyle.library.mvp.MvpBasePresenter
import me.chriskyle.library.mvp.MvpView

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
open class BasePresenter<V : MvpView> : MvpBasePresenter<V>() {

    private val compositeDisposable = CompositeDisposable()

    fun addSubscription(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun detachView() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }

        super.detachView()
    }
}

