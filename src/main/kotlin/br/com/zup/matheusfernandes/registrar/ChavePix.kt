package br.com.zup.matheusfernandes.registrar

import br.com.zup.matheusfernandes.TipoDeChave
import br.com.zup.matheusfernandes.TipoDeConta
import java.util.*
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class ChavePix (
    @field:NotNull
    @Column(nullable = false)
    val clienteId: UUID,

    @field:NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val tipoDeChave: TipoDeChave,

    @field:NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val tipoDeConta: TipoDeConta,

    @field:NotBlank
    @Column(unique = true, nullable = false)
    var chave: String,

    @field:Valid
    @Embedded
    val conta: ContaAssociada
    )
{
}
