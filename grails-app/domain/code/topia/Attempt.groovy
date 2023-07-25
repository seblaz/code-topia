package code.topia

class Attempt {
    static final int MIN_EXAT_POINTS = 0
    static final int INIT_EXAT_POINTS = 0
    static final int MAX_HELP = 3
    int         points = INIT_EXAT_POINTS
    String      answer
    boolean     approved = false
    Exercise    exercise
    User        user
    List<Help>  helps = []

    static belongsTo = [exercise: Exercise, user: User]
    static hasMay = [helps: Help]

    static constraints = {
        points      min: MIN_EXAT_POINTS
        exercise    nullable: false
        answer      nullable: true
        user        nullable: false
    }

    Attempt(User user, Exercise exercise) {
        assert user != null
        assert exercise != null

        this.user       = user
        this.exercise   = exercise
    }

    Attempt(User user, Exercise exercise, String answer) {
        assert user != null
        assert exercise != null
        assert answer != null

        this.user       = user
        this.exercise   = exercise
        this.answer     = answer
    }

    boolean isComplete() {
        return (this.approved && this.points == this.exercise.points)
    }

    boolean validateAnswer(ExerciseValidator validator) {
        assert validator != null

        if (this.answer == null || this.answer.isEmpty()){
            return false
        }

        if (this.isComplete()) {
            throw new AttemptAlreadyCompleteException()
        }
        
        this.approved = validator.validateAnswer(this.answer, this.exercise)
        return this.approved
    }

    Help getHelp() {
        if (this.helps.size() < MAX_HELP) {
            Help help = new Help()
            help.attempt = this
            this.helps.add(help)
            return help
        }
        throw new MaxHelpException()
    }

    // Calcula el puntaje del intento
    // si es menor el nuevo puntaje que el anterior 
    // se queda con el anterior si es mayor.
    void calculatePoints() {
        int temp_points = 0
        if (this.approved) {
            temp_points = this.exercise.points - this.helps.size()
            if (temp_points < 0) {
                temp_points = 0
            }
            if (temp_points < this.points) {
                temp_points = this.points
            }
            this.points = temp_points
        }
    }

    boolean isCorrect() {
        return this.approved
    }

    boolean resetHelp() {
        if ( (this.answer &&
              !this.approved &&
              this.helps.size() == MAX_HELP ) ||
             (this.answer &&
              this.approved &&
              this.helps.size() > 0)) {
            this.helps.clear()
            return true
        }
        return false
    }


    boolean isAttemptLevelExercise(Level level) {
        assert level != null

        return this.exercise.isLevelExercise(level)
    }
}
