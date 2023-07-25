package code.topia

import grails.gorm.transactions.Transactional
import org.slf4j.LoggerFactory

@Transactional
class UserGamificationService {

    def logger = LoggerFactory.getLogger(getClass())
    def attemptService
    def levelService

    List<Exercise> getAvailableExercises(long userId) {
        logger.info("[UserGamificationService] getExercises: ${userId}")
        User user = User.get(userId)
        UserGamification usGm = user.gamification
        return usGm.getAvailableExercises()
    }

    Attempt createEmptyAttempt(long userId, long exerciseId) {
        logger.info("[UserGamificationService] createEmptyAttempt: ${userId} - ${exerciseId}")
        User user = User.get(userId)
        UserGamification usGm = user.gamification
        Exercise exercise = Exercise.get(exerciseId)
        Attempt att = usGm.createEmptyAttempt(exercise)
        if (att.resetHelp()) {
            logger.info("[UserGamificationService] reseteamos los helps")
            Help.where { attempt == att }.deleteAll()
        }
        att.save(failOnError: true,flush: true)
        return att
    }

    boolean performAttempt(int userId, long attemptId, String answer) {
        logger.info("[UserGamificationService] performAttempt: ${userId} - ${attemptId} - ${answer}")
        User user = User.get(userId)
        UserGamification usGm = user.gamification
        Attempt attempt = attemptService.performAttempt(attemptId, answer)
        levelService.addAttemptPoints(usGm.level.id, usGm.calculateUserPointsDiff())
        return attempt.isCorrect()
    }

    
}


