@file:Suppress("unused")

package ro.jtonic.springktdsl

import org.springframework.beans.factory.BeanRegistrarDsl
import ro.jtonic.springktdsl.business.businessBeans
import ro.jtonic.springktdsl.config.GeeProps
import ro.jtonic.springktdsl.config.configBeans
import ro.jtonic.springktdsl.core.coreBeans
import ro.jtonic.springktdsl.user.userBeans
import ro.jtonic.springktdsl.util.BeanRegistrarDslExtensions.registerProperties

class CoreBeans : BeanRegistrarDsl({
    registerProperties<GeeProps>("gee")
    register(coreBeans)
    register(businessBeans)
    register(configBeans)
    register(userBeans)
})
