package br.com.zup.matheusfernandes.validation

import br.com.zup.matheusfernandes.registrar.NovaChavePix
import javax.inject.Singleton
import javax.validation.Constraint
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass
import kotlin.annotation.AnnotationTarget.*

@MustBeDocumented
@Target(CLASS, TYPE)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ChavePixValidaValidator::class])
annotation class ChavePixValida(
        val message: String = "chave Pix inválida (\${validatedValue.tipo})",
        val groups: Array<KClass<Any>> = [],
        val payload: Array<KClass<Payload>> = [],
)

@Singleton
class ChavePixValidaValidator: javax.validation.ConstraintValidator<ChavePixValida, NovaChavePix> {

    override fun isValid(
            value: NovaChavePix?,
            context: ConstraintValidatorContext
    ): Boolean {

        if (value?.tipo == null) {
            return true
        }

        val valid = value.tipo.validate(value.chave)
        if (!valid) {
            context.disableDefaultConstraintViolation()
            context
                    .buildConstraintViolationWithTemplate(context.defaultConstraintMessageTemplate)
                    .addPropertyNode("chave").addConstraintViolation()
        }

        return valid
    }
}