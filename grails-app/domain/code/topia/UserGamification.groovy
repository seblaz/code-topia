package code.topia

class UserGamification {
    static final int MIN_US_POINTS = 0
    int     userLevelPoints = MIN_US_POINTS
    User    user
    Level   level
    int   userTotalPoints
    int actualLevelPoints
    List<Attempt> attempts = []

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

        createAttempts()
    }

    Level getLevel() {
        return this.level
    }

    List<Attempt> getAllAttempts() {
        return this.attempts
    }

    private void createAttempts() {
        List<Exercise> exercises = this.level.getExercises()

        exercises.each { exercise ->
            Attempt attempt = new Attempt(this.user, exercise)
            attempts.add(attempt)
        }
    }

    Attempt getAttempt(int attempt_id) {
        Attempt temp = null
        this.attempts.each { attempt ->
            if (attempt.id == attempt_id) {
                temp = attempt
            }
        }
        return temp
    }
    
}
