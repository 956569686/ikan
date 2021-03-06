package me.chriskyle.library.mvp

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import me.chriskyle.library.mvp.delegate.FragmentMvpDelegate
import me.chriskyle.library.mvp.delegate.FragmentMvpDelegateImpl
import me.chriskyle.library.mvp.delegate.MvpDelegateCallback

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
abstract class MvpFragment<V : MvpView, P : MvpPresenter<V>> : Fragment(),
        MvpDelegateCallback<V, P>, MvpView {

    private var mvpDelegate: FragmentMvpDelegate<V, P>? = null

    override val mvpView: V
        get() = this as V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mvpDelegate?.onCreate(savedInstanceState!!)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        mvpDelegate?.onAttach(activity!!)
    }

    override fun onStart() {
        super.onStart()
        mvpDelegate?.onStart()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mvpDelegate = FragmentMvpDelegateImpl(this, this)
        mvpDelegate?.onViewCreated(view!!, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mvpDelegate?.onActivityCreated(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        mvpDelegate?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mvpDelegate?.onPause()
    }

    override fun onStop() {
        super.onStop()
        mvpDelegate?.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mvpDelegate?.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        mvpDelegate?.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
        mvpDelegate?.onDetach()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mvpDelegate?.onSaveInstanceState(outState)
    }

    abstract override fun createPresenter(): P
}

