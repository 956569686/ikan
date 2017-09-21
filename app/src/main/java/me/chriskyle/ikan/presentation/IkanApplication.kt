package me.chriskyle.ikan.presentation

import android.content.Context
import android.support.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import me.chriskyle.ikan.BuildConfig
import me.chriskyle.ikan.presentation.di.component.ApplicationComponent
import me.chriskyle.ikan.presentation.di.component.DaggerApplicationComponent
import me.chriskyle.ikan.presentation.di.module.ApplicationModule
import me.chriskyle.ikan.presentation.di.module.NetworkModule
import me.chriskyle.library.toolkit.log.*

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
class IkanApplication : MultiDexApplication() {

    private var applicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
            LeakCanary.install(this)
        }

        initLogger()
    }

    var component: ApplicationComponent
        get() {
            if (applicationComponent == null) {
                applicationComponent = DaggerApplicationComponent.builder()
                        .applicationModule(ApplicationModule(this))
                        .networkModule(NetworkModule(this))
                        .build()
            }
            return applicationComponent as ApplicationComponent
        }
        set(applicationComponent) {
            this.applicationComponent = applicationComponent
        }

    private fun initLogger() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(0)
                .methodOffset(7)
                .build()
        Logger.addLogAdapter(LogAdapter(formatStrategy))
    }

    companion object {

        operator fun get(context: Context): IkanApplication {
            return context.applicationContext as IkanApplication
        }
    }

    private class LogAdapter(formatStrategy: FormatStrategy) : AndroidLogAdapter(formatStrategy) {

        override fun isLoggable(priority: Int, tag: String?): Boolean {
            return BuildConfig.DEBUG
        }
    }
}
