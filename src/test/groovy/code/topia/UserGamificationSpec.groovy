package code.topia

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class UserGamificationSpec extends Specification implements DomainUnitTest<UserGamification> {


    Level beginnerLevel = new Level()
    Level advancedLevel = new Level()

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
        advancedLevel.type = LevelType.ADVANCED
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
        Exercise ex1 = beginnerLevel.getExercises().get(0)
        Attempt attempt = user.performAttempt(ex1, "print('Hello World')")
        then: "usergamification has the attempt"
        assert usGm.getAllAttempts().contains(attempt)
    }

    void "add an attempt with invalid exercise level"() {
        given: "a user"
        User user = new User("Alejandro", "Pena", "example@example.com")
        UserGamification usGm = user.initGamification(beginnerLevel)
        when: "add an attempt with invalid exercise level"
        advancedLevel.type = LevelType.ADVANCED
        Exercise ex1 = advancedLevel.getExercises().get(0)
        usGm.addAttempt(user.performAttempt(ex1, "print('Hello World')"))
        then: "throws AttemptWithInvalidExerciseLevelException"
        thrown AttemptWithInvalidExerciseLevelException
    }

    void "not repeat an attempt when add one"() {
        given: "a user"
        User user = new User("Alejandro", "Pena", "example@example.com")
        UserGamification usGm = user.initGamification(beginnerLevel)
        when: "add an attempt twice"
        Exercise ex1 = beginnerLevel.getExercises().get(0)
        Attempt attempt = user.performAttempt(ex1, "print('Hello World')")
        usGm.addAttempt(attempt)
        usGm.addAttempt(attempt)
        then: "usergamification has only one attempt"
        assert usGm.getAllAttempts().size() == 1
        
    }

    void "add points to user"() {
        given: "a user"
        User user = new User("Alejandro", "Pena", "example@example.com")
        UserGamification usGm = user.initGamification(beginnerLevel)
        when: "add points to user"
        usGm.addPoints(2)
        then: "user has 10 points"
        assert usGm.getUserPoints() == 2
    }


    void "complete level"() {
        given: "a user"
        User user = new User("Alejandro", "Pena", "example@example.com")
        UserGamification usGm = user.initGamification(beginnerLevel)
        when: "the user complete the level"
        usGm.addPoints(beginnerLevel.points)
        then: "has a new level"
        assert usGm.getUserLevel().getLevelType() == LevelType.ADVANCED
    }

    void "get available exercises"() {
        given: "a user"
        User user = new User("Alejandro", "Pena", "example@example.com")
        UserGamification usGm = user.initGamification(beginnerLevel)
        when: "the user get available exercises"
        List<Exercise> availableExercises = usGm.getAvailableExercises()
        then: "has 5 available exercises"
        assert availableExercises.size() == 5
    }

    void "get available exercises with an attempt"() {
        given: "a user"
        User user = new User("Alejandro", "Pena", "example@example.com")
        UserGamification usGm = user.initGamification(beginnerLevel)
        when: "perform an attempt"
        Exercise ex1 = beginnerLevel.getExercises().get(0)
        Attempt attempt = user.performAttempt(ex1, "print('Hello World')")
        usGm.addAttempt(attempt)
        //FIXME: deberia ser solo si esta completo la respuesta del ejercicio
        // sin error, en su totalidad de puntaje.
        then: "has 4 available exercises"
        List<Exercise> availableExercises = usGm.getAvailableExercises()
        assert availableExercises.size() == 4
    }
}
