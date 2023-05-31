package code.topia

import grails.gorm.transactions.Transactional

@Transactional
class AttemptService {

    def performAttempt(Attempt attempt, String answer) {
        //TODO: llamar a validar al ejercicio
        //attempt.exercise.validate(answer)
        attempt.answer = answer
        attempt.aproved = true
        attempt.points = attempt.exercise.points
    }
}
