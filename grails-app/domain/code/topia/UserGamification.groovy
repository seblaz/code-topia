package code.topia

class UserGamification {
    static final int MIN_US_POINTS = 0
    int     userPoints = MIN_US_POINTS
    User    user
    Level   level
    List<Attempt> attempts = []

    static belongsTo = [user: User]   

    static constraints = {
        userPoints     min: MIN_US_POINTS
        level    nullable: false
    }

    UserGamification(User user, Level level) {
        assert user != null
        assert level != null

        if (level.getLevelType() != LevelType.BEGINNER) {
            throw new UserGamificationInvalidLevelException()
        }

        this.user = user
        this.level = level
    }

    Level getUserLevel() {
        return this.level
    }

    int getUserPoints() {
        return this.userPoints
    }

    void addPoints(int points) {
        if (points < 0) throw new UserGamificationInvalidPointsException()
        //if (this.userPoints + points >= this.level.points) {
        //    this.userPoints = this.userPoints + points - this.level.points
        //    this.completeLevels.add(this.level)
        //    this.level = this.level.getNextLevelClass()
        //} else {
        //    this.userPoints += points
        //}
        this.level.acumulateUserPoints(points)
        this.userPoints += points
    }

    List<Attempt> getAllAttempts() {
        return this.attempts
    }

    void addAttempt(Attempt attempt) {
        if (attempt.exercise.level != this.level) {
            throw new AttemptWithInvalidExerciseLevelException()
        }
        if (!this.attempts.contains(attempt)) {
            this.attempts.add(attempt)
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

    void setLevel(Level level) {
        this.level = level
    }
    
}
