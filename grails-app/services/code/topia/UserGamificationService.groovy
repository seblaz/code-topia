package code.topia

import grails.gorm.transactions.Transactional

@Transactional
class UserGamificationService {

    def attemptService

    def getAttempt(UserGamification usGm, int attempt_id) {
        return usGm.getAttempt(attempt_id)
    }


    def performAttempt(UserGamification usGm, int attempt_id, String answer) {
        Attempt at = usGm.getAttempt(attempt_id)
        return attemptService.performAttempt(at, answer)
    }

    def increasePoints(User user, int points) {
        user.gamification.userTotalPoints += points
        if (user.gamification.userTotalPoints >= user.gamification.actualLevelPoints) {
            // Avanzar nivel
        }

    }
}
