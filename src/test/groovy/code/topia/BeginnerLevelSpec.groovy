package code.topia

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class BeginnerLevelSpec extends Specification implements DomainUnitTest<BeginnerLevel> {

    static final String STATEMENT_1 = "Escribe un programa en C que imprima '¡Hola, mundo!' en la pantalla"
    static final String STATEMENT_2 = "Escribe un programa en C que le pida al usuario ingresar dos números enteros e imprima la suma de esos dos números."
    static final String STATEMENT_3 = "Escribe un programa en C que verifique si un número ingresado por el usuario es par o impar."
    static final String STATEMENT_4 = "Escribe un programa en C que calcule el factorial de un número ingresado por el usuario."
    static final String STATEMENT_5 = "Escribe un programa en C que encuentre el máximo entre tres números ingresados por el usuario."
    static final String TITLE_1     = "Hola Mundo"
    static final String TITLE_2     = "Suma"
    static final String TITLE_3     = "Numero Par"
    static final String TITLE_4     = "Calculo Factorial"
    static final String TITLE_5     = "Funcion Maximo"

    Exercise ex1 = new Exercise(TITLE_1, STATEMENT_1, 1)
    Exercise ex2 = new Exercise(TITLE_2, STATEMENT_2, 1)
    Exercise ex3 = new Exercise(TITLE_3, STATEMENT_3, 1)
    Exercise ex4 = new Exercise(TITLE_4, STATEMENT_4, 1)
    Exercise ex5 = new Exercise(TITLE_5, STATEMENT_5, 1)
    
    
    def setup() {
    }

    def cleanup() {
    }

    void "test create BeginnerLevel"() {
        given: "exists 5 exercises"
        assert ex1 != null
        assert ex2 != null
        assert ex3 != null
        assert ex4 != null
        assert ex5 != null

        when: "create beginner level with 5 exercise"
        BeginnerLevel beginnerLevel = new BeginnerLevel([ex1,ex2,ex3,ex4,ex5],5)

        then: "the beginner level is valid"
        beginnerLevel.validate()
    }

    void "test min excercise required BeginnerLevel"() {
        given:
        Exercise ex_temp = new Exercise(TITLE_1, STATEMENT_1,5)

        when:
        BeginnerLevel beginnerLevel = new BeginnerLevel([ex_temp],5)

        then:
        !beginnerLevel.validate()
    }

    void "test accumulated points of exercises sufficient of BeginnerLevel"() {
        given: "exists 5 exercises"
        assert ex1 != null
        assert ex2 != null
        assert ex3 != null
        assert ex4 != null
        assert ex5 != null
        

        when:
        def thrownException = null
        def beginnerLevel = null
        // Se espera que falle por no cumplir la suma minima de puntos 
        // para completar el nivel.
        try {
            beginnerLevel = new BeginnerLevel([ex1,ex2,ex3,ex4,ex5],10)
        } catch (AssertionError e) {
            thrownException = e
        }    

        then:
        assert thrownException != null
    }

    void "test get 5 exercise at BeginnerLevel"() {
        given: "exists 5 exercises"
        assert ex1 != null
        assert ex2 != null
        assert ex3 != null
        assert ex4 != null
        assert ex5 != null
        
        when: "create beginner level"
        BeginnerLevel beginnerLevel = new BeginnerLevel([ex1,ex2,ex3,ex4,ex5],5)

        then: "it has 5 exercises"
        List<Exercise> list = beginnerLevel.getExercises()
        assert list.size() == 5
        
    }


    void "next level is advanced"() {
        given: "beginner level exist"
        BeginnerLevel beginnerLevel = new BeginnerLevel([ex1,ex2,ex3,ex4,ex5],5)
        assert beginnerLevel != null

        when: "get next level"
        def nextLevel = beginnerLevel.getNextLevelClass()

        then: "is Advanced Level"
        assert nextLevel instanceof AdvancedLevel
    }
}
