package code.topia

class Attempt {
    static final int MIN_EXAT_POINTS = 0
    static final int INIT_EXAT_POINTS = 0
    int         points = INIT_EXAT_POINTS
    String      answer
    boolean     aproved = false
    Exercise    exercise
    User        user
    

    static constraints = {
        points      nullable: false, min: MIN_EXAT_POINTS
        exercise    nullable: false
        answer      nullable: true
        aproved     nullable: false
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

    boolean validateAnswser(String answer, ExerciseValidator validator) {
        assert answer != null
        assert validator != null
        
        this.answer = answer
        this.aproved = validator.validateAnswer(answer, this.exercise)
        if (this.aproved) {
            this.points = this.exercise.points
        }
        return this.aproved
    }
}
