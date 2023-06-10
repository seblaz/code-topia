package code.topia

class User {

    String              firstName
    String              lastName
    String              email
    UserGamification    gamification

    static hasOne = [gamification: UserGamification]

    static constraints = {
        firstName       nullable: false, blank: false
        lastName        nullable: false, blank: false
        email           nullable: false, blank: false, email: true, unique: true
        gamification    nullable: false
    }

    User(String firstName, String lastName, String email) {
        assert firstName != null
        assert lastName != null
        assert email != null

        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        
    }

    Level getLevel() {
        return this.gamification.getUserLevel()
    }

    UserGamification initGamification(Level level) {
        this.gamification = new UserGamification(this, level)
        return this.gamification
    }

    Attempt performAttempt(Exercise exercise, String answer) {
        Attempt attempt = new Attempt(this, exercise, answer)
        this.gamification.addAttempt(attempt)
        return attempt
    }

}
