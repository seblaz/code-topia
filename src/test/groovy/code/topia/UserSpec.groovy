package code.topia

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class UserSpec extends Specification implements DomainUnitTest<User> {

    Level beginnerLevel = new Level()
    Level advancedLevel = new Level()
    def validatorMock = Mock(ExerciseValidator)

    def setup() {
    }

    def cleanup() {
    }

    void "create user"() {
        when: "create a new user"
        User user = new User("Alejandro", "Pena", "example@example.com")
        UserGamification usGm = user.initGamification(beginnerLevel)

        then: "the new user is valid"
        assert user.validate()
    }

    void "get level user"() {
        when: "create a new user"
        User user = new User("Alejandro", "Pena", "example@example.com")
        UserGamification usGm = user.initGamification(beginnerLevel)

        then: "the new user is valid"
        assert user.validate()

        and: "his level is beginner"
        def level = user.getLevel()
        assert level.getLevelType() == LevelType.BEGINNER
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


    void "create user"() {
        given: "a new user"
        User user = new User("Alejandro", "Pena", "example@example.com")
        UserGamification usGm = user.initGamification(beginnerLevel)
        assert user.validate()
        when: "his level is beginner"
        def level = user.getLevel()
        assert level.getLevelType() == LevelType.BEGINNER
        then: "has at least five exercises"
        assert usGm.getAvailableExercises().size() >= 5
    }

}
