package me.chriskyle.library.mvp.delegate

import android.os.Bundle

import me.chriskyle.library.mvp.MvpPresenter
import me.chriskyle.library.mvp.MvpView

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
interface ActivityMvpDelegate<in V : MvpView, in P : MvpPresenter<V>> {

    fun onCreate(bundle: Bundle?)

    fun onDestroy()

    fun onPause()

    fun onResume()

    fun onStart()

    fun onStop()

    fun onRestart()

    fun onContentChanged()

    fun onSaveInstanceState(outState: Bundle?)

    fun onPostCreate(savedInstanceState: Bundle?)
}
