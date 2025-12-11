package ro.jtonic.springktdsl

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("fee")
data class FeeProps(val a: Int, val b: String)
