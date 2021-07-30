package br.com.zup.matheusfernandes.registrar

import br.com.zup.matheusfernandes.ChavePixRepository
import br.com.zup.matheusfernandes.client.ContasDeClientesNoItau
import br.com.zup.matheusfernandes.handler.ChavePixExistenteException
import io.micronaut.validation.Validated
import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.Valid

@Singleton
@Validated
class NovaChavePixService(@Inject val repository: ChavePixRepository,
                          @Inject val itauClient: ContasDeClientesNoItau) {

    @Transactional
    fun registra(@Valid novaChave: NovaChavePix): ChavePix {

        if (repository.existsbyChave(novaChave.chave!!)){
            throw ChavePixExistenteException("Chave Pix ${novaChave.chave} existente")
        }

        val response = itauClient.buscaContaPorTipo(novaChave.clienteId!!, novaChave.tipoDeConta!!.name)
        val conta = response.body()?.toModel() ?: throw IllegalStateException("Cliente não econtrado no Itau")

        val chave = novaChave.toModel(conta)
        repository.save(chave)

        return chave
    }
}
