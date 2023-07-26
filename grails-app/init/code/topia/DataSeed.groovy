package code.topia

import grails.gorm.transactions.Transactional

@Transactional
class DataSeed {

    def init = { servletContext ->
        loadSeed()
    }

    void loadSeed() {
        println("Inicializando semilla..")

        User user = new User("admin", "admin", "admin@example.com")
        Level level = new Level()
        UserGamification userGamification = user.initGamification(level)
        user.save(flush: true, failOnError: true)
        level.save(flush: true, failOnError: true)
        userGamification.save(flush: true, failOnError: true)
    }
}
