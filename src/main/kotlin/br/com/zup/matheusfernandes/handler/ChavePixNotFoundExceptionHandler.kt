package br.com.zup.matheusfernandes.handler

import io.grpc.Status

class ChavePixNotFoundExceptionHandler: ExceptionHandler<ChavePixNotFoundException>  {
    override fun handle(e: ChavePixNotFoundException): ExceptionHandler.StatusWithDetails {
        return ExceptionHandler.StatusWithDetails(
                Status.NOT_FOUND
                        .withDescription(e.message)
                        .withCause(e)
        )
    }

    override fun supports(e: Exception): Boolean {
        return e is ChavePixNotFoundException
    }
}

class ChavePixNotFoundException(message: String) : RuntimeException(message)