package ro.jtonic.springktdsl

import org.springframework.beans.factory.BeanRegistrarDsl

class BusinessBeans : BeanRegistrarDsl({
    registerBean<BusinessFoo>()
})
open class BusinessFoo() {}
