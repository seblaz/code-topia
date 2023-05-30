package code.topia

class UserGamification {
    static final int MIN_US_POINTS = 0
    static final int MIN_LVL_POINTS = 1
    int     userTotalPoints
    int     actualLevelPoints
    User    user
    Level   level
    List<Observer> observers

    static belongsTo = [user: User]   

    static constraints = {
        userTotalPoints     nullable: false, min: MIN_US_POINTS
        actualLevelPoints   nullable: false, min: MIN_LVL_POINTS
        level               nullable: false
    }

    void notifyAllObservers() {
        if (!this.observers.isEmpty()) {
            List<Exercise> exerciseList = this.level.getExercises()
            this.observers.each { observer ->
                try {
                    observer.notifyObserver(this.user, exerciseList)
                } catch (Exception e) {
                    // error al notificar..
                    println("error al notificar al observer!!")
                    println(e)
                }
            }
        }
    }

    UserGamification(Level level) {
        assert level != null
        assert level.points >= MIN_LVL_POINTS

        this.userTotalPoints    = MIN_US_POINTS
        this.level              = level
        this.actualLevelPoints  = level.points
        this.observers          = []

    }

    void setUser(User user) {
        this.user = user
        if (user != null) {
            this.notifyAllObservers()
        }
    }

    Level getLevel() {
        return this.level
    }

    void addObserver(Observer observer) {
        if (observer != null) {
            this.observers << observer
        }
    }

    void removeObserver(Observer observer) {
        if (observer != null) {
            this.observers.remove(observer)
        }
    }
}
