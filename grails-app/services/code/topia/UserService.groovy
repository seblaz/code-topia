package code.topia

import grails.gorm.transactions.Transactional

@Transactional
class UserService {

    def createUser(String firstName, String lastnName, String email) {
        def beginnerLevel = Level.findByName("Beginner Level")
        UserGamification usGm = new UserGamification(beginnerLevel)
        User user = new User(firstName, lastnName, email, usGm)
        user.save(failOnError: true)
    }

    def getUser(String email) {

    }
}
