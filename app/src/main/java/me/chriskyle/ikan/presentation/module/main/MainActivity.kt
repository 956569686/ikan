package me.chriskyle.ikan.presentation.module.main

import android.os.Bundle
import butterknife.BindView
import me.chriskyle.ikan.R
import me.chriskyle.ikan.presentation.module.base.BaseActivity
import me.chriskyle.library.support.bottomnavigation.BottomNavigationBar
import javax.inject.Inject

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
class MainActivity : BaseActivity<MainView, MainPresenter>(), MainView {

    @Inject lateinit var mainPresenter: MainPresenter

    @BindView(R.id.bottom_nav_bar)
    @JvmField
    var bottomNavigationBar: BottomNavigationBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainPresenter.loadRecommendMedias()
    }

    override val layout: Int
        get() = R.layout.activity_main

    override fun initInjection(savedInstanceState: Bundle?) {
        super.initInjection(savedInstanceState)

        activityComponent().mainComponent(MainModule()).inject(this)
    }

    override fun createPresenter() = mainPresenter
}