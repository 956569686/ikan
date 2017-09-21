package me.chriskyle.ikan.data.repository.datastore.factory

import me.chriskyle.ikan.data.repository.datastore.MediaDataStore
import me.chriskyle.ikan.data.repository.datastore.cloud.CloudMediaDataStore
import me.chriskyle.ikan.data.repository.datastore.cloud.request.interceptor.TokenInterceptor
import me.chriskyle.library.net.connection.Connector
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/19.
 */
@Singleton
class MediaDataStoreFactory @Inject internal constructor(private val apiConnector: Connector,
                                                         private val tokenInterceptor: TokenInterceptor) {

    fun createCloudDataStore(): MediaDataStore {
        return CloudMediaDataStore(apiConnector, tokenInterceptor)
    }
}