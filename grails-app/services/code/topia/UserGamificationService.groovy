package code.topia

import grails.gorm.transactions.Transactional

@Transactional
class UserGamificationService {

    def attemptService
    def levelService

    def getAttempt(UserGamification usGm, int attempt_id) {
        return usGm.getAttempt(attempt_id)
    }

    def updatePointsAttempt(UserGamification usGm, int points) {
        usGm.userPoints += points
        Level level = usGm.getUserLevel()
        int userPoints = usGm.getUserPoints()
        if (levelService.isComplete(level, userPoints)) {
            // Avanzar nivel
            usGm.userPoints = levelService.useLevelPoints(level, userPoints)
            usGm.level = levelService.getNextLevel(level, usGm)
        }
    }

    def performAttempt(UserGamification usGm, int attempt_id, String answer) {
        Attempt at = usGm.getAttempt(attempt_id)
        int attemptPoints = attemptService.performAttempt(at, answer)
        updatePointsAttempt(usGm, attemptPoints)
        return attemptPoints
    }

}
