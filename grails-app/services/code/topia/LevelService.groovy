package code.topia

import grails.gorm.transactions.Transactional

@Transactional
class LevelService {

    boolean isComplete(Level level, int userGamificationPoints) {
        return level.points <= userGamificationPoints
    }

    int useLevelPoints(Level level, int userGamificationPoints) {
        assert (userGamificationPoints - level.points)>=0
        return userGamificationPoints - level.points
    }

    Level getNextLevel(Level level, UserGamification usGm) {
        if (level.getNextLevel()!=null) {
            Level new_level = Level.findByName(level.getNextLevel())
            assert new_level != null
            return new_level
        } else {
            //FIXME: alguna excepcion para atrapar luego del otro lado
            return null
        }
    }
}
