package me.chriskyle.library.net.request.param

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/16.
 */
open class SimpleParamProvider : ParamProvider {

    override var optionalParam: OptionalParam = OptionalParam()

    fun append(key: String?, value: String) {
        if (null != key) {
            optionalParam.put(key, value)
        }
    }
}
