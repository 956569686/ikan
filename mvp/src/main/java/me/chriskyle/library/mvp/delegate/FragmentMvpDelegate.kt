package me.chriskyle.library.mvp.delegate

import android.app.Activity
import android.os.Bundle
import android.view.View

import me.chriskyle.library.mvp.MvpPresenter
import me.chriskyle.library.mvp.MvpView

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
interface FragmentMvpDelegate<in V : MvpView, P : MvpPresenter<V>> {

    fun onCreate(saved: Bundle)

    fun onDestroy()

    fun onViewCreated(view: View, savedInstanceState: Bundle?)

    fun onDestroyView()

    fun onPause()

    fun onResume()

    fun onStart()

    fun onStop()

    fun onActivityCreated(savedInstanceState: Bundle?)

    fun onAttach(activity: Activity)

    fun onDetach()

    fun onSaveInstanceState(outState: Bundle?)
}
