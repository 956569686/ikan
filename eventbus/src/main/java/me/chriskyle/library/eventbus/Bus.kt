package me.chriskyle.library.eventbus

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
interface Bus {

    fun subscribe(observer: Any)

    fun <T : Any> subscribe(observer: Any, subscriber: DefaultSubscriber<T>)

    fun dispose(observer: Any)

    fun post(event: Any)
}
