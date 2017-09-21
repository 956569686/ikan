package me.chriskyle.ikan.data.repository.datastore.cloud.request.interceptor

import android.content.Context
import android.text.TextUtils
import me.chriskyle.ikan.data.entity.TokenEntity
import me.chriskyle.ikan.data.repository.datastore.cloud.request.param.Constants
import me.chriskyle.ikan.data.repository.datastore.local.cache.TokenCache
import me.chriskyle.library.net.request.interceptor.BaseNetworkRequestInterceptor
import me.chriskyle.library.toolkit.utils.ManifestUtils
import okhttp3.HttpUrl

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/19.
 */
class NetworkRequestInterceptor(private val context: Context, private val tokenCache: TokenCache)
    : BaseNetworkRequestInterceptor() {

    private var tokenEntity: TokenEntity? = null

    override fun wrapperInjectParams(params: MutableMap<String, String>): Map<String, String> {
        tokenEntity = tokenCache.get()
        if (tokenEntity != null && !TextUtils.isEmpty(tokenEntity?.token)) {
            params[Constants.KPI.TOKEN] = tokenEntity?.token!!
        }

        params[Constants.KPI.VERSION_CODE] = ManifestUtils.getVersionCode(context).toString()
        params[Constants.KPI.VERSION_NAME] = ManifestUtils.getVersionName(context).toString()

        return params
    }

    override fun signHttpUrlBuilder(signHttpUrlBuilder: HttpUrl.Builder, urlParamsMap: Map<String, String>)
            : HttpUrl.Builder {
        return signHttpUrlBuilder
    }
}
