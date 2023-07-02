package code.topia

import grails.gorm.transactions.Transactional

@Transactional
class DataSeed {

    def init = { servletContext ->
        loadSeed()
    }

    void loadSeed() {
        println("Inicializando semilla..")

        //Level lvl = new Level()
        //lvl.save(failOnError: true)

    }
}
