package me.chriskyle.ikan.presentation.module.base

import android.os.Bundle
import android.support.v4.util.LongSparseArray
import butterknife.ButterKnife
import me.chriskyle.ikan.presentation.IkanApplication
import me.chriskyle.ikan.presentation.di.component.ActivityComponent
import me.chriskyle.ikan.presentation.di.component.ConfigPersistentComponent
import me.chriskyle.ikan.presentation.di.component.DaggerConfigPersistentComponent
import me.chriskyle.ikan.presentation.di.module.ActivityModule
import me.chriskyle.library.mvp.MvpActivity
import me.chriskyle.library.mvp.MvpPresenter
import me.chriskyle.library.mvp.MvpView
import me.chriskyle.library.toolkit.log.Logger
import java.util.concurrent.atomic.AtomicLong

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
abstract class BaseActivity<V : MvpView, P : MvpPresenter<V>> : MvpActivity<V, P>() {

    private var activityComponent: ActivityComponent? = null
    private var activityId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        initInjection(savedInstanceState)

        super.onCreate(savedInstanceState)

        setContentView(layout)
        ButterKnife.bind(this)
    }

    abstract val layout: Int

    open fun initInjection(savedInstanceState: Bundle?) {
        activityId = savedInstanceState?.getLong(KEY_ACTIVITY_ID) ?: NEXT_ID.getAndIncrement()
        val configPersistentComponent: ConfigPersistentComponent
        if (sComponentsArray.get(activityId) == null) {
            Logger.i("Creating new ConfigPersistentComponent id=%d", activityId)
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(IkanApplication[this].component)
                    .build()
            sComponentsArray.put(activityId, configPersistentComponent)
        } else {
            Logger.i("Reusing ConfigPersistentComponent id=%d", activityId)
            configPersistentComponent = sComponentsArray.get(activityId)
        }
        activityComponent = configPersistentComponent.activityComponent(ActivityModule(this))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_ACTIVITY_ID, activityId)
    }

    override fun onDestroy() {
        if (!isChangingConfigurations) {
            Logger.i("Clearing ConfigPersistentComponent id=%d", activityId)
            sComponentsArray.remove(activityId)
        }
        super.onDestroy()
    }

    fun activityComponent() = activityComponent as ActivityComponent

    companion object {

        private val KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID"
        private val NEXT_ID = AtomicLong(0)
        private val sComponentsArray = LongSparseArray<ConfigPersistentComponent>()
    }
}
