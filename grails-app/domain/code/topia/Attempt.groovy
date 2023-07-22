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
        
        return validator.validateAnswer(this.answer, this.exercise)
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

    int calculatePoints() {
        int temp_points = 0
        if (this.approved) {
            temp_points = this.exercise.points - this.helps.size()
            if (temp_points < 0) {
                temp_points = 0
            }
        }
        return temp_points
    }

    boolean isCorrect() {
        return this.approved
    }

    boolean checkResetHelp() {
        if ( (this.answer &&
              !this.approved &&
              this.helps.size() == MAX_HELP ) ||
             (this.answer &&
              this.approved &&
              this.helps.size() > 0)) {
            return true
        }
        return false
    }
}
