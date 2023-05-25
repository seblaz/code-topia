package code.topia

import grails.gorm.transactions.Transactional

@Transactional
class UserService {

    def createUser(String firstName, String lastnName, String email) {
        User user = new User(firstName, lastnName, email)
        user.save(failOnError: true)
    }

    def getUser(String email) {

    }
}
