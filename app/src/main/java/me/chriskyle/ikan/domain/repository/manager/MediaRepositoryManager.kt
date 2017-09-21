package me.chriskyle.ikan.domain.repository.manager

import io.reactivex.Observer
import io.reactivex.schedulers.Schedulers
import me.chriskyle.ikan.data.entity.MediaEntity
import me.chriskyle.ikan.data.repository.MediaDataRepository
import me.chriskyle.ikan.data.repository.datastore.params.provider.MediaParamProvider
import me.chriskyle.ikan.domain.executor.PostExecutionThread
import me.chriskyle.ikan.domain.executor.ThreadExecutor
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/17.
 */
@Singleton
class MediaRepositoryManager @Inject constructor(threadExecutor: ThreadExecutor,
                                                 postExecutionThread: PostExecutionThread,
                                                 private var mediaDataRepository: MediaDataRepository)
    : BaseRepositoryManager(threadExecutor, postExecutionThread) {

    fun executeLoadRecommendMedias(mediaParamProvider: MediaParamProvider, subscriber: Observer<List<MediaEntity>>) {
        mediaDataRepository.getRecommendMedias(mediaParamProvider)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler)
                .subscribe(subscriber)
    }
}