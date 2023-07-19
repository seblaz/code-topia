package code.topia

import grails.gorm.transactions.Transactional
import org.slf4j.LoggerFactory

@Transactional
class LevelService {
    def logger = LoggerFactory.getLogger(getClass())

    def addAttemptPoints(int levelId, int points) {
        Level level = Level.get(levelId)
        logger.info("[LevelService] addAttemptPoints: ${levelId} - ${points}")
        level.userPoints += points
        if (level.isLevelComplete()) {
            level.type = level.getNextLevel()
            // si se paso lo que le falta lo acumula en el siguiente nivel
            level.userPoints = level.userPoints - level.points
            level.points = level.getLevelTypePoints()
            level.exercises = level.getTypeExercises()
        }   
    }
}
