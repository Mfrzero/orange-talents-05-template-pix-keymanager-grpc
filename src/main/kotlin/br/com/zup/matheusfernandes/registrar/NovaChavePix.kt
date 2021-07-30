package br.com.zup.matheusfernandes.registrar

import br.com.zup.matheusfernandes.TipoDeChave
import br.com.zup.matheusfernandes.TipoDeConta
import br.com.zup.matheusfernandes.validation.ChavePixValida
import br.com.zup.matheusfernandes.validation.ValidUUID
import io.micronaut.core.annotation.Introspected
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@ChavePixValida
@Introspected
data class NovaChavePix(
        @ValidUUID
        @field:NotBlank
        val clienteId: String?,
        @field:NotNull
        val tipo: TipoDeChaveEnum?,
        @field:Size(max = 77)
        val chave: String?,
        @field:NotNull
        val tipoDeConta: TipoDeContaEnum?,) {

    fun toModel(conta: ContaAssociada): ChavePix{
        return ChavePix(
                clienteId = UUID.fromString(this.clienteId),
                tipoDeChave = TipoDeChave.valueOf(this.tipo!!.name),
                chave = if (this.tipo == TipoDeChaveEnum.ALEATORIA) UUID.randomUUID().toString() else this.chave!!,
                tipoDeConta = TipoDeConta.valueOf(this.tipoDeConta!!.name),
                conta = conta
        )
    }
}