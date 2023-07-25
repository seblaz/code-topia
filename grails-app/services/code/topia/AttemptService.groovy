package code.topia

import grails.gorm.transactions.Transactional
import org.slf4j.LoggerFactory

@Transactional
class AttemptService {

    def logger = LoggerFactory.getLogger(getClass())
    def exerciseValidator
    def helpService

    Attempt performAttempt(long attemptId, String answer) {
        Attempt attempt = Attempt.get(attemptId)
        logger.info("[AttemptService] performAttempt: ${attemptId} - ${answer}")
        attempt.answer = answer
        attempt.validateAnswer(exerciseValidator)
        attempt.calculatePoints()
        logger.info("[AttemptService] attempt puntos: ${attempt.points}")
        return attempt
    }

    Help getHelp(long attemptId) {
        Attempt attempt = Attempt.get(attemptId)
        Help help = attempt.getHelp()
        help.helpMessage = help.getHelpMessage(helpService)
        help.save()
        return help
    }

    Attempt getAttempt(long attemptId) {
        return Attempt.get(attemptId)
    }
    
}
