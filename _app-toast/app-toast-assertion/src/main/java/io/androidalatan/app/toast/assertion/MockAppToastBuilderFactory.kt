package io.androidalatan.app.toast.assertion

import io.androidalatan.app.toast.api.AppToastBuilder
import io.androidalatan.app.toast.api.AppToastBuilderFactory

class MockAppToastBuilderFactory : AppToastBuilderFactory {
    var createCount = 0
    var createdBuilder: MockAppToastBuilder? = null

    override fun create(): AppToastBuilder {
        createCount++
        createdBuilder = MockAppToastBuilder()
        return createdBuilder!!
    }
}