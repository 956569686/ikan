package me.chriskyle.ikan.presentation.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import me.chriskyle.ikan.domain.executor.PostExecutionThread
import me.chriskyle.ikan.domain.executor.ThreadExecutor
import me.chriskyle.ikan.domain.executor.impl.JobExecutor
import me.chriskyle.ikan.domain.executor.impl.UIThread
import me.chriskyle.ikan.domain.repository.manager.MediaRepositoryManager
import me.chriskyle.ikan.presentation.di.ApplicationContext
import me.chriskyle.library.eventbus.Bus
import me.chriskyle.library.eventbus.BusProvider
import javax.inject.Singleton

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
@Module(includes = arrayOf(NetworkModule::class))
class ApplicationModule(private val application: Application) {

    @Provides
    internal fun provideApplication() = application

    @Provides
    @ApplicationContext
    internal fun provideContext() = application.applicationContext

    @Singleton
    @Provides
    internal fun provideEventBus(): Bus = BusProvider.instance

    @Singleton
    @Provides
    internal fun provideThreadExecutor(): ThreadExecutor = JobExecutor()

    @Singleton
    @Provides
    internal fun provideUiThread(): PostExecutionThread = UIThread()

    @Singleton
    @Provides
    internal fun provideMediaRepositoryManager(manager: MediaRepositoryManager): MediaRepositoryManager = manager
}
