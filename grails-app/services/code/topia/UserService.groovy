package code.topia

import grails.gorm.transactions.Transactional

@Transactional
class UserService {
    def userGamificationService

    def createUser(String firstName, String lastnName, String email) {
        User user = new User(firstName, lastnName, email)
        Level beginnerLevel = Level.findByName("Beginner Level")
        UserGamification usGm = new UserGamification(user,beginnerLevel)
        
        user.save(failOnError: true)
        return user
    }

    def getAllExercises(User user) {
        return user.gamification.getAllAttempts()
    }

    def getAttempt(User user, int attempt_id) {
        return userGamificationService.getAttempt(user.gamification, attempt_id)
    }

    def performAttempt(int userId, int exerciseAttemptId, String answer) {
        User user = User.get(userId)
        userGamificationService.performAttempt(user.gamification,
                                               exerciseAttemptId, answer)
        user.save(failOnError: true)
        
    }

}
