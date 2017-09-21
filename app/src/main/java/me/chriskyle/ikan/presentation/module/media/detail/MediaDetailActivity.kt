package me.chriskyle.ikan.presentation.module.media.detail

import android.os.Bundle
import android.support.v7.widget.Toolbar
import butterknife.BindView
import me.chriskyle.ikan.R
import me.chriskyle.ikan.presentation.module.base.BaseActivity
import javax.inject.Inject

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
class MediaDetailActivity : BaseActivity<me.chriskyle.ikan.presentation.module.media.detail.MediaDetailView, me.chriskyle.ikan.presentation.module.media.detail.MediaDetailPresenter>(), me.chriskyle.ikan.presentation.module.media.detail.MediaDetailView {

    @Inject lateinit var mediaDetailPresenter: me.chriskyle.ikan.presentation.module.media.detail.MediaDetailPresenter

    @BindView(R.id.toolbar)
    @JvmField
    var toolbar: Toolbar? = null

    override fun createPresenter(): me.chriskyle.ikan.presentation.module.media.detail.MediaDetailPresenter = mediaDetailPresenter

    override fun initInjection(savedInstanceState: Bundle?) {
        super.initInjection(savedInstanceState)
    }

    override val layout: Int
        get() = R.layout.activity_detail
}