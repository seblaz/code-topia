package code.topia

import grails.gorm.transactions.Transactional
import org.slf4j.LoggerFactory

@Transactional
class UserService {
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

    List<Exercise> getAvailableExercises(int userId) {
        assert userId != null
        User user = User.get(userId)
        assert user.gamification != null
        assert user.gamification.level != null
        return user.gamification.getAvailableExercises()
    }

    Attempt performAttempt(int userId, int exerciseAttemptId, String answer) {
        logger.info("[UserService] Usuario intento resolucion ejercicio datos: ${userId}|${exerciseAttemptId}|${answer}")
        User user = User.get(userId)
        Attempt attempt = user.performAttempt(Exercise.get(exerciseAttemptId), answer, exerciseValidator)
        logger.info("[UserService] Usuario intento resolucion ejercicio - intento: ${attempt}")
        attempt.save(failOnError: true)
        return attempt  
    }

    List<Attempt> getAllExercises(User user) {
        return user.gamification.getAllAttempts()
    }

}
