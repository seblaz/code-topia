package code.topia
import org.slf4j.LoggerFactory
import grails.gorm.transactions.Transactional

@Transactional
class ExerciseService {

    def logger = LoggerFactory.getLogger(getClass())

    Exercise getExercise(long exerciseId) {
        logger.info("[ExerciseService] Buscamos ejercicio: ${exerciseId}")
        Exercise exercise = Exercise.get(exerciseId)
        if (exercise) {
            logger.info("[ExerciseService] Ejercicio recuperado: ${exercise}")
        } else {
            logger.info("[ExerciseService] Ejercicio no existe: ${exerciseId}")
            throw new ExerciseNotExistException()
        }
        return exercise
    }
}
