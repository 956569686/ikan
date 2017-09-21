package me.chriskyle.ikan.presentation.module.media.detail

import dagger.Component

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
@Component(modules = arrayOf(me.chriskyle.ikan.presentation.module.media.detail.MediaDetailModule::class))
interface MediaDetailComponent {

    fun inject(detailActivity: me.chriskyle.ikan.presentation.module.media.detail.MediaDetailActivity)
}