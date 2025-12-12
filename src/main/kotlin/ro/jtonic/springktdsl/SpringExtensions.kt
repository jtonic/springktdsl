package ro.jtonic.springktdsl

import org.springframework.beans.factory.BeanRegistrarDsl
import org.springframework.boot.context.properties.bind.Binder
import org.springframework.core.env.Environment
import org.springframework.core.env.getProperty

object SpringExtensions {

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

    inline fun <reified T : Any> Environment.getPropertyOrElse(prop: String): T? {
        val t = this.getProperty<T>(prop)
        return t
    }
}