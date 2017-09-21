package me.chriskyle.library.eventbus

import java.lang.reflect.Method

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
internal class AnnotatedSubscriber<T : Any>(private var observer: Any?, private var method: Method?)
    : AbstractSubscriber<T>() {

    private val hashCode: Int = 31 * observer!!.hashCode() + method!!.hashCode()

    @Throws(Exception::class)
    override fun acceptEvent(event: T) {
        method!!.invoke(observer, event)
    }

    override fun release() {
        observer = null
        method = null
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        val that = other as AnnotatedSubscriber<*>?

        return observer == that!!.observer && method == that.method
    }

    override fun hashCode(): Int {
        return hashCode
    }
}
