package code.topia

import grails.gorm.transactions.Transactional
import org.slf4j.LoggerFactory

@Transactional
class AttemptService {

    def logger = LoggerFactory.getLogger(getClass())
    def helpService

    Help getHelp(long attemptId) {
        Attempt attempt = Attempt.get(attemptId)
        Help help = attempt.getHelp()
        help.getHelpMessage(helpService)
        help.save()
        return help
    }

    Attempt getAttempt(long attemptId) {
        return Attempt.get(attemptId)
    }
    
}
