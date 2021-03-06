package me.chriskyle.ikan.domain.exception

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/18.
 */
class DefaultErrorBundle(override val exception: Exception?) : ErrorBundle {

    override val errorMessage: String?
        get() = if (exception != null) this.exception.message else DEFAULT_ERROR_MSG

    companion object {

        private val DEFAULT_ERROR_MSG = "Unknown error"
    }
}
