package code.topia

class Exercise {
    static final int MIN_EX_POINTS = 1
    String  statement
    String  anwser
    int     points
    Boolean approved

    static constraints = {
        statement nullable: false
        anwser    nullable: true
        points    nullable: false, min: MIN_EX_POINTS
        approved  nullable: true
    }

    Exercise(String statement, int points) {
        assert statement != null
        assert points != null
        assert points >= MIN_EX_POINTS

        this.statement  = statement
        this.points     = points
    }
}
