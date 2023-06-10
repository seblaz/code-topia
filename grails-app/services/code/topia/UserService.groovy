package code.topia

import grails.gorm.transactions.Transactional
import org.slf4j.LoggerFactory

@Transactional
class UserService {
    def userGamificationService
    def logger = LoggerFactory.getLogger(getClass())

    def createUser(String firstName, String lastnName, String email) {
        User user = new User(firstName, lastnName, email)
        Level beginnerLevel = Level.findByName("Beginner Level")
        UserGamification usGm = new UserGamification(user,beginnerLevel)
        logger.info("[UserService] Usuario creado: ${user}")
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
        logger.info("[UserService] Usuario intento resolucion ejercicio datos: ${userId}|${exerciseAttemptId}|${answer}")
        User user = User.get(userId)
        userGamificationService.performAttempt(user.gamification,
                                               exerciseAttemptId, answer)
        
    }

}
