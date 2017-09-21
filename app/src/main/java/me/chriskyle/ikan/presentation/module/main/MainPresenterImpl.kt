package me.chriskyle.ikan.presentation.module.main

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import me.chriskyle.ikan.data.entity.MediaEntity
import me.chriskyle.ikan.data.repository.datastore.params.provider.MediaParamProvider
import me.chriskyle.ikan.domain.observer.DefaultObserver
import me.chriskyle.ikan.domain.repository.manager.MediaRepositoryManager
import me.chriskyle.ikan.presentation.event.ReSignInEvent
import me.chriskyle.ikan.presentation.module.base.BasePresenter
import me.chriskyle.library.eventbus.Bus

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/16.
 */
class MainPresenterImpl(private val bus: Bus, private val mediaRepositoryManager: MediaRepositoryManager)
    : BasePresenter<MainView>(), MainPresenter {

    init {
        initSubscription()
    }

    override fun loadRecommendMedias() {
        mediaRepositoryManager.executeLoadRecommendMedias(MediaParamProvider(), LoadRecommendMediasObserver())
    }

    private fun initSubscription() {
        bus.subscribe(ReSignInObserver())
    }

    private fun reSignIn() {
    }

    private inner class LoadRecommendMediasObserver : DefaultObserver<List<MediaEntity>>() {

        override fun onComplete() {
        }

        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: List<MediaEntity>) {
        }

        override fun onError(e: Throwable) {
        }
    }

    private inner class ReSignInObserver : DefaultObserver<ReSignInEvent>() {

        override fun onNext(t: ReSignInEvent) {
            reSignIn()
        }

        override fun onSubscribe(d: Disposable) {
            addSubscription(d)
        }
    }
}