package com.sample.proxy.gateways.controllers

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpVersion
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Filter
import io.micronaut.http.client.RxProxyHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.filter.OncePerRequestHttpServerFilter
import io.micronaut.http.filter.ServerFilterChain
import org.reactivestreams.Publisher

@Filter("/hybris/v1/**")
class ProxyFilter2 (@Client("https://localhost:8083",
                            httpVersion = HttpVersion.HTTP_1_1)
                    val client: RxProxyHttpClient) :
        OncePerRequestHttpServerFilter() {

    override fun doFilterOnce(request: HttpRequest<*>?,
                              chain: ServerFilterChain?):
            Publisher<MutableHttpResponse<*>> {
        return client.proxy(request!!.mutate())
    }
}