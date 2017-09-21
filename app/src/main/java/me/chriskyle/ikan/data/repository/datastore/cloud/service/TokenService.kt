package me.chriskyle.ikan.data.repository.datastore.cloud.service

import io.reactivex.Observable
import me.chriskyle.ikan.data.entity.TokenEntity
import me.chriskyle.library.net.response.DataResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/16.
 */
interface TokenService {

    @GET("/user/token/refreshToken")
    fun refreshToken(@QueryMap queryMap: Map<String, String>): Observable<DataResponse<TokenEntity>>

    @GET("/user/deviceToken/bind")
    fun bindDeviceToken(@QueryMap queryMap: Map<String, String>): Observable<DataResponse<Any>>

    @GET("/user/deviceToken/unbind")
    fun ubBindDeviceToken(@QueryMap queryMap: Map<String, String>): Observable<DataResponse<Any>>
}
