package code.topia

class Attempt {
    static final int MIN_EXAT_POINTS = 0
    static final int INIT_EXAT_POINTS = 0
    int         points = INIT_EXAT_POINTS
    String      answer
    boolean     approved = false
    Exercise    exercise
    User        user
    

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

    boolean validateAnswser(ExerciseValidator validator) {
        assert validator != null

        if (this.answer == null || this.answer.isEmpty()){
            return false
        }
        
        this.approved = validator.validateAnswer(this.answer, this.exercise)
        if (this.approved) {
            this.points = this.exercise.points
        }
        return this.approved
    }



    ////////////////////////////////////////////////////////////////////////////
    boolean validateAnswer2(ExerciseValidator validator) {
        assert validator != null

        if (this.answer == null || this.answer.isEmpty()){
            return false
        }
        
        return validator.validateAnswer(this.answer, this.exercise)
    }

    int calculatePoints() {
        int temp_points = 0
        if (this.approved) {
            //TODO: aca restar los puntos por la ayuda solicitada.
            temp_points = this.exercise.points
        }
        return temp_points
    }
}
