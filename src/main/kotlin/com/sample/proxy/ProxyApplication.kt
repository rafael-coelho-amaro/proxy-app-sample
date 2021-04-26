package com.sample.proxy

import io.micronaut.runtime.Micronaut.build

fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("com.amaro.ecp.cerberus")
		.start()
}

