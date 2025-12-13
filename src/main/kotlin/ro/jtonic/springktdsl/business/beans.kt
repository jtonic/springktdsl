package ro.jtonic.springktdsl.business

import org.springframework.beans.factory.BeanRegistrarDsl
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.modulith.core.ApplicationModules
import ro.jtonic.springktdsl.App

val businessBeans = BeanRegistrarDsl{
    registerBean<BusinessFoo>()
    registerBean<ModulithModules>()
}

open class BusinessFoo() {}

open class ModulithModules : ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        val modules = ApplicationModules.of(App::class.java)
        println("Application modules fetching...")
        println("# modules = ${modules.toList().size}")
        modules.forEach(::println)
        println("Application modules fetched!")
    }
}

