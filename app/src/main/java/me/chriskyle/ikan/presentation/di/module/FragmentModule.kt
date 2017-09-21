package me.chriskyle.ikan.presentation.di.module

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment

import dagger.Module
import dagger.Provides
import me.chriskyle.ikan.presentation.di.ActivityContext

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
    internal fun providesFragment(): Fragment {
        return fragment
    }

    @Provides
    internal fun provideActivity(): Activity {
        return fragment.activity
    }

    @Provides
    @ActivityContext
    internal fun providesContext(): Context {
        return fragment.activity
    }
}