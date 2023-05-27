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

        // Ej 6
        statement = "Escribe un programa en C que lea una cadena de caracteres ingresada por el usuario y determine si es un palíndromo (se lee igual de izquierda a derecha que de derecha a izquierda)."
        points = 2
        def ex6 = new Exercise(statement,points)
        ex6.save()

        // Ej 7
        statement = "Escribe un programa en C que genere los primeros n números de la serie de Fibonacci, donde n es ingresado por el usuario."
        points = 2
        def ex7 = new Exercise(statement,points)
        ex7.save()
        
        // Ej 8
        statement = "Escribe un programa en C que lea un archivo de texto y cuente el número de palabras en él. Considera que las palabras están separadas por espacios en blanco."
        points = 2
        def ex8 = new Exercise(statement,points)
        ex8.save()

        // Ej 9
        statement = "Escribe un programa en C que implemente una función recursiva para calcular el factorial de un número."
        points = 2
        def ex9 = new Exercise(statement,points)
        ex9.save()

        // Ej 10
        statement = "Escribe un programa en C que implemente una función para ordenar un arreglo de números enteros en orden ascendente utilizando el algoritmo de ordenamiento de selección."
        points = 2
        def ex10 = new Exercise(statement,points)
        ex10.save()

        def beginnerLevel = new BeginnerLevel([ex1,ex2,ex3,ex4,ex5],5)
        beginnerLevel.save()

        def advancedLevel = new AdvancedLevel([ex6,ex7,ex8,ex9,ex10],8)
        advancedLevel.save()

    }
}
