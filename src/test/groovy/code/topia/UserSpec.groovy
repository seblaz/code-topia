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

    def ex1 = new Exercise(TITLE_1, STATEMENT_1, 1)
    def ex2 = new Exercise(TITLE_2, STATEMENT_2, 2)
    def ex3 = new Exercise(TITLE_3, STATEMENT_3, 1)
    def ex4 = new Exercise(TITLE_4, STATEMENT_4, 3)
    def ex5 = new Exercise(TITLE_5, STATEMENT_5, 2)
    def beginnerLevel = new BeginnerLevel([ex1,ex2,ex3,ex4,ex5],5)

    def setup() {
    }

    def cleanup() {
    }



    void "test blank email constrain"() {
        given:
        def user = new User()

        when:
        user.firstName      = "Alejandro"
        user.lastName       = "Peña"
        user.email          = ""
        user.gamification   = new UserGamification(user,beginnerLevel)

        then:
        !user.validate()
    }

    void "test fake email constrain"() {
        given:
        def user = new User()

        when:
        user.firstName = "Alejandro"
        user.lastName = "Peña"
        user.email = "False mail de prueba"
        user.gamification   = new UserGamification(user,beginnerLevel)
        
        then:
        !user.validate()
    }

    void "test valid email constrain"() {
        given:
        def user = new User()

        when:
        user.firstName = "Alejandro"
        user.lastName = "Peña"
        user.email = "email@example.com"
        user.gamification   = new UserGamification(user,beginnerLevel)
        

        then:
        user.validate()
    }

    void "test valid user gamification constrain"() {
        when:
        def thrownException = null
        def user = null

        try {
            user = new User("Alejandro", "Peña",
                            "email@example.com")
        } catch (AssertionError e) {
            thrownException = e
        }    

        then:
        assert thrownException != null
    }

    void "test User"() {
        given: "no user"
        def usGm = new UserGamification(user,beginnerLevel)
        
        when: "create a new user"
        def user = new User("Alejandro", "Peña",
                            "email@example.com")
        
        
        then: "the user is in beginner level"
        Level level = user.getLevel()
        assert level.name == BEGINNER_LEVEL_NAME
        user.validate()

        when:
        def user_err1 = new User("Alejandro", "Peña",
                                 "email falso")
        
        then: 
        !user_err1.validate()

    }
    

}
