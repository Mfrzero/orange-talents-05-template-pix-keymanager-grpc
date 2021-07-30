package br.com.zup.matheusfernandes.remove

import br.com.zup.matheusfernandes.ChavePixRepository
import br.com.zup.matheusfernandes.handler.ChavePixNotFoundException
import br.com.zup.matheusfernandes.validation.ValidUUID
import java.util.*
import javax.inject.Inject
import javax.transaction.Transactional
import javax.validation.constraints.NotBlank


class RemoveChavePixService(@Inject val repository: ChavePixRepository,) {

    @Transactional
    fun remove(
            @NotBlank @ValidUUID clienteId: String?,
            @NotBlank @ValidUUID pixId: String?
    ) {
        val uuidPixId = UUID.fromString(pixId)
        val uuidclienteId = UUID.fromString(clienteId)

        val chave = repository.findByIdAndClienteId(uuidPixId, uuidclienteId)
                .orElseThrow { ChavePixNotFoundException("Chave Pix não encontrada ou não pertence ao cliente") }

        repository.deleteById(uuidPixId)
    }
}