package ro.jtonic.springktdsl.core

import org.springframework.beans.factory.BeanRegistrarDsl

val coreBeans = BeanRegistrarDsl {
    registerBean<CoreFoo>()
    registerBean<CoreBar>(
        name = "bar",
        prototype = true,
        lazyInit = true,
        description = "Custom description") {
        CoreBar(bean<CoreFoo>())
    }
    profile("baz") {
        registerBean { CoreBaz("Hello World!") }
    }

    registerBean<List<CoreFoo>> { listOf(CoreFoo())}
    registerBean<List<CoreBar>> { listOf(CoreBar(bean<CoreFoo>())) }
    registerBean<TypeParamBean> { TypeParamBean(bean<List<CoreFoo>>(), bean<List<CoreBar>>()) }
}

open class CoreBaz(string: String)

open class CoreFoo

open class TypeParamBean(val coreFoos: List<CoreFoo>, val coreBars: List<CoreBar>)

open class CoreBar(coreFoo: CoreFoo)
