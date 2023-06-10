package code.topia

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class UserSpec extends Specification implements DomainUnitTest<User> {
    static final String BEGINNER_LEVEL_NAME = "Beginner Level"

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

    // Refactorizá los siguientes tests
    void "create user"() {
        when: "create a new user"
        User user = new User("Alejandro", "Pena", "example@example.com")
        UserGamification usGm = user.initGamification(beginnerLevel)

        then: "the new user is valid"
        assert user.validate()

        and: "it has at leas 5 exercises"
        List<Attempt> attempts = usGm.getAllAttempts()
        assert attempts.size() >= 5
    }

    void "get level user"() {
        when: "create a new user"
        User user = new User("Alejandro", "Pena", "example@example.com")
        UserGamification usGm = user.initGamification(beginnerLevel)

        then: "the new user is valid"
        assert user.validate()

        and: "his level is beginner"
        Level level = user.getLevel()
        assert level == beginnerLevel
    }


    void "blank email constrain"() {
        given: "create a new user"
        User user = new User()
        UserGamification usGm = user.initGamification(beginnerLevel)

        when: "set attributes for new user, except email"
        user.firstName      = "Alejandro"
        user.lastName       = "Peña"
        user.email          = ""        

        then: "user is not valid"
        !user.validate()
    }

    void "fake email constrain"() {
        given: "create a new user"
        User user = new User()
        UserGamification usGm = user.initGamification(beginnerLevel)

        when: "set attributes for new user and bad email"
        user.firstName = "Alejandro"
        user.lastName = "Peña"
        user.email = "False mail de prueba"
        
        then: "user is not valid"
        !user.validate()
    }

    void "valid user gamification constrain"() {
        when: "create user and no UserGamification"
        User user = new User("Alejandro", "Peña","email@example.com")

        then: "user is not valid"
        !user.validate()
    }


    void "user perform an exercise Attempt"() {
        given: "create a new user"
        User user = new User("Alejandro", "Peña","email@example.com")
        UserGamification usGm = user.initGamification(beginnerLevel)

        when: "user perform an exercise attempt"
        Exercise ex = beginnerLevel.getExercises().get(0)
        Attempt attempt = user.performAttempt(ex, "Una respuesta")

        then: "the attempt is valid"
        assert attempt.validate()
        and: "the attempt is loaded in user gamification"
        assert usGm.getAllAttempts().contains(attempt)   
    }

    void "user perform an exercise Attempt with bad level"() {
        given: "create a new user"
        User user = new User("Alejandro", "Peña","email@exmample.com")
        UserGamification usGm = user.initGamification(beginnerLevel)

        when: "user perform an exercise attempt with bad level"
        Exercise ex = advancedLevel.getExercises().get(0)
        Attempt attempt = user.performAttempt(ex, "Una respuesta")

        then: "the attempt is not valid"
        thrown(AttemptWithInvalidExerciseLevelException)
    }
    

}
