package code.topia

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class EntryLevelSpec extends Specification implements DomainUnitTest<EntryLevel> {

    def setup() {
    }

    def cleanup() {
    }

    void "test create EntryLevel"() {
        given:
        // Ej 1
        def statement = "Escribe un programa en C que imprima '¡Hola, mundo!' en la pantalla"
        def points = 1
        def ex1 = new Exercise(statement,points)

        // Ej 2
        statement = "Escribe un programa en C que le pida al usuario ingresar dos números enteros e imprima la suma de esos dos números."
        points = 2
        def ex2 = new Exercise(statement,points)

        // Ej 3
        statement = "Escribe un programa en C que verifique si un número ingresado por el usuario es par o impar."
        points = 1
        def ex3 = new Exercise(statement,points)

        // Ej 4
        statement = "Escribe un programa en C que calcule el factorial de un número ingresado por el usuario."
        points = 3
        def ex4 = new Exercise(statement,points)

        // Ej 5
        statement = "Escribe un programa en C que encuentre el máximo entre tres números ingresados por el usuario."
        points = 2
        def ex5 = new Exercise(statement,points)

        when:
        def entryLevel = new EntryLevel([ex1,ex2,ex3,ex4,ex5],5)

        then:
        entryLevel.validate()
    }

    void "test min excercise required EntryLevel"() {
        given:
        def statement = "Escribe un programa en C que imprima '¡Hola, mundo!' en la pantalla"
        def points = 5
        def ex1 = new Exercise(statement,points)

        when:
        def entryLevel = new EntryLevel([ex1],5)

        then:
        !entryLevel.validate()
    }

    void "test accumulated points of exercises sufficient of EntryLevel"() {
        given:
        // Ej 1
        def statement = "Escribe un programa en C que imprima '¡Hola, mundo!' en la pantalla"
        def points = 1
        def ex1 = new Exercise(statement,points)

        // Ej 2
        statement = "Escribe un programa en C que le pida al usuario ingresar dos números enteros e imprima la suma de esos dos números."
        points = 1
        def ex2 = new Exercise(statement,points)

        // Ej 3
        statement = "Escribe un programa en C que verifique si un número ingresado por el usuario es par o impar."
        points = 1
        def ex3 = new Exercise(statement,points)

        // Ej 4
        statement = "Escribe un programa en C que calcule el factorial de un número ingresado por el usuario."
        points = 1
        def ex4 = new Exercise(statement,points)

        // Ej 5
        statement = "Escribe un programa en C que encuentre el máximo entre tres números ingresados por el usuario."
        points = 1
        def ex5 = new Exercise(statement,points)

        when:
        def thrownException = null
        def entryLevel = null
        // Se espera que falle por no cumplir la suma minima de puntos 
        // para completar el nivel.
        try {
            entryLevel = new EntryLevel([ex1,ex2,ex3,ex4,ex5],10)
        } catch (AssertionError e) {
            thrownException = e
        }    

        then:
        assert thrownException != null
    }
}
