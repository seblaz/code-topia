package code.topia

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class AdvancedLevelSpec extends Specification implements DomainUnitTest<AdvancedLevel> {

    static final String STATEMENT_1 = "Escribe un programa en C que imprima '¡Hola, mundo!' en la pantalla"
    static final String STATEMENT_2 = "Escribe un programa en C que le pida al usuario ingresar dos números enteros e imprima la suma de esos dos números."
    static final String STATEMENT_3 = "Escribe un programa en C que verifique si un número ingresado por el usuario es par o impar."
    static final String STATEMENT_4 = "Escribe un programa en C que calcule el factorial de un número ingresado por el usuario."
    static final String STATEMENT_5 = "Escribe un programa en C que encuentre el máximo entre tres números ingresados por el usuario."

    def ex1 = new Exercise(STATEMENT_1,1)
    def ex2 = new Exercise(STATEMENT_2,2)
    def ex3 = new Exercise(STATEMENT_3,1)
    def ex4 = new Exercise(STATEMENT_4,3)
    def ex5 = new Exercise(STATEMENT_5,2)

    def setup() {
    }

    def cleanup() {
    }

    void "test create AdvancedLevel"() {
        given: "exists 5 exercises"
        assert ex1 != null
        assert ex2 != null
        assert ex3 != null
        assert ex4 != null
        assert ex5 != null

        when: "create advanced level"
        def advancedLevel = new AdvancedLevel([ex1,ex2,ex3,ex4,ex5],8)

        then: "advanced level is valid"
        advancedLevel.validate()
    }

    void "test min excercise required AdvancedLevel"() {
        given:
        def statement = "Escribe un programa en C que imprima '¡Hola, mundo!' en la pantalla"
        def points = 8
        def ex_tmp = new Exercise(statement,points)

        when:
        def advancedLevel = new AdvancedLevel([ex_tmp],8)

        then:
        !advancedLevel.validate()
    }

    void "test accumulated points of exercises sufficient of AdvancedLevel"() {
        given: "exists 5 exercises"
        assert ex1 != null
        assert ex2 != null
        assert ex3 != null
        assert ex4 != null
        assert ex5 != null

        when:
        def thrownException = null
        def advancedLevel = null
        // Se espera que falle por no cumplir la suma minima de puntos 
        // para completar el nivel.
        try {
            advancedLevel = new AdvancedLevel([ex1,ex2,ex3,ex4,ex5],10)
        } catch (AssertionError e) {
            thrownException = e
        }    

        then:
        assert thrownException != null

    }

    void "test get 5 exercise at AdvancedLevel"() {
        given: "exists 5 exercises"
        assert ex1 != null
        assert ex2 != null
        assert ex3 != null
        assert ex4 != null
        assert ex5 != null
        
        when: "create beginner level"
        def advancedLevel = new AdvancedLevel([ex1,ex2,ex3,ex4,ex5],8)

        then: "it has 5 exercises"
        List<Exercise> list = advancedLevel.getExercises()
        assert list.size() == 5
        
    }
}
