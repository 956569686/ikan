package me.chriskyle.ikan.presentation.module.media.detail

import dagger.Module
import dagger.Provides

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
@Module
internal class MediaDetailModule {

    @Provides
    fun providerDetailPresenter(): me.chriskyle.ikan.presentation.module.media.detail.MediaDetailPresenter {
        return me.chriskyle.ikan.presentation.module.media.detail.MediaDetailPresenterImpl()
    }
}
