package ro.jtonic.springktdsl

import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.buildAndAwait
import org.springframework.web.reactive.function.server.coRouter
import org.springframework.web.reactive.function.server.RequestPredicates as ReactiveRequestPredicates
import org.springframework.web.reactive.function.server.ServerRequest as ReactiveServerRequest
import org.springframework.web.reactive.function.server.ServerResponse as ReactiveServerResponse

fun coRouter(userHandler: UserHandler) = coRouter {
    GET(
        pattern = "/api/users/",
        predicate = ReactiveRequestPredicates.accept(APPLICATION_JSON),
        f = userHandler::findById
    )
    GET(
        pattern = "/api/config/{props}",
        predicate = ReactiveRequestPredicates.accept(APPLICATION_JSON),
        f = userHandler::getConfigProps
    )
}

class UserHandler(val geeProps: GeeProps, val feeProps: FeeProps) {

    suspend fun findById(req: ReactiveServerRequest): ReactiveServerResponse = run {
        val id = req.queryParam("id")
        println("findById: $id")
        ReactiveServerResponse.ok().bodyValue(User(1, "Tony")).awaitSingle()
    }

    suspend fun getConfigProps(req: ReactiveServerRequest): ReactiveServerResponse = run {
        val props = req.pathVariable("props")
        println("props = $props")
        val body: Any? = when (props) {
            "gee" -> geeProps
            "fee" -> feeProps
            else -> null
        }
        body?.run {
            ReactiveServerResponse.ok().bodyValue(body).awaitSingle()
        } ?: ReactiveServerResponse.notFound().buildAndAwait()
    }
}
