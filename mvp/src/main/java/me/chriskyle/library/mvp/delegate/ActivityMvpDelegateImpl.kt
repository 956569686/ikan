package  me.chriskyle.library.mvp.delegate

import android.app.Activity
import android.os.Bundle
import me.chriskyle.library.mvp.MvpPresenter
import me.chriskyle.library.mvp.MvpView

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
class ActivityMvpDelegateImpl<in V : MvpView,in P : MvpPresenter<V>>
(var activity: Activity, private val delegateCallback: MvpDelegateCallback<V, P>)
    : ActivityMvpDelegate<V, P> {

    override fun onCreate(bundle: Bundle?) {
        delegateCallback.setPresenter(delegateCallback.createPresenter())
        presenter?.attachView(mvpView)
    }

    private val presenter: P?
        get() = delegateCallback.getPresenter()

    private val mvpView: V
        get() = delegateCallback.mvpView

    override fun onDestroy() {
        presenter?.detachView()
    }

    override fun onPause() {}

    override fun onResume() {}

    override fun onStart() {}

    override fun onStop() {}

    override fun onRestart() {}

    override fun onContentChanged() {}

    override fun onSaveInstanceState(outState: Bundle?) {}

    override fun onPostCreate(savedInstanceState: Bundle?) {}
}
