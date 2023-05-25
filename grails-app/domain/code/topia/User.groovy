package code.topia

class User {

    String  firstName
    String  lastName
    String  email

    static constraints = {
        firstName nullable: false, blank: false
        lastName nullable: false, blank: false
        email nullable: false, blank: false, email: true, unique: true

    }



    User(String firstName, String lastName, String email) {
        assert firstName != null
        assert lastName != null
        assert email != null

        this.firstName  = firstName
        this.lastName   = lastName
        this.email      = email
        
    }


}
