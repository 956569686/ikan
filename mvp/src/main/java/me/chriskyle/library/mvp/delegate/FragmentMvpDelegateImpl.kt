package me.chriskyle.library.mvp.delegate

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import me.chriskyle.library.mvp.MvpPresenter
import me.chriskyle.library.mvp.MvpView

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
class FragmentMvpDelegateImpl<in V : MvpView, P : MvpPresenter<V>>
(var fragment: Fragment, private val delegateCallback: MvpDelegateCallback<V, P>)
    : FragmentMvpDelegate<V, P> {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        delegateCallback.setPresenter(delegateCallback.createPresenter())
        delegateCallback.getPresenter()?.attachView(mvpView)
    }

    private val presenter: P?
        get() = delegateCallback.getPresenter()

    private val mvpView: V
        get() = delegateCallback.mvpView

    override fun onDestroyView() {
        val presenter = presenter
        presenter?.detachView()
    }

    override fun onPause() {}

    override fun onResume() {}

    override fun onStart() {
    }

    override fun onStop() {}

    override fun onActivityCreated(savedInstanceState: Bundle?) {}

    override fun onAttach(activity: Activity) {}

    override fun onDetach() {}

    override fun onSaveInstanceState(outState: Bundle?) {
    }

    override fun onCreate(saved: Bundle) {}

    override fun onDestroy() {}
}
