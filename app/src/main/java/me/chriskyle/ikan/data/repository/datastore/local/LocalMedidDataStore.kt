package me.chriskyle.ikan.data.repository.datastore.local

import io.reactivex.Observable
import me.chriskyle.ikan.data.entity.MediaEntity
import me.chriskyle.ikan.data.repository.datastore.MediaDataStore
import me.chriskyle.ikan.data.repository.datastore.params.provider.MediaParamProvider

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/16.
 */
class LocalMedidDataStore : MediaDataStore {

    override fun getRecommendMedias(mediaParamProvider: MediaParamProvider): Observable<List<MediaEntity>> {
        return Observable.create { }
    }
}