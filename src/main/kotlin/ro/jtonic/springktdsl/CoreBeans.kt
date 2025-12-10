package ro.jtonic.springktdsl

import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.beans.factory.BeanRegistrarDsl
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.coRouter
import org.springframework.web.reactive.function.server.RequestPredicates as ReactiveRequestPredicates
import org.springframework.web.reactive.function.server.ServerRequest as ReactiveServerRequest
import org.springframework.web.reactive.function.server.ServerResponse as ReactiveServerResponse

class CoreBeans : BeanRegistrarDsl({
    register(BusinessBeans())
    registerBean<CoreFoo>()
    registerBean(
        name = "bar",
        prototype = true,
        lazyInit = true,
        description = "Custom description") {
        CoreBar(bean<CoreFoo>(), bean<BusinessFoo>())
    }
    profile("baz") {
        registerBean { CoreBaz("Hello World!") }
    }

    registerBean<List<CoreFoo>>() { listOf(CoreFoo())}
    registerBean<List<CoreBar>> { listOf(CoreBar(bean(), bean()))}
    registerBean<TypeParamBean> { TypeParamBean(bean<List<CoreFoo>>(), bean<List<CoreBar>>()) }

    registerBean<UserHandler>()
    registerBean() {
        val userHandler = bean<UserHandler>()
        coRouter {
            GET(pattern = "/api/users/", predicate = ReactiveRequestPredicates.accept(APPLICATION_JSON), f = userHandler::findById)
        }
    }
})

data class User(val id: Long, val name: String)

class UserHandler {
    suspend fun findById(req: ReactiveServerRequest): ReactiveServerResponse = run {
        val id = req.queryParam("id")
        println("id = $id")
        ReactiveServerResponse.ok().bodyValue(User(1, "Tony")).awaitSingle()
    }
}

open class TypeParamBean(val coreFoos: List<CoreFoo>, val coreBars: List<CoreBar>)

open class CoreBaz(string: String) {

}

open class CoreBar(coreFoo: CoreFoo, businessFoo: BusinessFoo) {}

open class CoreFoo {}
