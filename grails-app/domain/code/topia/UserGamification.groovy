package code.topia

class UserGamification {
    static final int MIN_US_POINTS = 0
    int     userPoints = MIN_US_POINTS
    User    user
    Level   actualLevel
    List<Level> completeLevels = []
    List<Attempt> attempts = []

    static belongsTo = [user: User]   

    static constraints = {
        userPoints     min: MIN_US_POINTS
        actualLevel    nullable: false
    }

    UserGamification(User user, Level level) {
        assert user != null
        assert level != null

        if (!(level instanceof BeginnerLevel)) {
            throw new UserGamificationInvalidLevelException()
        }

        this.user = user
        this.actualLevel = level
        user.gamification = this

        //createAttempts()
    }

    Level getUserLevel() {
        return this.actualLevel
    }

    int getUserPoints() {
        return this.userPoints
    }

    List<Attempt> getAllAttempts() {
        return this.attempts
    }

    void addAttempt(Attempt attempt) {
        if (attempt.exercise.level != this.actualLevel) {
            throw new AttemptWithInvalidExerciseLevelException()
        }
        if (!this.attempts.contains(attempt)) {
            this.attempts.add(attempt)
        }
    }

    //private void createAttempts() {
    //    List<Exercise> exercises = this.level.getExercises()
    //    exercises.each { exercise ->
    //        Attempt attempt = new Attempt(this.user, exercise)
    //        attempts.add(attempt)
    //    }
    //}

    Attempt getAttempt(int attempt_id) {
        Attempt temp = null
        this.attempts.each { attempt ->
            if (attempt.id == attempt_id) {
                temp = attempt
            }
        }
        return temp
    }

    void setLevel(Level level) {
        this.actualLevel = level
        //if (level != null) {
        //    this.createAttempts()
        //}
    }
    
}
