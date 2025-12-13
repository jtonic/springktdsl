package ro.jtonic.springktdsl.config

import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.buildAndAwait

open class ConfigHandler(val geeProps: GeeProps, val feeProps: FeeProps, val monitoringEnabled: MonitoringEnabled) {

    suspend fun getConfigProps(req: ServerRequest): ServerResponse = run {
        val props = req.pathVariable("props")
        println("props = $props")
        val body: Any? = when (props) {
            "gee" -> geeProps
            "fee" -> feeProps
            "monitoring" -> monitoringEnabled
            else -> null
        }
        body?.run {
            ServerResponse.ok().bodyValue(body).awaitSingle()
        } ?: ServerResponse.notFound().buildAndAwait()
    }
}

