package code.topia

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class AttemptSpec extends Specification implements DomainUnitTest<Attempt> {

    Level beginnerLevel = new Level()
    User user = new User("Alejandro", "Pena", "example@example.com")
    UserGamification usGm = user.initGamification(beginnerLevel)


    def setup() {
    }

    def cleanup() {
    }

    void "Validation attempt successful"() {
        given: "create a new attempt"
        Exercise ex = beginnerLevel.getExercises().get(0)
        Attempt attempt = user.performAttempt(ex, "Some answer")

        when: "perform validation"
        def validatorMock = Mock(ExerciseValidator)
        validatorMock.validateAnswer("Some answer", ex) >> true
        def result = attempt.validateAnswser("Some answer", validatorMock)

        then: "the new attempt is valid"
        assert result==true
    }

    void "Validation attempt successful"() {
        given: "create a new attempt"
        Exercise ex = beginnerLevel.getExercises().get(0)
        Attempt attempt = user.performAttempt(ex, "Some answer")

        when: "perform validation"
        def validatorMock = Mock(ExerciseValidator)
        validatorMock.validateAnswer("Some answer", ex) >> false
        def result = attempt.validateAnswser("Some answer", validatorMock)

        then: "the new attempt is valid"
        assert result==false
    }

}
