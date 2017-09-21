package me.chriskyle.ikan.data.repository

import io.reactivex.Observable
import me.chriskyle.ikan.data.entity.MediaEntity
import me.chriskyle.ikan.data.repository.datastore.factory.MediaDataStoreFactory
import me.chriskyle.ikan.data.repository.datastore.params.provider.MediaParamProvider
import me.chriskyle.ikan.domain.repository.MediaRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/16.
 */
@Singleton
class MediaDataRepository @Inject constructor(private var mediaDataStoreFactory: MediaDataStoreFactory)
    : MediaRepository {

    override fun getRecommendMedias(mediaParamProvider: MediaParamProvider): Observable<List<MediaEntity>> {
        return mediaDataStoreFactory.createCloudDataStore().getRecommendMedias(mediaParamProvider)
    }
}