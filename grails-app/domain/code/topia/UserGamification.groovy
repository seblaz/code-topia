package code.topia

class UserGamification {
    static final int MIN_US_POINTS = 0
    int     userTotalPoints
    User    user
    Level   level

    static belongsTo = [user: User]   

    static constraints = {
        userTotalPoints nullable: false, min: MIN_US_POINTS
        level           nullable: false
    }

    UserGamification(Level level) {
        assert level != null

        this.userTotalPoints = MIN_US_POINTS
        this.level = level
    }

    Level getLevel() {
        return this.level
    }
}
