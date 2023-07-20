package code.topia

import grails.gorm.transactions.Transactional
import org.slf4j.LoggerFactory

@Transactional
class AttemptService {

    def logger = LoggerFactory.getLogger(getClass())
    def exerciseValidator

    Attempt performAttempt(long attemptId, String answer) {
        Attempt attempt = Attempt.get(attemptId)
        logger.info("[AttemptService] performAttempt: ${attemptId} - ${answer}")
        attempt.answer = answer
        attempt.approved = attempt.validateAnswer(exerciseValidator)
        attempt.points = attempt.calculatePoints()
        logger.info("[AttemptService] attempt puntos: ${attempt.points}")
        return attempt
    }
    
}
