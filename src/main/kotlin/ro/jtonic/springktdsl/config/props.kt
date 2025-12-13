package ro.jtonic.springktdsl.config

import org.springframework.boot.context.properties.ConfigurationProperties

data class GeeProps(val c: Int)

@ConfigurationProperties("fee")
data class FeeProps(val a: Int, val b: String)

enum class MonitoringEnabled(val value: String) {
    ON("on"), OFF("off")
}