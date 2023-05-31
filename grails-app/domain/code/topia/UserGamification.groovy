package code.topia
//import org.springframework.transaction.annotation.Transactional

class UserGamification {


    static final int MIN_US_POINTS = 0
    int     userLevelPoints = MIN_US_POINTS
    User    user
    Level   level
    int   userTotalPoints
    int actualLevelPoints
    List<Attempt> exerciseAttempts = []

 
    static belongsTo = [user: User]   

    static constraints = {
        userTotalPoints     nullable: false, min: MIN_US_POINTS
        level               nullable: false
    }

    UserGamification(User user, Level level) {
        //logger.debug("Creando UserGamification con user:${user}|level:${level}")
        this.user = user
        this.level = level
        user.gamification = this

        createExerciseAttempts()
    }

    Level getLevel() {
        return this.level
    }

    //@Transactional
    private void createExerciseAttempts() {
        List<Exercise> exercises = this.level.getExercises()

        exercises.each { exercise ->
            Attempt attempt = new Attempt(this.user, exercise)
            exerciseAttempts.add(attempt)
        }
    }
    
}
