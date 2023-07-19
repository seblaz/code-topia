package code.topia

import grails.gorm.transactions.Transactional
import org.slf4j.LoggerFactory

@Transactional
class UserGamificationService {

    def logger = LoggerFactory.getLogger(getClass())
    def attemptService
    def levelService

    def performAttempt(int userId, int attemptId, String answer) {
        logger.info("[UserGamificationService] performAttempt: ${userId} - ${attemptId} - ${answer}")
        User user = User.get(userId)
        UserGamification usGm = user.gamification
        //TODO: chequear que el attempt este contenido en usGm
        
        Attempt attempt = attemptService.performAttempt(attemptId, answer)
        levelService.addAttemptPoints((int)usGm.level.id, attempt.points)

    }

    List<Exercise> getAvailableExercises(long userId) {
        logger.info("[UserGamificationService] getExercises: ${userId}")
        User user = User.get(userId)
        UserGamification usGm = user.gamification
        return usGm.getAvailableExercises()
    }

    
}


