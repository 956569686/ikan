package me.chriskyle.ikan.presentation.di.component

import me.chriskyle.ikan.presentation.di.PerFragment
import me.chriskyle.ikan.presentation.di.module.FragmentModule
import dagger.Subcomponent

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
@PerFragment
@Subcomponent(modules = arrayOf(FragmentModule::class))
interface FragmentComponent