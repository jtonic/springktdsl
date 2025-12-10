package ro.jtonic.springktdsl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(Beans::class)
class SpringKtDslApplication

fun main(args: Array<String>) {
    runApplication<SpringKtDslApplication>(*args)
}
