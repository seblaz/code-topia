package code.topia

import grails.gorm.transactions.Transactional

@Transactional
class UserGamificationService {


    def increasePoints(User user, int points) {
        user.gamification.userTotalPoints += points
        if (user.gamification.userTotalPoints >= user.gamification.actualLevelPoints) {
            // Avanzar nivel
        }

    }
}
