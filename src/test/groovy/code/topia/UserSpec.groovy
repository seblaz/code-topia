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

    // Refactorizá los siguientes tests
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


//    void "user perform an exercise Attempt"() {
//        given: "create a new user"
//        User user = new User("Alejandro", "Peña","email@example.com")
//        UserGamification usGm = user.initGamification(beginnerLevel)
//
//        when: "user perform an exercise attempt"
//        Exercise ex = beginnerLevel.getExercises().get(0)
//        validatorMock.validateAnswer("print('Hello World')", ex) >> true
//        Attempt attempt = user.performAttempt(ex, "Una respuesta", validatorMock)
//
//        then: "the attempt is valid"
//        assert attempt.validate()
//        and: "the attempt is loaded in user gamification"
//        assert usGm.getAllAttempts().contains(attempt)
//    }
//
//    void "user perform an exercise Attempt with bad level"() {
//        given: "create a new user"
//        User user = new User("Alejandro", "Peña","email@exmample.com")
//        UserGamification usGm = user.initGamification(beginnerLevel)
//
//        when: "user perform an exercise attempt with bad level"
//        advancedLevel.type = LevelType.ADVANCED
//        Exercise ex = advancedLevel.getExercises().get(0)
//        validatorMock.validateAnswer("print('Hello World')", ex) >> true
//        Attempt attempt = user.performAttempt(ex, "Una respuesta", validatorMock)
//        then: "throws AttemptWithInvalidExerciseLevelException"
//        thrown(AttemptWithInvalidExerciseLevelException)
//    }
//    
//
//    void "user retries an exercise" () {
//        given: "create a new user"
//        User user = new User("Alejandro", "Peña","email@exmample.com")
//        UserGamification usGm = user.initGamification(beginnerLevel)
//        and: "has an attempt"
//        Exercise ex = usGm.getAvailableExercises().get(0)
//        validatorMock.validateAnswer("Una respuesta", ex) >> true
//        Attempt attempt = user.performAttempt(ex, "Una respuesta", validatorMock)
//        attempt.points -= 1
//        attempt.approved = false
//            
//        when: "user retries an incomplete exercise"
//        validatorMock.validateAnswer("Otra respuesta", ex) >> true
//        user.retryAttempt(attempt, "Otra respuesta", validatorMock)
//
//        then: "the attempt is valid"
//        assert attempt.approved==true
//    }

}
