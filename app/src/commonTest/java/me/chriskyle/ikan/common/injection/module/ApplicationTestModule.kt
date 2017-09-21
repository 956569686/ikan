package me.chriskyle.ikan.common.injection.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import me.chriskyle.ikan.presentation.di.ApplicationContext
import javax.inject.Singleton

/**
 * Provides application-level dependencies for an app running on a testing environment
 * This allows injecting mocks if necessary.
 */
@Module
class ApplicationTestModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application {
        return application
    }

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return application
    }
}
