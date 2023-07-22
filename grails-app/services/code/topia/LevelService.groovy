package code.topia

import grails.gorm.transactions.Transactional
import org.slf4j.LoggerFactory

@Transactional
class LevelService {
    def logger = LoggerFactory.getLogger(getClass())

    def addAttemptPoints(long levelId, int points) {
        Level level = Level.get(levelId)
        logger.info("[LevelService] addAttemptPoints: ${levelId} - ${points}")
        level.userPoints += points
        if (level.isLevelComplete()) {
            level.type = level.getNextLevel()
            level.userPoints = level.getLeftOverPoints()
            level.points = level.getLevelTypePoints()
            level.exercises = level.getTypeExercises()
            level.name = level.getTypeName()
        }
    }
}
