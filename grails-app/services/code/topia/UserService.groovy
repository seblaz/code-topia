package code.topia

import grails.gorm.transactions.Transactional

@Transactional
class UserService {
    def userGamificationService

    def createUser(String firstName, String lastnName, String email) {
        User user = new User(firstName, lastnName, email)
        Level beginnerLevel = Level.findByName("Beginner Level")
        UserGamification usGm = new UserGamification(user,beginnerLevel)
        
        user.save(failOnError: true)
        return user
    }

    def performAttempt(int userId, int exerciseAttemptId, String answer) {
        User user = User.get(userId)
        Attempt attempt = Attempt.get(exerciseAttemptId)
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
