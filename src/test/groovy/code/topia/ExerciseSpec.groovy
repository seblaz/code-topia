package code.topia

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ExerciseSpec extends Specification implements DomainUnitTest<Exercise> {

    def setup() {
    }

    def cleanup() {
    }

    void "create exercise Hello World"() {
        given:
        def statement = "Escribe un programa en C que imprima '¡Hola, mundo!' en la pantalla"
        def points = 1

        when:
        def ex = new Exercise("Un titulo", statement, points, LevelType.BEGINNER)

        then:
        ex.validate()
    }
}
