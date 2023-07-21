package code.topia

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import spock.lang.Specification

@Integration
@Rollback
// Ejecuta DataSeed previo..
class UserStory extends Specification {

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
        def level = userAlejandro.getLevel()
        assert level.getLevelType() == LevelType.BEGINNER

        and: "it has at least 5 exercise available"
        List<Exercise> exercises = userService.getAvailableExercises((int)userAlejandro.id)
        assert exercises.size() >= 5
    }


//    void "User performAttempt OK"() {
//        given: "there is a user"
//        User user = userService.createUser("Alejandro","Pena","ale@gmail.com")
//        def userPoints = user.gamification.userPoints
//        assert user != null
//
//        and: "with at least 1 exercise available"
//        List<Exercise> exercises = userService.getAvailableExercises((int)user.id)
//        assert exercises.size() >= 1
//
//        when: "the user perform an attempt for an exercise"
//        assert exercises[0] != null
//        Attempt attempt = userService.performAttempt((int)user.id,
//                                                     (int)exercises[0].id,
//                                                     "Una respuesta")
//        then: "the attempt is approved"
//        assert attempt.approved
//
//        and: "the user total points increase"
//        assert user.gamification.userPoints > userPoints
//    }
//
//
//    void "User Beginner level promotion to Advanced"() {
//        given: "there is a user at beginner level"
//        User user = userService.createUser("Alejandro","Pena","ale@gmail.com")
//        assert user != null
//        def userPoints = user.gamification.userPoints
//        List<Exercise> b_exercises = user.gamification.getAvailableExercises()
//        
//        when: "complete beginner level"
//        // ex0->1 punto
//        // ex3->4 puntos
//        userService.performAttempt((int)user.id, (int)b_exercises[0].id, "--una resp ok--")
//        userService.performAttempt((int)user.id, (int)b_exercises[3].id, "--una resp ok--")
//
//        then: "the actual level is advanced"
//        Level actual_level = user.getLevel()
//        assert actual_level.type == LevelType.ADVANCED
//
//        and: "has new exercises to complete"
//        List<Exercise> a_exercises = user.gamification.getAvailableExercises()
//        boolean hasNewExercises = true
//        a_exercises.each { a_ex ->
//            b_exercises.each { b_ex ->
//                if (a_ex.statement == b_ex.statement) {
//                    hasNewExercises = false
//                }
//            }
//        }
//        assert hasNewExercises
//    }


 


}
