package code.topia

import grails.gorm.transactions.Transactional

@Transactional
class AttemptService {

    def performAttempt(Attempt attempt, String answer) {
        //TODO: llamar a validar al ejercicio
        //attempt.exercise.validate(answer)
        int attemptPoints = 0
        //FIXME: esto es para no tener un validador por el momento!
        if (answer == "--una resp ok--") {
            attempt.answer = answer
            attempt.aproved = true
            attempt.points = attempt.exercise.points
            attemptPoints = attempt.points
        }
        return attemptPoints
    }
}
