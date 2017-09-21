package me.chriskyle.library.mvp

import android.support.annotation.UiThread
import java.lang.ref.WeakReference

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
open class MvpBasePresenter<V : MvpView> : MvpPresenter<V> {

    private var viewRef: WeakReference<V>? = null

    val view: V?
        @UiThread
        get() = if (viewRef == null) null else viewRef!!.get()

    @UiThread
    override fun attachView(mvpView: V) {
        viewRef = WeakReference(mvpView)
    }

    @UiThread
    override fun detachView() {
        if (viewRef != null) {
            viewRef!!.clear()
            viewRef = null
        }
    }
}
