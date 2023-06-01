package code.topia

class Exercise {
    static final int MIN_EX_POINTS = 1
    String  title
    String  statement
    int     points
    Level   level
    List<Help> helps = []

    static constraints = {
        title       nullable: false
        statement   nullable: false
        points      nullable: false, min: MIN_EX_POINTS
        level       nullable: true
        helps       nullable: true
    }

    Exercise(String title, String statement, int points, List<Help> helps = []) {
        assert title != null
        assert statement != null
        assert points != null
        assert points >= MIN_EX_POINTS

        this.title      = title
        this.statement  = statement
        this.points     = points
        this.helps      = helps
    }
}
