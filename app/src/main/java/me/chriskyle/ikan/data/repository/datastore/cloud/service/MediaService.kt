package me.chriskyle.ikan.data.repository.datastore.cloud.service

import io.reactivex.Observable
import me.chriskyle.ikan.data.entity.MediaEntity
import me.chriskyle.library.net.response.DataResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/16.
 */
interface MediaService {

    @GET("/media/recommend")
    fun getRecommendMedias(@QueryMap queryMap: Map<String, String>): Observable<DataResponse<List<MediaEntity>>>
}
