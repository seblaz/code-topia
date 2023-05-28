package code.topia

class ExerciseAttempt {
    static final int MIN_EXAT_POINTS = 0
    static final int INIT_EXAT_POINTS = 0
    int         points
    String      answer
    boolean     result
    Exercise    exercise
    User        user
    

    static constraints = {
        points      nullable: false, min: MIN_EXAT_POINTS
        exercise    nullable: false
        answer      nullable: true
        result      nullable: false
        user        nullable: false
    }

    ExerciseAttempt(User user, Exercise exercise) {
        assert user != null
        assert exercise != null

        this.user       = user
        this.exercise   = exercise
        this.points     = INIT_EXAT_POINTS
        this.result     = false
    }
}
