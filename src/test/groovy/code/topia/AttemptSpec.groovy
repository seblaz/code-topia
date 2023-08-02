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
        Attempt attempt = usGm.createEmptyAttempt(ex)

        then: "the new attempt is valid"
        assert attempt.validateAnswer(validatorMock,"Some answer")==true
        and: "is complete"
        assert attempt.isComplete()==true
    }

    void "Validation attempt not successful"() {
        given: "exists an exercise"
        Exercise ex = beginnerLevel.getExercises().get(0)

        when: "perform attempt with invalid answer"
        validatorMock.validateAnswer("Some answer", ex) >> false
        Attempt attempt = usGm.createEmptyAttempt(ex)

        then: "the new attempt is not valid"
        assert attempt.validateAnswer(validatorMock,"Some answer")==false
    }

    void "Attempt could get 3 helps"() {
        given: "exists an exercise"
        Exercise ex = beginnerLevel.getExercises().get(0)

        when: "ask for 3 helps"
        Attempt attempt = usGm.createEmptyAttempt(ex)
        Help h1 = attempt.getHelp()
        Help h2 = attempt.getHelp()
        Help h3 = attempt.getHelp()
        Help h4 = null
        h4 = attempt.getHelp()

        then: "there are 3 helps"
        thrown MaxHelpException
        assert h1 != null
        assert h2 != null
        assert h3 != null
        assert h4 == null
    }

    void "Attempt complete with help"() {
        given: "exists an exercise"
        Exercise ex = beginnerLevel.getExercises().get(0)

        when: "perform attempt with valid answer"
        validatorMock.validateAnswer("Some answer", ex) >> true
        Attempt attempt = usGm.createEmptyAttempt(ex)
        Help h1 = attempt.getHelp()
        assert h1 != null
        assert attempt.helps.size() == 1

        then: "the new attempt is valid"
        assert attempt.validateAnswer(validatorMock,"Some answer")==true
        and: "the attempt points is lower than the exercise points"
        assert attempt.points < ex.points
    }

}
