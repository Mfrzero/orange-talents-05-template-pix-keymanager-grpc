package br.com.zup.matheusfernandes.remove

import br.com.zup.matheusfernandes.KeymanagerRemoveGrpcServiceGrpc
import br.com.zup.matheusfernandes.RemoveChavePixRequest
import br.com.zup.matheusfernandes.RemoveChavePixResponse
import br.com.zup.matheusfernandes.handler.ErrorHandler

import io.grpc.stub.StreamObserver
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ErrorHandler
class RemoveChaveEndpoint (
        @Inject private val service: RemoveChavePixService
) : KeymanagerRemoveGrpcServiceGrpc.KeymanagerRemoveGrpcServiceImplBase() {

    override fun remove(request: RemoveChavePixRequest, responseObserver: StreamObserver<RemoveChavePixResponse>) {
        service.remove(
                clienteId = request.clienteId,
                pixId = request.pixId
        )

        responseObserver.onNext(
                RemoveChavePixResponse.newBuilder()
                        .setClienteId(request.clienteId)
                        .setPixId(request.pixId)
                        .build()
        )

        responseObserver.onCompleted()
    }
}
