package code.topia

import grails.gorm.transactions.Transactional

@Transactional
class UserService {
    def userGamificationService

    def createUser(String firstName, String lastnName, String email) {
        def beginnerLevel = Level.findByName("Beginner Level")
        Observer observer = new ExerciseAttemptObserver()
        UserGamification usGm = new UserGamification(beginnerLevel)
        usGm.addObserver(observer)
        User user = new User(firstName, lastnName, email, usGm)
        user.save(failOnError: true)
        observer.save(failOnError: true)
        return user
    }

    def performAttempt(int userId, int exerciseAttemptId, String answer) {
        User user = User.get(userId)
        ExerciseAttempt attempt = ExerciseAttempt.get(exerciseAttemptId)
        assert attempt != null
        assert user != null
        attempt.answer = answer
        // FIXME: Pedirle al validador del ejercicio que consulte la api de 
        // gpt-3 para hacer la validacion .. 
        // TODO: Ver luego si se consultan ayudas y decrementar puntaje
        // TODO: Ver si es reintento y acumular diferencia de puntos..
        attempt.aproved = true
        attempt.points = attempt.exercise.points
        userGamificationService.increasePoints(user,attempt.points)
        user.save(failOnError: true)
        attempt.save(failOnError: true)
    }

}
