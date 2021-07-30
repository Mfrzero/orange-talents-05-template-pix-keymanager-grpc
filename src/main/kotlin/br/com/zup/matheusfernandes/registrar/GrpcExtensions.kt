package br.com.zup.matheusfernandes.registrar

import br.com.zup.matheusfernandes.RegistraChavePixRequest
import br.com.zup.matheusfernandes.TipoDeChave
import br.com.zup.matheusfernandes.TipoDeConta

fun RegistraChavePixRequest.toModel(): NovaChavePix {
    return NovaChavePix(
            clienteId = clienteId,
            tipo = when (tipoDeChave) {
                TipoDeChave.UNKNOWN_TIPO_CHAVE -> null
                else -> TipoDeChaveEnum.valueOf(tipoDeChave.name)
            },
            chave = chave,
            tipoDeConta = when (tipoDeConta) {
                TipoDeConta.UNKNOWN_TIPO_CONTA -> null
                else -> TipoDeContaEnum.valueOf(tipoDeConta.name)
            }
    )
}