package code.topia

class UserGamification {

    int userTotalPoints

    Level level

    static hasOne = [level: Level]

    static constraints = {
        userTotalPoints nullable: false, min: 0
        level nullable: false
    }

    UserGamification(Level level) {
        assert level != null

        this.userTotalPoints = 0
        this.level = level
    }
}
