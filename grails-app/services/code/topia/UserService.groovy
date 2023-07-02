package code.topia

import grails.gorm.transactions.Transactional
import org.slf4j.LoggerFactory

@Transactional
class UserService {
    def userGamificationService
    def exerciseValidator
    def logger = LoggerFactory.getLogger(getClass())

    def createUser(String firstName, String lastnName, String email) {
        User user = new User(firstName, lastnName, email)
        Level beginnerLevel = new Level()
        UserGamification usGm = user.initGamification(beginnerLevel)
        logger.info("[UserService] Usuario creado: ${user}")
        user.save(failOnError: true)
        beginnerLevel.save(failOnError: true)
        usGm.save(failOnError: true)
        return user
    }

    def getAvailableExercises(int userId) {
        assert userId != null
        User user = User.get(userId)
        assert user.gamification != null
        assert user.gamification.level != null
        assert user.gamification.level.getExercises() != null
        return user.gamification.level.getExercises()
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
        Attempt attempt = user.performAttempt(Exercise.get(exerciseAttemptId), answer)
        logger.info("[UserService] Usuario intento resolucion ejercicio - intento: ${attempt}")
        attempt.validateAnswser(answer, exerciseValidator)
        user.gamification.addPoints(attempt.points)
        attempt.save(failOnError: true)
        return attempt  
    }

}
