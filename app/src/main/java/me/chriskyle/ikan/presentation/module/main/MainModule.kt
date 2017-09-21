package me.chriskyle.ikan.presentation.module.main

import dagger.Module
import dagger.Provides
import me.chriskyle.ikan.domain.repository.manager.MediaRepositoryManager
import me.chriskyle.library.eventbus.Bus

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
@Module
class MainModule {

    @Provides
    fun provideMainPresenter(bus: Bus, mediaRepositoryManager: MediaRepositoryManager): MainPresenter {
        return MainPresenterImpl(bus, mediaRepositoryManager)
    }
}