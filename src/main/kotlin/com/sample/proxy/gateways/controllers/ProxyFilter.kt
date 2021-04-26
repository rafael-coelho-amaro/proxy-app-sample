package com.sample.proxy.gateways.controllers

import com.sample.proxy.configs.GatewayConfig
import io.micronaut.core.async.publisher.Publishers
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpVersion
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Filter
import io.micronaut.http.client.RxProxyHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.filter.OncePerRequestHttpServerFilter
import io.micronaut.http.filter.ServerFilterChain
import org.reactivestreams.Publisher
import java.util.function.Function

@Filter("\${gateway-conf.hybris-filter-keys}")
class ProxyFilter(val gatewayConfig: GatewayConfig,
                  @Client("\${gateway-conf.hybris-url}",
                          httpVersion = HttpVersion.HTTP_1_1)
                  val client: RxProxyHttpClient) :
//                    val client: RxHttpClient) :
        OncePerRequestHttpServerFilter() {

    override fun doFilterOnce(request: HttpRequest<*>?,
                              chain: ServerFilterChain?):
            Publisher<MutableHttpResponse<*>> {

//        return client.proxy(request!!.mutate())

        return Publishers.map(client.proxy(request!!.mutate()),
                Function { response: MutableHttpResponse<*> -> response })

//        val client = RxHttpClient.create(URL("https://%s/".format(gatewayConfig.hybrisUrn)))
//
//        var result = client.exchange(
//                request!!.mutate(),
//                String::class.java
//        )
//        return Publishers.map(result, Function {
//            response -> mutate(response)
//        })
    }

//    private fun mutate(response: HttpResponse<String>): MutableHttpResponse<String> {
//        var mutable = HttpResponse.status<String>(response.status)
//        mutable.body(response.body.orElse(null))
//        response.headers.forEach { k, v ->
//            mutable.header(k, v.get(0))
//        }
//        return mutable
//    }
}