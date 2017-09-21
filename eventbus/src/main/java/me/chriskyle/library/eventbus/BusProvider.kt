package me.chriskyle.library.eventbus

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
object BusProvider {

    val instance: Bus
        get() = BusHolder.INSTANCE

    private object BusHolder {

        internal val INSTANCE: Bus = RxBus()
    }
}
