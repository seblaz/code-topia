package code.topia

class UserGamification {
    static final int MIN_US_POINTS = 0
    int     userTotalPoints
    User    user
    Level   level
    List<Observer> observers

    static belongsTo = [user: User]   

    static constraints = {
        userTotalPoints nullable: false, min: MIN_US_POINTS
        level           nullable: false
    }

    void notifyAllObservers() {
        if (!this.observers.isEmpty()) {
            //FIXME: Obtener la lista del nivel..
            List<Exercise> exerciseList = []
            this.observers.each { observer ->
                try {
                    println("notificamos observer")
                    observer.notifyObserver(this.user, exerciseList)
                } catch (Exception e) {
                    // error al notificar..
                    println("error al notificar!!")
                    println(e)
                }
            }
        }
    }

    UserGamification(Level level, Observer observer = null) {
        assert level != null

        this.userTotalPoints = MIN_US_POINTS
        this.level = level
        this.observers = []
        // add observer 
        if (observer != null) {
            println("agrego observer")
            this.observers << observer
        } else {
            println("observer nulo")
        }
        this.notifyAllObservers()
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
