package ro.jtonic.springktdsl.config

import org.springframework.beans.factory.BeanRegistrarDsl
import org.springframework.core.env.getProperty

val configBeans = BeanRegistrarDsl {

    registerBean<ConfigHandler>{
        ConfigHandler(bean<GeeProps>(), bean<FeeProps>(), env.getProperty<MonitoringEnabled>("monitoring.enabled")?: MonitoringEnabled.OFF)
    }
    registerBean {
        configCoRouter(bean<ConfigHandler>())
    }
}