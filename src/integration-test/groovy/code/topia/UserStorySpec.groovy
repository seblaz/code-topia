package code.topia

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import spock.lang.Specification

@Integration
@Rollback
// Ejecuta DataSeed previo..
class UserStory extends Specification {

    def exerciseValidatorMock = Mock(ExerciseValidator)
    def helpServiceMock = Mock(HelpService)
    @Autowired
    UserService userService
    @Autowired
    UserGamificationService userGamificationService
    @Autowired
    AttemptService attemptService

    def setup() {
    }

    def cleanup() {
    }

    // Historia de usuario: 1.01
    void "New User: Beginner Level Exercises"() {
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

    // Historia de usuario: 1.02
    void "Beginner User: Earn 1 Point for Correct Exercise"() {
        given: "there is a user"
        User user = userService.createUser("Alejandro","Pena","ale@gmail.com")
        assert user != null
        def userPoints = user.gamification.level.userPoints
        attemptService.exerciseValidator = exerciseValidatorMock
        userGamificationService.attemptService = attemptService
        exerciseValidatorMock.validateAnswer(_,_) >> true
        
        and: "with at least 1 exercise available"
        List<Exercise> exercises = userService.getAvailableExercises((int)user.id)
        assert exercises.size() >= 1
        
        when: "the user perform an attempt for an exercise"
        assert exercises[0] != null
        Attempt attempt = userGamificationService.createEmptyAttempt((int)user.id,
                                                                     (int)exercises[0].id)
        assert attempt != null
        userGamificationService.performAttempt((int)user.id,
                                                attempt.id,
                                                "Una respuesta")
        
        then: "the attempt is approved"
        assert attempt.approved
        
        and: "the user total points increase"
        assert user.gamification.level.userPoints > userPoints
    }



    // Historia de usuario: 2.01
    void "User Beginner level promotion to Advanced"() {
        given: "there is a user at beginner level"
        User user = userService.createUser("Alejandro","Pena","ale@gmail.com")
        assert user != null
        attemptService.exerciseValidator = exerciseValidatorMock
        userGamificationService.attemptService = attemptService
        exerciseValidatorMock.validateAnswer(_,_) >> true
        
        List<Exercise> exercises_a = userService.getAvailableExercises((int)user.id)
        
        when: "complete beginner level"
        // ex0->1 punto
        // ex3->4 puntos
        assert exercises_a[0] != null
        assert exercises_a[3] != null
        Attempt attempt1 = userGamificationService.createEmptyAttempt((int)user.id,
                                                                      (int)exercises_a[0].id)
        Attempt attempt2 = userGamificationService.createEmptyAttempt((int)user.id,
                                                                      (int)exercises_a[3].id)
        assert attempt1 != null
        assert attempt2 != null
        userGamificationService.performAttempt((int)user.id,
                                                attempt1.id,
                                                "Una respuesta")
        userGamificationService.performAttempt((int)user.id,
                                                attempt2.id,
                                                "Una respuesta")

        then: "the actual level is advanced"
        Level actual_level = user.getLevel()
        assert actual_level.type == LevelType.ADVANCED

        and: "has new exercises to complete"
        List<Exercise> exercises_b = userService.getAvailableExercises((int)user.id)
        boolean hasNewExercises = true
        exercises_b.each { b_ex ->
            exercises_a.each { a_ex ->
                if (a_ex.statement == b_ex.statement) {
                    hasNewExercises = false
                }
            }
        }
        assert hasNewExercises
    }




    // Historia de usuario: 7.01
    void "Helps user when stuck"() {
        given: "there is a user at beginner level"
        User user = userService.createUser("Alejandro","Pena","ale@gmail.com")
        assert user != null
        attemptService.helpService = helpServiceMock
        helpServiceMock.getHelpMessage(_) >> "Una ayuda"
        
        List<Exercise> exercises_a = userService.getAvailableExercises((int)user.id)
        
        when: "the user does not know how to solve an exercise, and ask for help"
        assert exercises_a[0] != null
        Attempt attempt = userGamificationService.createEmptyAttempt((int)user.id,
                                                                      (int)exercises_a[0].id)
        assert attempt != null
        Help help = attemptService.getHelp(attempt.id)

        then: "the help is provided"
        assert help.helpMessage != null
        
    }
    // Historia de usuario: 7.02
    void "Helps user when stuck - limit 3"() {
        given: "there is a user at beginner level"
        User user = userService.createUser("Alejandro","Pena","ale@gmail.com")
        assert user != null
        attemptService.helpService = helpServiceMock
        helpServiceMock.getHelpMessage(_) >> "Una ayuda"
        
        List<Exercise> exercises_a = userService.getAvailableExercises((int)user.id)
        
        when: "the user does not know how to solve an exercise, and ask for help more than 3 times"
        assert exercises_a[0] != null
        Attempt attempt = userGamificationService.createEmptyAttempt((int)user.id,
                                                                      (int)exercises_a[0].id)
        assert attempt != null
        Help help = attemptService.getHelp(attempt.id)
        Help help2 = attemptService.getHelp(attempt.id)
        Help help3 = attemptService.getHelp(attempt.id)
        Help help4 = null
        help4 = attemptService.getHelp(attempt.id)

        then: "getHelp throws MaxHelpException and only 3 helps are provided"
        thrown MaxHelpException
        assert help.helpMessage != null
        assert help2.helpMessage != null
        assert help3.helpMessage != null
        assert help4 == null
    }
 

    // Historia de usuario: 7.03
    void "Exercise Assistance: Reduced Score for Help"() {
        given: "there is a user at beginner level"
        User user = userService.createUser("Alejandro","Pena","ale@gmail.com")
        assert user != null
        attemptService.helpService = helpServiceMock
        helpServiceMock.getHelpMessage(_) >> "Una ayuda"
        attemptService.exerciseValidator = exerciseValidatorMock
        userGamificationService.attemptService = attemptService
        exerciseValidatorMock.validateAnswer(_,_) >> true
        
        List<Exercise> exercises_a = userService.getAvailableExercises((int)user.id)
        
        when: "the user does not know how to solve an exercise, and ask for help and perform ok the attempt"
        assert exercises_a[0] != null
        Attempt attempt = userGamificationService.createEmptyAttempt((int)user.id,
                                                                      (int)exercises_a[0].id)
        assert attempt != null
        Help help = attemptService.getHelp(attempt.id)
        assert help.helpMessage != null

        userGamificationService.performAttempt((int)user.id,
                                                attempt.id,
                                                "Una respuesta")

        then: "the attempt points are reduced"
        assert attempt.points < attempt.exercise.points
    }



    // Historia de usuario: 5
    void "Retry Partially Completed Exercises"() {
        given: "there is a user at beginner level"
        User user = userService.createUser("Alejandro","Pena","ale@gmail.com")
        assert user != null
        attemptService.helpService = helpServiceMock
        helpServiceMock.getHelpMessage(_) >> "Una ayuda"
        attemptService.exerciseValidator = exerciseValidatorMock
        userGamificationService.attemptService = attemptService
        exerciseValidatorMock.validateAnswer(_,_) >> true
        
        List<Exercise> exercises_a = userService.getAvailableExercises((int)user.id)
        and: "the user does not know how to solve an exercise, and ask for help and perform ok the attempt"
        assert exercises_a[0] != null
        Attempt attempt = userGamificationService.createEmptyAttempt((int)user.id,
                                                                      (int)exercises_a[0].id)
        assert attempt != null
        Help help = attemptService.getHelp(attempt.id)
        assert help.helpMessage != null

        userGamificationService.performAttempt((int)user.id,
                                                attempt.id,
                                                "Una respuesta")

        assert attempt.points < attempt.exercise.points
        
        when: "the user retry the exercise without help"
        Attempt attempt2 = userGamificationService.createEmptyAttempt((int)user.id,
                                                                      (int)exercises_a[0].id)
        // Se resetean las ayudas al reintentar
        assert attempt2.helps.size() == 0
        assert attempt2 != null
        userGamificationService.performAttempt((int)user.id,
                                                attempt2.id,
                                                "Una respuesta")
        then: "the attempt points are not reduced"
        assert attempt2.points == attempt2.exercise.points
        
    }


}
