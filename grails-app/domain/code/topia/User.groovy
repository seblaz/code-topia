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



    User(String firstName, String lastName, String email, UserGamification gamification) {
        assert firstName != null
        assert lastName != null
        assert email != null
        assert gamification != null

        this.firstName      = firstName
        this.lastName       = lastName
        this.email          = email
        this.gamification   = gamification
        
    }


}
