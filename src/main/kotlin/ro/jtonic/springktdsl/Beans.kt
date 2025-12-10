package ro.jtonic.springktdsl

import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.beans.factory.BeanRegistrarDsl
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.coRouter
import org.springframework.web.reactive.function.server.RequestPredicates as ReactiveRequestPredicates
import org.springframework.web.reactive.function.server.ServerRequest as ReactiveServerRequest
import org.springframework.web.reactive.function.server.ServerResponse as ReactiveServerResponse

class Beans : BeanRegistrarDsl({
    registerBean<Foo>()
    registerBean(
        name = "bar",
        prototype = true,
        lazyInit = true,
        description = "Custom description") {
        Bar(bean<Foo>())
    }
    profile("baz") {
        registerBean { Baz("Hello World!") }
    }

    registerBean<List<Foo>>() { listOf(Foo())}
    registerBean<List<Bar>> { listOf(Bar(bean()))}
    registerBean<TypeParamBean> { TypeParamBean(bean<List<Foo>>(), bean<List<Bar>>()) }

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

open class TypeParamBean(val foos: List<Foo>, val bars: List<Bar>)

open class Baz(string: String) {

}

open class Bar(foo: Foo) {

}

class Foo {

}
