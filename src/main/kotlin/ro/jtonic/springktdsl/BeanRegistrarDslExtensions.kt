package ro.jtonic.springktdsl

import org.springframework.beans.factory.BeanRegistrarDsl
import org.springframework.boot.context.properties.bind.Binder

object BeanRegistrarDslExtensions {

    inline fun <reified T : Any> BeanRegistrarDsl.registerProperties(
        property: String,
        errMsg: String = "The ${T::class.qualifiedName} cannot be null!"
    ) = run {
        registerBean<T> {
            Binder.get(env)
                .bind(property, T::class.java)
                .orElseThrow { IllegalArgumentException(errMsg) }
        }
    }
}