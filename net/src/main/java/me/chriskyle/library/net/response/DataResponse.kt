package me.chriskyle.library.net.response

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/16.
 */
class DataResponse<T> {

    var status: Int = 0
    var msg: String? = null
    var data: T? = null
}
