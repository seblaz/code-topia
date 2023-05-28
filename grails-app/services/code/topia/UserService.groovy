package code.topia

import grails.gorm.transactions.Transactional

@Transactional
class UserService {

    def createUser(String firstName, String lastnName, String email) {
        def beginnerLevel = Level.findByName("Beginner Level")
        Observer observer = new ExerciseAttemptObserver()
        UserGamification usGm = new UserGamification(beginnerLevel, observer)
        User user = new User(firstName, lastnName, email, usGm)
        user.save(failOnError: true)
        observer.save(failOnError: true)
    }

    def getUser(String email) {

    }
}
