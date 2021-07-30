package br.com.zup.matheusfernandes

import br.com.zup.matheusfernandes.registrar.ChavePix
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

interface ChavePixRepository: JpaRepository<ChavePix, UUID> {

    fun existsbyChave(chavePix: String): Boolean

}