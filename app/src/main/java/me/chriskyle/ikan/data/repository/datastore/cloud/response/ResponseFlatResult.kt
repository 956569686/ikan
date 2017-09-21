package me.chriskyle.ikan.data.repository.datastore.cloud.response

import io.reactivex.Observable
import me.chriskyle.ikan.data.exception.UnKnownException
import me.chriskyle.library.net.response.DataResponse

/**
 * Description :
 *
 * Created by Chris Kyle on 2017/9/16.
 */
object ResponseFlatResult {

    fun <T : Any> flatResult(result: DataResponse<T>): Observable<T> {
        return Observable.create { observableEmitter ->
            when (result.status) {
                ResponseStatus.SUCCESS -> {
                    observableEmitter.onNext(result.data as T)
                    observableEmitter.onComplete()
                }
                ResponseStatus.UN_KNOWN_EXCEPTION -> {
                    observableEmitter.onError(UnKnownException())
                }
            }
        }
    }
}
