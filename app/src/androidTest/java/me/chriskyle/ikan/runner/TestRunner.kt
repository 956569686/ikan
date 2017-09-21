package me.chriskyle.ikan.runner

import me.chriskyle.ikan.presentation.IkanApplication
import android.app.Application
import android.content.Context
import io.appflate.restmock.android.RESTMockTestRunner

class TestRunner : RESTMockTestRunner() {

    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, IkanApplication::class.java.name, context)
    }
}
