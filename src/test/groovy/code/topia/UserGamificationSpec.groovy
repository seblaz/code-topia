package code.topia

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class UserGamificationSpec extends Specification implements DomainUnitTest<UserGamification> {

    static final String STATEMENT_1 = "Escribe un programa en C que imprima '¡Hola, mundo!' en la pantalla"
    static final String STATEMENT_2 = "Escribe un programa en C que le pida al usuario ingresar dos números enteros e imprima la suma de esos dos números."
    static final String STATEMENT_3 = "Escribe un programa en C que verifique si un número ingresado por el usuario es par o impar."
    static final String STATEMENT_4 = "Escribe un programa en C que calcule el factorial de un número ingresado por el usuario."
    static final String STATEMENT_5 = "Escribe un programa en C que encuentre el máximo entre tres números ingresados por el usuario."
    
    static final String TITLE_1  = "Hola Mundo"
    static final String TITLE_2  = "Suma"
    static final String TITLE_3  = "Numero Par"
    static final String TITLE_4  = "Calculo Factorial"
    static final String TITLE_5  = "Funcion Maximo"

    Exercise ex1 = new Exercise(TITLE_1, STATEMENT_1, 1)
    Exercise ex2 = new Exercise(TITLE_2, STATEMENT_2, 2)
    Exercise ex3 = new Exercise(TITLE_3, STATEMENT_3, 1)
    Exercise ex4 = new Exercise(TITLE_4, STATEMENT_4, 3)
    Exercise ex5 = new Exercise(TITLE_5, STATEMENT_5, 2)
    BeginnerLevel beginnerLevel = new BeginnerLevel([ex1,ex2,ex3,ex4,ex5],5)
    Exercise ex6  = new Exercise(TITLE_1, STATEMENT_1, 1)
    Exercise ex7  = new Exercise(TITLE_2, STATEMENT_2, 2)
    Exercise ex8  = new Exercise(TITLE_3, STATEMENT_3, 1)
    Exercise ex9  = new Exercise(TITLE_4, STATEMENT_4, 3)
    Exercise ex10 = new Exercise(TITLE_5, STATEMENT_5, 6)
    AdvancedLevel advancedLevel = new AdvancedLevel([ex6,ex7,ex8,ex9,ex10],8)

    def setup() {
    }

    def cleanup() {
    }

    void "create a UserGamification at BeginnerLevel"() {
        when: "create a new user"
        User user = new User("Alejandro", "Pena", "example@example.com")
        UserGamification usGm = user.initGamification(beginnerLevel)

        then: "the UserGamification is valid"
        assert usGm.validate()
    }


    void "could not create a UserGamification at AdvancedLevel"() {
        when: "create a new user"
        User user = new User("Alejandro", "Pena", "example@example.com")
        UserGamification usGm = user.initGamification(advancedLevel)

        then: "the UserGamification is not valid"
        thrown UserGamificationInvalidLevelException
    }

    void "get level user"() {
        when: "create a new user"
        User user = new User("Alejandro", "Pena", "example@example.com")
        UserGamification usGm = user.initGamification(beginnerLevel)

        then: "the new user is valid"
        assert user.validate()
        and: "the level is BeginnerLevel"
        assert usGm.getUserLevel() == beginnerLevel
    }

    void "add an attempt"() {
        given: "a user"
        User user = new User("Alejandro", "Pena", "example@example.com")
        UserGamification usGm = user.initGamification(beginnerLevel)
        when: "add an attempt"
        Attempt attempt = user.performAttempt(ex1, "print('Hello World')")
        then: "usergamification has the attempt"
        assert usGm.getAllAttempts().contains(attempt)
    }

    void "add an attempt with invalid exercise level"() {
        given: "a user"
        User user = new User("Alejandro", "Pena", "example@example.com")
        UserGamification usGm = user.initGamification(beginnerLevel)
        when: "add an attempt with invalid exercise level"
        usGm.addAttempt(user.performAttempt(ex6, "print('Hello World')"))
        then: "throws AttemptWithInvalidExerciseLevelException"
        thrown AttemptWithInvalidExerciseLevelException
    }


}
