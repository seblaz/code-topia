package code.topia

abstract class Level {
    static final int MIN_LVL_POINTS = 5
    static final int MIN_EXERCISES  = 5
    static final int MAX_EXERCISES  = 15
    String          name
    int             points
    List<Exercise>  exercises

    static hasMany = [exercises: Exercise]

    static constraints = {
        name        nullable: false, unique: true
        points      nullable: false, min: MIN_LVL_POINTS
        exercises   size: MIN_EXERCISES..MAX_EXERCISES
    }

    static int calculateExTotalPoints(List<Exercise> exercises) {
        int totalExPoints = 0

        for (Exercise exercise : exercises) {
            totalExPoints += exercise.points
        }

        return totalExPoints
    }


    List<Exercise> getExercises() {
        return this.exercises
    }
}

class BeginnerLevel extends Level{
    static final int MIN_REQ_POINTS = 5
    static final String BEGINNER_NAME = "Beginner Level"

    static constraints = {
    }

    BeginnerLevel() {
        this.name = BEGINNER_NAME
    }

    BeginnerLevel(List<Exercise> exercises, int points) {
        assert exercises != null
        assert points != null
        assert points >= MIN_REQ_POINTS

        // Chequeamos que con los ejercicios pueda completarse
        // los puntos del nivel.
        assert this.calculateExTotalPoints(exercises) >= points

        this.exercises  = exercises
        this.points     = points
        this.name       = BEGINNER_NAME
        
    }
}

class AdvancedLevel extends Level{
    static final int MIN_REQ_POINTS = 8
    static final String ADVANCED_NAME = "Advanced Level"

    AdvancedLevel() {
        this.name = ADVANCED_NAME
    }

    AdvancedLevel(List<Exercise> exercises, int points) {
        assert exercises != null
        assert points != null
        assert points >= MIN_REQ_POINTS

        // Chequeamos que con los ejercicios pueda completarse
        // los puntos del nivel.
        assert this.calculateExTotalPoints(exercises) >= points

        this.exercises  = exercises
        this.points     = points
        this.name       = ADVANCED_NAME
        
    }
}
