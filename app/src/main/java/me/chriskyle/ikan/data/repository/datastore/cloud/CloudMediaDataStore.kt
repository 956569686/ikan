package me.chriskyle.ikan.data.repository.datastore.cloud

import io.reactivex.Observable
import me.chriskyle.ikan.data.entity.MediaEntity
import me.chriskyle.ikan.data.repository.datastore.MediaDataStore
import me.chriskyle.ikan.data.repository.datastore.cloud.request.interceptor.TokenInterceptor
import me.chriskyle.ikan.data.repository.datastore.cloud.response.ResponseFlatResult
import me.chriskyle.ikan.data.repository.datastore.cloud.service.MediaService
import me.chriskyle.ikan.data.repository.datastore.params.provider.MediaParamProvider
import me.chriskyle.library.net.connection.Connector

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/16.
 */
class CloudMediaDataStore(connector: Connector, tokenInterceptor: TokenInterceptor)
    : BaseCloudDataStore(tokenInterceptor), MediaDataStore {

    private val mediaService: MediaService = connector
            .getServiceCreator()
            .create(MediaService::class.java)

    override fun getRecommendMedias(mediaParamProvider: MediaParamProvider): Observable<List<MediaEntity>> {
        return mediaService.getRecommendMedias(mediaParamProvider.optionalParam.map)
                .flatMap({ dataResponse ->
                    ResponseFlatResult.flatResult(dataResponse)
                })
                .onErrorResumeNext(tokenInterceptor.refreshTokenAndRetry(Observable.defer {
                    mediaService.getRecommendMedias(mediaParamProvider.optionalParam.map)
                            .flatMap({ dataResponse ->
                                ResponseFlatResult.flatResult(dataResponse)
                            })
                }
                ))
    }
}
