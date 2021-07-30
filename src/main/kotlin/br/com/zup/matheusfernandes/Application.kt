package br.com.zup.matheusfernandes

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("br.com.zup.matheusfernandes")
		.start()
}

