package me.chriskyle.ikan.presentation.di.component

import dagger.Subcomponent
import me.chriskyle.ikan.presentation.di.PerActivity
import me.chriskyle.ikan.presentation.di.module.ActivityModule
import me.chriskyle.ikan.presentation.module.main.MainComponent
import me.chriskyle.ikan.presentation.module.main.MainModule

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun mainComponent(mainModule: MainModule): MainComponent
}