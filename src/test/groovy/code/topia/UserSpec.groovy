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

        when:
        user.firstName = "Alejandro"
        user.lastName = "Peña"
        user.email = ""

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

        then:
        user.validate()
    }

    void "test User"() {
        
        when:
        def user = new User("Alejandro", "Peña",
                            "email@example.com")
        
        then:
        user.validate()

        when:
        def user_err1 = new User("Alejandro", "Peña",
                                 "email falso")
        
        then:
        !user_err1.validate()
    }

}
