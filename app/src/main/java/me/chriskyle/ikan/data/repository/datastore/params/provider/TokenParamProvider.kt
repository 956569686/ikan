package me.chriskyle.ikan.data.repository.datastore.params.provider

import me.chriskyle.ikan.data.repository.datastore.params.Constants
import me.chriskyle.library.net.request.param.SimpleParamProvider

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/16.
 */
class TokenParamProvider : SimpleParamProvider() {

    fun reToken(token: String): TokenParamProvider {
        append(TOKEN, token)
        return this
    }

    companion object {

        private val TOKEN = Constants.API.TOKEN
    }
}
