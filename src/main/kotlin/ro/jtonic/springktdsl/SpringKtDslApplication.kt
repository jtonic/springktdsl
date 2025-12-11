package ro.jtonic.springktdsl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(CoreBeans::class)
@EnableConfigurationProperties(FeeProps::class)
class SpringKtDslApplication

fun main(args: Array<String>) {
    runApplication<SpringKtDslApplication>(*args)
}
