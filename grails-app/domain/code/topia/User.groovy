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

    boolean performAttempt(int attempt_id, String answer) {
        println("respuesta aca!")
        firstName = answer
        return true
    }

}
