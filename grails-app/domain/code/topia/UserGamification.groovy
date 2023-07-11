package code.topia

class UserGamification {
    User    user
    Level   level
    List<Attempt> attempts = []

    static belongsTo = [user: User]   

    static constraints = {
        user        nullable: false
        level       nullable: false
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
        return this.level.getUserPoints()
    }

    List<Exercise> getAvailableExercises() {
        List<Exercise> _exercises = this.level.getExercises()
        List<Exercise> _availableExercises = []
        _exercises.each { exercise ->
            boolean _found = false
            this.attempts.each { attempt ->
                // si es el mismo y esta completo su puntaje no mostramos.
                if (attempt.exercise.statement == exercise.statement &&
                    attempt.points == exercise.points) {
                    _found = true
                }
            }
            if (!_found) {
                _availableExercises.add(exercise)
            }
        }
        return _availableExercises
    }

    void addPoints(int points) {
        if (points < 0) throw new UserGamificationInvalidPointsException()
        this.level.acumulateUserPoints(points)
    }

    List<Attempt> getAllAttempts() {
        return this.attempts
    }

    void performAttempt(Attempt attempt, ExerciseValidator exerciseValidator) {
        if (attempt.exercise.level != this.level) {
            throw new AttemptWithInvalidExerciseLevelException()
        }
        boolean result = attempt.validateAnswser(exerciseValidator)
        if (result) {
            this.addPoints(attempt.points)
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
