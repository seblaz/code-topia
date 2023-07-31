package code.topia

class Exercise {
    static final int MIN_EX_POINTS = 1
    String  title
    String  statement
    int     points
    Level   level
    LevelType levelType


    static constraints = {
        title       nullable: false
        statement   nullable: false
        points      min: MIN_EX_POINTS
        level       nullable: true
        levelType   nullable: true
    }

    Exercise(String title, String statement, int points, LevelType levelType) {
        assert title != null
        assert statement != null
        assert points != null
        assert points >= MIN_EX_POINTS

        this.title      = title
        this.statement  = statement
        this.points     = points
        this.levelType  = levelType
    }

    void setLevel(Level level) {
        assert level != null
        this.levelType = level.type
        this.level = level
    }

    boolean isLevelExercise(Level level) {
        return this.levelType == level.type
    }

    boolean isEqualToExercise(Exercise exercise) {
        return this.statement == exercise.statement
    }
}
