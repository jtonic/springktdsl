package ro.jtonic.springktdsl.config

import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.coRouter

fun configCoRouter(configHandler: ConfigHandler) = coRouter {

    GET(
        pattern = "/api/config/{props}",
        predicate = RequestPredicates.accept(APPLICATION_JSON),
        f = configHandler::getConfigProps
    )
}

