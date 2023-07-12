package code.topia

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class AttemptSpec extends Specification implements DomainUnitTest<Attempt> {

    Level beginnerLevel = new Level()
    User user = new User("Alejandro", "Pena", "example@example.com")
    UserGamification usGm = user.initGamification(beginnerLevel)
    def validatorMock = Mock(ExerciseValidator)


    def setup() {
    }

    def cleanup() {
    }

    void "Validation attempt successful"() {
        given: "exists an exercise"
        Exercise ex = beginnerLevel.getExercises().get(0)

        when: "perform attempt with valid answer"
        validatorMock.validateAnswer("Some answer", ex) >> true
        Attempt attempt = user.performAttempt(ex, "Some answer",validatorMock)

        then: "the new attempt is valid"
        assert attempt.approved==true
    }

    void "Validation attempt not successful"() {
        given: "exists an exercise"
        Exercise ex = beginnerLevel.getExercises().get(0)

        when: "perform attempt with invalid answer"
        validatorMock.validateAnswer("Some answer", ex) >> false
        Attempt attempt = user.performAttempt(ex, "Some answer", validatorMock)

        then: "the new attempt is not valid"
        assert attempt.approved==false
    }

}
