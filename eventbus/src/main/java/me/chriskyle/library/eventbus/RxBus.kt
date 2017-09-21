package me.chriskyle.library.eventbus

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.internal.functions.ObjectHelper
import io.reactivex.subjects.PublishSubject
import java.lang.reflect.Modifier
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArraySet

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/15.
 */
internal class RxBus : Bus {

    private val OBSERVERS = ConcurrentHashMap<Class<*>, CompositeDisposable>()
    private val SUBSCRIBERS = ConcurrentHashMap<Class<*>, CopyOnWriteArraySet<DefaultSubscriber<*>>>()

    private val bus = PublishSubject.create<Any>().toSerialized()

    override fun subscribe(observer: Any) {
        val observerClass = observer.javaClass

        if (OBSERVERS.putIfAbsent(observerClass, CompositeDisposable()) != null)
            throw IllegalArgumentException("Observer has already been registered.")

        val composite = OBSERVERS[observerClass]

        val events = HashSet<Class<*>>()

        for (method in observerClass.declaredMethods) {
            if (method.isBridge || method.isSynthetic)
                continue

            if (!method.isAnnotationPresent(Subscribe::class.java))
                continue

            val mod = method.modifiers

            if (Modifier.isStatic(mod) || !Modifier.isPublic(mod))
                throw IllegalArgumentException("Method " + method.name +
                        " has @Subscribe annotation must be public, non-static")

            val params = method.parameterTypes

            if (params.size != 1)
                throw IllegalArgumentException("Method " + method.name +
                        " has @Subscribe annotation must require a single argument")

            val eventClass = params[0]

            if (eventClass.isInterface)
                throw IllegalArgumentException("Event class must be on a concrete class type.")

            if (!events.add(eventClass))
                throw IllegalArgumentException("Subscribe for " + eventClass.simpleName +
                        " has already been registered.")

            composite!!.add(bus.ofType(eventClass)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(AnnotatedSubscriber<Any>(observer, method)))
        }
    }

    override fun <T : Any> subscribe(observer: Any, subscriber: DefaultSubscriber<T>) {
        ObjectHelper.requireNonNull(observer, "Observer to subscribe must not be null.")
        ObjectHelper.requireNonNull(subscriber, "Subscribe to subscribe must not be null.")

        (SUBSCRIBERS).putIfAbsent(observer.javaClass, CopyOnWriteArraySet())
        val subscribers = SUBSCRIBERS[observer.javaClass]
        if (subscribers!!.contains(subscriber))
            throw IllegalArgumentException("Subscribe has already been registered.")
        else
            subscribers.add(subscriber)

        val observable = bus.ofType(subscriber.eventClass)
                .observeOn(if (subscriber.scheduler == null)
                    AndroidSchedulers.mainThread()
                else
                    subscriber.scheduler)

        val observerClass = observer.javaClass

        OBSERVERS.putIfAbsent(observerClass, CompositeDisposable())
        val composite = OBSERVERS[observerClass]

        composite!!.add(observable.subscribe(subscriber))
    }

    override fun dispose(observer: Any) {
        ObjectHelper.requireNonNull(observer, "Observer to dispose must not be null.")
        val composite = OBSERVERS[observer.javaClass]
        ObjectHelper.requireNonNull<CompositeDisposable>(composite, "Missing observer, it was registered?")
        composite!!.dispose()
        OBSERVERS.remove(observer.javaClass)

        val subscribers = SUBSCRIBERS[observer.javaClass]
        if (subscribers != null) {
            subscribers.clear()
            SUBSCRIBERS.remove(observer.javaClass)
        }
    }

    override fun post(event: Any) {
        ObjectHelper.requireNonNull(event, "Event must not be null.")
        bus.onNext(event)
    }
}
