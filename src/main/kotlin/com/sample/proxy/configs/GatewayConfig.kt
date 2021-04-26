package com.sample.proxy.configs

import io.micronaut.context.annotation.ConfigurationProperties

/**
 * Configure
 */
@ConfigurationProperties("gateway-conf")
class GatewayConfig {
    var hybrisFilterKeys: String? = null
    var hybrisUrl: String? = null
}