package code.topia

import grails.gorm.transactions.Transactional

@Transactional
class DataSeed {

    def init = { servletContext ->
        loadSeed()
    }

    void loadSeed() {
        println("Inicializando semilla..")
        // Ej 1
        def statement = "Escribe un programa en C que imprima '¡Hola, mundo!' en la pantalla"
        def points = 1
        def ex1 = new Exercise(statement,points)
        ex1.save()

        // Ej 2
        statement = "Escribe un programa en C que le pida al usuario ingresar dos números enteros e imprima la suma de esos dos números."
        points = 2
        def ex2 = new Exercise(statement,points)
        ex2.save()

        // Ej 3
        statement = "Escribe un programa en C que verifique si un número ingresado por el usuario es par o impar."
        points = 1
        def ex3 = new Exercise(statement,points)
        ex3.save()

        // Ej 4
        statement = "Escribe un programa en C que calcule el factorial de un número ingresado por el usuario."
        points = 3
        def ex4 = new Exercise(statement,points)
        ex4.save()

        // Ej 5
        statement = "Escribe un programa en C que encuentre el máximo entre tres números ingresados por el usuario."
        points = 2
        def ex5 = new Exercise(statement,points)
        ex5.save()

        def beginnerLevel = new BeginnerLevel([ex1,ex2,ex3,ex4,ex5],5)
        beginnerLevel.save()

    }
}
