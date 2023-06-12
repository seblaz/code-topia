package code.topia

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import spock.lang.Specification

@Integration
@Rollback
// Ejecuta DataSeed previo..
class UserSpec extends Specification {

    @Autowired
    UserService userService

    def setup() {
    }

    def cleanup() {
    }

    void "Create new user"() {
        given: "The user is no register"
        User noUser = User.findByEmail("ale@gmail.com")
        assert noUser == null

        when: "create the user"
        User user = userService.createUser("Alejandro","Pena","ale@gmail.com")

        then: "the user level is beginner"
        User userAlejandro = User.findByEmail("ale@gmail.com")
        assert userAlejandro.id == user.id
        Level level = userAlejandro.getLevel()
        assert level.name == "Beginner Level"

        //FIXME:WIP
        //and: "it has at least 5 exercise available"
        //List<Attempt> attempts = Attempt.findAllByUser(userAlejandro)
        //assert attempts.size() >= 5
    }

//FIXME:WIP
//    void "User performAttempt OK"() {
//        given: "there is a user"
//        User user = userService.createUser("Alejandro","Pena","ale@gmail.com")
//        def userPoints = user.gamification.userPoints
//        assert user != null
//
//        and: "with at least 1 exercise available"
//        List<Attempt> attempts = Attempt.findAllByUser(user)
//        assert attempts.size() >= 1
//
//        when: "the user perform an attempt for an exercise"
//        userService.performAttempt((int)user.id, (int)attempts[0].id, "--una resp ok--")
//
//        then: "the attempt is aproved"
//        assert attempts[0].aproved
//
//        and: "the user total points increase"
//        assert user.gamification.userPoints > userPoints
//    }

//FIXME:WIP
//    void "User Beginner level promotion to Advanced"() {
//        given: "there is a user at beginner level"
//        User user = userService.createUser("Alejandro","Pena","ale@gmail.com")
//        assert user != null
//        def userPoints = user.gamification.userPoints
//        List<Attempt> attempts = Attempt.findAllByUser(user)
//        def prevAttemptsSz = attempts.size()
//        
//        when: "complete beginner level"
//        // ex0->1 punto
//        // ex3->4 puntos
//        userService.performAttempt((int)user.id, (int)attempts[0].id, "--una resp ok--")
//        userService.performAttempt((int)user.id, (int)attempts[3].id, "--una resp ok--")
//
//        then: "the actual level is advanced"
//        Level actual_level = user.getLevel()
//        assert actual_level.name == "Advanced Level"
//
//        and: "has new exercises to complete"
//        assert prevAttemptsSz < user.gamification.attempts.size()
//    }


 


}
