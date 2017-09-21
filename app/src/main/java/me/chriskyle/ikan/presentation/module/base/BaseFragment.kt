package me.chriskyle.ikan.presentation.module.base

import me.chriskyle.ikan.presentation.IkanApplication
import me.chriskyle.ikan.presentation.di.component.ConfigPersistentComponent
import me.chriskyle.ikan.presentation.di.component.DaggerConfigPersistentComponent
import me.chriskyle.ikan.presentation.di.component.FragmentComponent
import me.chriskyle.ikan.presentation.di.module.FragmentModule
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.util.LongSparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import me.chriskyle.library.toolkit.log.Logger
import java.util.concurrent.atomic.AtomicLong

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
abstract class BaseFragment : Fragment() {

    private var mFragmentComponent: FragmentComponent? = null
    private var mFragmentId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the FragmentComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        mFragmentId = savedInstanceState?.getLong(KEY_FRAGMENT_ID) ?: NEXT_ID.getAndIncrement()
        val configPersistentComponent: ConfigPersistentComponent
        if (sComponentsArray.get(mFragmentId) == null) {
            Logger.i("Creating new ConfigPersistentComponent id=%d", mFragmentId)
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(IkanApplication[activity].component)
                    .build()
            sComponentsArray.put(mFragmentId, configPersistentComponent)
        } else {
            Logger.i("Reusing ConfigPersistentComponent id=%d", mFragmentId)
            configPersistentComponent = sComponentsArray.get(mFragmentId)
        }
        mFragmentComponent = configPersistentComponent.fragmentComponent(FragmentModule(this))
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View? = inflater?.inflate(layout, container, false)
        ButterKnife.bind(this, view as View)
        return view
    }

    abstract val layout: Int

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putLong(KEY_FRAGMENT_ID, mFragmentId)
    }

    override fun onDestroy() {
        if (!activity.isChangingConfigurations) {
            Logger.i("Clearing ConfigPersistentComponent id=%d", mFragmentId)
            sComponentsArray.remove(mFragmentId)
        }
        super.onDestroy()
    }

    fun fragmentComponent(): FragmentComponent {
        return mFragmentComponent as FragmentComponent
    }

    companion object {

        private val KEY_FRAGMENT_ID = "KEY_FRAGMENT_ID"
        private val sComponentsArray = LongSparseArray<ConfigPersistentComponent>()
        private val NEXT_ID = AtomicLong(0)
    }
}
