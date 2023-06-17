package code.topia

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class AttemptSpec extends Specification implements DomainUnitTest<Attempt> {

    BeginnerLevel beginnerLevel = new BeginnerLevel()
    User user = new User("Alejandro", "Pena", "example@example.com")
    UserGamification usGm = user.initGamification(beginnerLevel)


    def setup() {
    }

    def cleanup() {
    }

    //FIXME: WIP
    void "Validation attempt successful"() {
        given: "create a new attempt"
        Exercise ex = beginnerLevel.getExercises().get(0)
        Attempt attempt = user.performAttempt(ex, "Some answer")

        when: "perform validation"
        def result = attempt.validateAnwser()

        then: "the new attempt is valid"
        assert result        
    }

}
