package me.chriskyle.ikan.domain.repository

import io.reactivex.Observable
import me.chriskyle.ikan.data.entity.MediaEntity
import me.chriskyle.ikan.data.repository.datastore.params.provider.MediaParamProvider

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/16.
 */
interface MediaRepository {

    fun getRecommendMedias(mediaParamProvider: MediaParamProvider): Observable<List<MediaEntity>>
}