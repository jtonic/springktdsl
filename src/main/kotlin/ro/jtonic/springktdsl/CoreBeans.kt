package ro.jtonic.springktdsl

import org.springframework.beans.factory.BeanRegistrarDsl
import org.springframework.core.env.getProperty
import ro.jtonic.springktdsl.BeanRegistrarDslExtensions.registerProperties

class CoreBeans : BeanRegistrarDsl({

    // bind a simple properties to a kotlin primitive type variable
    val geeCValue: Int = env.getProperty<Int>("gee.c", -1)

    // bind a structured config properties to a data class
    registerProperties<GeeProps>("gee")

    // register transitively beans in a separate BeanRegistrar
    register(businessBeans)

    // simple beans registration
    registerBean<CoreFoo>()
    registerBean<CoreBar>(
        name = "bar",
        prototype = true,
        lazyInit = true,
        description = "Custom description") {
        CoreBar(bean<CoreFoo>(), bean<BusinessFoo>())
    }
    registerBean<List<CoreFoo>>() { listOf(CoreFoo())}
    registerBean<List<CoreBar>> { listOf(CoreBar(bean<CoreFoo>(), bean<BusinessFoo>())) }

    // Type based (generics) beans autowired finally works
    registerBean<TypeParamBean> { TypeParamBean(bean<List<CoreFoo>>(), bean<List<CoreBar>>()) }

    // register based on condition (active profile)
    profile("baz") {
        registerBean { CoreBaz("Hello World!") }
    }

    registerBean<UserHandler> {
        UserHandler(env, bean<GeeProps>(), bean<FeeProps>())
    }
    registerBean() {
        // register async endpoints
        coRouter(bean<UserHandler>())
    }
})

data class User(val id: Long, val name: String)

open class TypeParamBean(val coreFoos: List<CoreFoo>, val coreBars: List<CoreBar>)

open class CoreBaz(string: String)

open class CoreBar(coreFoo: CoreFoo, businessFoo: BusinessFoo)

open class CoreFoo
