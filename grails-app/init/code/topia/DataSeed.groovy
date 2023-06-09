package code.topia

import grails.gorm.transactions.Transactional

@Transactional
class DataSeed {

    static final String STATEMENT_1  = "Escribe un programa en C que imprima '¡Hola, mundo!' en la pantalla"
    static final String STATEMENT_2  = "Escribe un programa en C que le pida al usuario ingresar dos números enteros e imprima la suma de esos dos números."
    static final String STATEMENT_3  = "Escribe un programa en C que verifique si un número ingresado por el usuario es par o impar."
    static final String STATEMENT_4  = "Escribe un programa en C que calcule el factorial de un número ingresado por el usuario."
    static final String STATEMENT_5  = "Escribe un programa en C que encuentre el máximo entre tres números ingresados por el usuario."
    static final String STATEMENT_6  = "Escribe un programa en C que lea una cadena de caracteres ingresada por el usuario y determine si es un palíndromo (se lee igual de izquierda a derecha que de derecha a izquierda)."
    static final String STATEMENT_7  = "Escribe un programa en C que genere los primeros n números de la serie de Fibonacci, donde n es ingresado por el usuario."
    static final String STATEMENT_8  = "Escribe un programa en C que lea un archivo de texto y cuente el número de palabras en él. Considera que las palabras están separadas por espacios en blanco."
    static final String STATEMENT_9  = "Escribe un programa en C que implemente una función recursiva para calcular el factorial de un número."
    static final String STATEMENT_10 = "Escribe un programa en C que implemente una función para ordenar un arreglo de números enteros en orden ascendente utilizando el algoritmo de ordenamiento de selección."

    static final String TITLE_1  = "Hola Mundo"
    static final String TITLE_2  = "Suma"
    static final String TITLE_3  = "Numero Par"
    static final String TITLE_4  = "Calculo Factorial"
    static final String TITLE_5  = "Funcion Maximo"
    static final String TITLE_6  = "Palindromo"
    static final String TITLE_7  = "Fibonacci"
    static final String TITLE_8  = "Procesamiento archivo texto"
    static final String TITLE_9  = "Recursividad"
    static final String TITLE_10 = "Algoritmo de ordenamiento"

    def init = { servletContext ->
        loadSeed()
    }

    void loadSeed() {
        println("Inicializando semilla..")

        def ex1  = new Exercise(TITLE_1, STATEMENT_1, 1)
        def ex2  = new Exercise(TITLE_2, STATEMENT_2, 2)
        def ex3  = new Exercise(TITLE_3, STATEMENT_3, 3)
        def ex4  = new Exercise(TITLE_4, STATEMENT_4, 4)
        def ex5  = new Exercise(TITLE_5, STATEMENT_5, 4)
        def ex6  = new Exercise(TITLE_6, STATEMENT_6, 1)
        def ex7  = new Exercise(TITLE_7, STATEMENT_7, 2)
        def ex8  = new Exercise(TITLE_8, STATEMENT_8, 4)
        def ex9  = new Exercise(TITLE_9, STATEMENT_9, 4)
        def ex10 = new Exercise(TITLE_10, STATEMENT_10, 4)
        ex1.save(failOnError: true)
        ex2.save(failOnError: true)
        ex3.save(failOnError: true)
        ex4.save(failOnError: true)
        ex5.save(failOnError: true)
        ex6.save(failOnError: true)
        ex7.save(failOnError: true)
        ex8.save(failOnError: true)
        ex9.save(failOnError: true)
        ex10.save(failOnError: true)

        def beginnerLevel = new BeginnerLevel([ex1,ex2,ex3,ex4,ex5],5)
        beginnerLevel.save(failOnError: true)

        def advancedLevel = new AdvancedLevel([ex6,ex7,ex8,ex9,ex10],8)
        advancedLevel.save(failOnError: true)

    }
}
