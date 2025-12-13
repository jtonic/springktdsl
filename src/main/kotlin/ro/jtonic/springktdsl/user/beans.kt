package ro.jtonic.springktdsl.user

import org.springframework.beans.factory.BeanRegistrarDsl
import ro.jtonic.springktdsl.config.FeeProps
import ro.jtonic.springktdsl.config.GeeProps

val userBeans = BeanRegistrarDsl {
    registerBean<UserHandler> {
        UserHandler(env, bean<GeeProps>(), bean<FeeProps>())
    }
    registerBean {
        userCoRouter(bean<UserHandler>())
    }
}

data class User(val id: Long, val name: String)
