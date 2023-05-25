import grails.gorm.transactions.Transactional

@Transactional
class DataSeed {

    def init = { servletContext ->
        loadSeed()
    }

    void loadSeed() {
        println("Inicializando semilla..")
    }
}
