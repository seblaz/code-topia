package code.topia

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class UserSpec extends Specification implements DomainUnitTest<User> {

    def setup() {
    }

    def cleanup() {
    }

    void "test blank email constrain"() {
        given:
        def user = new User()
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
        user.firstName      = "Alejandro"
        user.lastName       = "Peña"
        user.email          = ""
        user.gamification   = new UserGamification(beginnerLevel)

        then:
        !user.validate()
    }

    void "test fake email constrain"() {
        given:
        def user = new User()
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
        user.firstName = "Alejandro"
        user.lastName = "Peña"
        user.email = "False mail de prueba"
        user.gamification   = new UserGamification(beginnerLevel)
        
        then:
        !user.validate()
    }

    void "test valid email constrain"() {
        given:
        def user = new User()
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
        user.firstName = "Alejandro"
        user.lastName = "Peña"
        user.email = "email@example.com"
        user.gamification   = new UserGamification(beginnerLevel)
        

        then:
        user.validate()
    }

    void "test valid user gamification constrain"() {
        when:
        def thrownException = null
        def user = null

        try {
            user = new User("Alejandro", "Peña",
                            "email@example.com",null)
        } catch (AssertionError e) {
            thrownException = e
        }    

        then:
        assert thrownException != null
    }

    void "test User"() {
        given: "no user"
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
        def usGm = new UserGamification(beginnerLevel)
        
        when: "create a new user"
        def user = new User("Alejandro", "Peña",
                            "email@example.com",usGm)
        
        
        then:
        //FIXME:
        assert user.gamification.level.name == "Beginner Level"
        user.validate()

        when:
        def user_err1 = new User("Alejandro", "Peña",
                                 "email falso",usGm)
        
        then:
        !user_err1.validate()

    }
    

}
