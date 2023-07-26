package code.topia

import grails.gorm.transactions.Transactional
import org.slf4j.LoggerFactory

@Transactional
class UserService {
    
    def logger = LoggerFactory.getLogger(getClass())
    def userGamificationService


    User getUserByEmail(String email) {
        User user = User.findByEmail(email)
        if (user) {
            logger.info("[UserService] Usuario recuperado: ${user}")
        } else {
            logger.info("[UserService] Usuario no existe: ${email}")
            throw new UserNotExistException()
        }
        return user
    }

    User getUserById(Long user_id) {
        User user = User.get(user_id)
        if (user) {
            logger.info("[UserService] Usuario recuperado: ${user}")
        } else {
            logger.info("[UserService] Usuario no existe: ${user_id}")
            throw new UserNotExistException()
        }
        return user
    }


    User createUser(String firstName, String lastnName, String email) {
        logger.info("[UserService] Creando usuario: ${firstName} - ${lastnName} - ${email}")
        User user_temp = User.findByEmail(email)
        if (user_temp) {
            logger.info("[UserService] Usuario ya existe: ${email}")
            throw new UserAlreadyExistException()
        }
        User user = new User(firstName, lastnName, email)
        Level beginnerLevel = new Level()
        UserGamification usGm = user.initGamification(beginnerLevel)
        logger.info("[UserService] Usuario creado: ${user}")
        user.save(failOnError: true)
        beginnerLevel.save(failOnError: true)
        usGm.save(failOnError: true)
        return user
    }


    List<Exercise> getUserExercises(User user) {
        logger.info("[UserService] Obtenemos ejercicios del usuario: ${user}")
        assert user != null
        assert user.gamification != null
        List<Exercise> a = userGamificationService.getAvailableExercises(user.gamification.id)
        logger.info("[UserService] lista del : ${user} es:${a}")
        return a
    }




    List<Exercise> getAvailableExercises(int userId) {
        assert userId != null
        User user = User.get(userId)
        assert user.gamification != null
        assert user.gamification.level != null
        return userGamificationService.getAvailableExercises(user.gamification.id)
    }



}
