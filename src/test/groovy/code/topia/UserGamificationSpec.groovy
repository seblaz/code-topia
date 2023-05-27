package code.topia

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class UserGamificationSpec extends Specification implements DomainUnitTest<UserGamification> {

    def setup() {
    }

    def cleanup() {
    }

    void "test UserGamification level constrain"() {
        when:
        def usGm = new UserGamification()

        then:
        !usGm.validate()
    }

    void "test UserGamification"() {
        given:
        def statement = "Escribe un programa en C que imprima '¡Hola, mundo!' en la pantalla"
        def points = 1
        def ex1 = new Exercise(statement,points)
        
        statement = "Escribe un programa en C que le pida al usuario ingresar dos números enteros e imprima la suma de esos dos números."
        points = 2
        def ex2 = new Exercise(statement,points)
        
        statement = "Escribe un programa en C que verifique si un número ingresado por el usuario es par o impar."
        points = 1
        def ex3 = new Exercise(statement,points)
        
        statement = "Escribe un programa en C que calcule el factorial de un número ingresado por el usuario."
        points = 3
        def ex4 = new Exercise(statement,points)

        statement = "Escribe un programa en C que encuentre el máximo entre tres números ingresados por el usuario."
        points = 2
        def ex5 = new Exercise(statement,points)

        def beginnerLevel = new BeginnerLevel([ex1,ex2,ex3,ex4,ex5],5)

        when:
        def usGm = new UserGamification(beginnerLevel)

        then:
        usGm.validate()
    }
}
