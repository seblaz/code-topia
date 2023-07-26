package code.topia

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class LevelSpec extends Specification implements DomainUnitTest<Level> {

    def setup() {
    }

    def cleanup() {
    }

    void "beginner level has at least 5 required points"() {
        given: "a beginner level"
        Level level = new Level()
        assert level.getLevelType() == LevelType.BEGINNER
        expect: "has 5 required points"
        assert level.getPoints() >= 5
    }

    void "advaced level has at least 5 required points"() {
        given: "a beginner level"
        Level level = new Level()
        assert level.getLevelType() == LevelType.BEGINNER
        level.updateUserPoints(level.points)
        assert level.getLevelType() == LevelType.ADVANCED
        expect: "has 5 required points"
        assert level.getPoints() >= 5
    }

    void "complete beginner level"() {
        given: "a beginner level"
        Level level = new Level()
        assert level.getLevelType() == LevelType.BEGINNER
        when: "complete the level"
        level.updateUserPoints(level.points)
        then: "level is advanced"
        assert level.getLevelType() == LevelType.ADVANCED
    }

    void "complete advanced level"() {
        given: "a advanced level"
        Level level = new Level()
        level.updateUserPoints(level.points)
        assert level.getLevelType() == LevelType.ADVANCED
        when: "complete the level"
        level.updateUserPoints(level.points)
        then: "level still advanced"
        assert level.getLevelType() == LevelType.ADVANCED
        and: "acumulate points"
        assert level.getUserPoints() == level.points
    }
    


}
