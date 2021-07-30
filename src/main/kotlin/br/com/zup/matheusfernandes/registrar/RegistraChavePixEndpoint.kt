package br.com.zup.matheusfernandes.registrar

import br.com.zup.matheusfernandes.KeyManagerGrpcServiceGrpc
import br.com.zup.matheusfernandes.RegistraChavePixRequest
import br.com.zup.matheusfernandes.RegistraChavePixResponse
import br.com.zup.matheusfernandes.handler.ErrorHandler
import io.grpc.stub.StreamObserver
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ErrorHandler
class RegistraChavePixEndpoint(@Inject private val service: NovaChavePixService,): KeyManagerGrpcServiceGrpc.KeyManagerGrpcServiceImplBase() {

    override fun registra(request: RegistraChavePixRequest,
                          responseObserver: StreamObserver<RegistraChavePixResponse>?) {

    val novaChave = request.toModel()
    val chaveCriada = service.registra(novaChave)

    responseObserver?.onNext(RegistraChavePixResponse.newBuilder()
                .setClienteId(chaveCriada.clienteId.toString())
                .setPixId(chaveCriada.chave.toString())
                .build())
    responseObserver?.onCompleted()

    }

}