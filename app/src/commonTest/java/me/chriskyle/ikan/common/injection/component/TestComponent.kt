package me.chriskyle.ikan.common.injection.component

import me.chriskyle.ikan.common.injection.module.ApplicationTestModule
import me.chriskyle.ikan.presentation.di.component.ApplicationComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationTestModule::class))
interface TestComponent : ApplicationComponent