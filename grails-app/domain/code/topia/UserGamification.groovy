package code.topia

class UserGamification {
    User    user
    Level   level
    List<Attempt> attempts = []

    static belongsTo = [user: User]

    static hasOne = [level: Level]
    static hasMany = [attempts: Attempt]

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
        this.level.userGamification = this
    }

    List<Attempt> getAllAttempts() {
        return this.attempts
    }

    Level getUserLevel() {
        return this.level
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
    
    Attempt createEmptyAttempt(Exercise exercise) {
        assert exercise != null

        for (Attempt attempt : this.attempts) {
            if ( attempt.exercise == exercise ) {
                return attempt
            }
        }
        if (exercise.level != this.level) {
            throw new AttemptWithInvalidExerciseLevelException()
        }
        Attempt attempt = new Attempt(this.user, exercise)
        this.attempts.add(attempt)
        return attempt
    }

    // devuelve la diferencia de los puntos que tiene el usuario en el nivel
    int calculateUserPointsDiff() {
        int points = 0
        this.attempts.each { attempt ->
            if (attempt.isAttemptLevelExercise(this.level)) {
                points += attempt.points
            }
        }
        // retornamos la diferencia de los puntos vs los que ya tiene en el nivel
        points -= this.level.userPoints
        if (points < 0) {
            points = 0
        }
        return points
    }

    
}
