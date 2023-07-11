package code.topia

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class UserGamificationSpec extends Specification implements DomainUnitTest<UserGamification> {


    Level beginnerLevel = new Level()
    Level advancedLevel = new Level()
    def validatorMock = Mock(ExerciseValidator)

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
        validatorMock.validateAnswer("print('Hello World')", ex1) >> true
        Attempt attempt = user.performAttempt(ex1, "print('Hello World')", validatorMock)
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
        validatorMock.validateAnswer("print('Hello World')", ex1) >> true
        user.performAttempt(ex1, "print('Hello World')",validatorMock)
        then: "throws AttemptWithInvalidExerciseLevelException"
        thrown AttemptWithInvalidExerciseLevelException
    }

    void "Accumulate points of attempt "() {
        given: "a user"
        User user = new User("Alejandro", "Pena", "example@example.com")
        UserGamification usGm = user.initGamification(beginnerLevel)
        assert usGm.getUserPoints() == 0
        when: "perform an attempt with valid answer"
        Exercise ex1 = beginnerLevel.getExercises().get(0)
        validatorMock.validateAnswer("print('Hello World')", ex1) >> true
        Attempt attempt = user.performAttempt(ex1, "print('Hello World')", validatorMock)
        then: "user increase points"
        assert usGm.getUserPoints() > 0
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
        validatorMock.validateAnswer("print('Hello World')", ex1) >> true
        Attempt attempt = user.performAttempt(ex1, "print('Hello World')", validatorMock)
        then: "has 4 available exercises"
        List<Exercise> availableExercises = usGm.getAvailableExercises()
        assert availableExercises.size() == 4
    }

    void "get available exercises with a incomplete attempt"() {
        given: "a user"
        User user = new User("Alejandro", "Pena", "example@example.com")
        UserGamification usGm = user.initGamification(beginnerLevel)
        when: "perform a incomplete attempt"
        Exercise ex1 = beginnerLevel.getExercises().get(1)
        validatorMock.validateAnswer("print('Hello World')", ex1) >> true
        Attempt attempt = user.performAttempt(ex1, "print('Hello World')", validatorMock)
        attempt.points -= 1
        then: "has 5 available exercises"
        List<Exercise> availableExercises = usGm.getAvailableExercises()
        assert availableExercises.size() == 5
    }

    void "complete beginner level"() {
        given: "a user at beginner level"
        User user = new User("Alejandro", "Pena", "example@example.com")
        UserGamification usGm = user.initGamification(beginnerLevel)
        List<Exercise> b_exercises = usGm.getAvailableExercises()
        when: "the user complete the level with required points"
        usGm.addPoints(beginnerLevel.points)
        then: "the user level is advanced"
        assert usGm.getUserLevel().getLevelType() == LevelType.ADVANCED
        and: "it has new exerceises"
        List<Exercise> a_exercises = usGm.getAvailableExercises()
        boolean hasNewExercises = true
        a_exercises.each { a_ex ->
            b_exercises.each { b_ex ->
                if (a_ex.statement == b_ex.statement) {
                    hasNewExercises = false
                }
            }
        }
        assert hasNewExercises
    }

    
}
