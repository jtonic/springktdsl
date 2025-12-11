package ro.jtonic.springktdsl

import org.springframework.beans.factory.BeanRegistrarDsl

internal class BusinessBeans : BeanRegistrarDsl({
    registerBean<BusinessFoo>()
})
open class BusinessFoo() {}

internal val businessBeans: BusinessBeans = BusinessBeans()

