package code.topia

abstract class Level {
    static final int MIN_LVL_POINTS = 5
    static final int MIN_EXERCISES  = 5
    static final int MAX_EXERCISES  = 15
    String          name
    int             points
    List<Exercise>  exercises

    static hasMany = [exercises: Exercise]

    static constraints = {
        name            nullable: false, unique: true
        points          min: MIN_LVL_POINTS
        exercises       size: MIN_EXERCISES..MAX_EXERCISES
    }

    static int calculateExTotalPoints(List<Exercise> exercises) {
        int totalExPoints = 0

        for (Exercise exercise : exercises) {
            totalExPoints += exercise.points
        }

        return totalExPoints
    }

    Level getNextLevelClass() {
        return null
    }

    List<Exercise> getExercises() {
        return this.exercises
    }

}

class BeginnerLevel extends Level{
    private static final int MIN_REQ_POINTS = 5
    private static final String BEGINNER_NAME = "Beginner Level"
    private static final String STATEMENT_1  = "Escribe un programa en C que imprima '¡Hola, mundo!' en la pantalla"
    private static final String STATEMENT_2  = "Escribe un programa en C que le pida al usuario ingresar dos números enteros e imprima la suma de esos dos números."
    private static final String STATEMENT_3  = "Escribe un programa en C que verifique si un número ingresado por el usuario es par o impar."
    private static final String STATEMENT_4  = "Escribe un programa en C que calcule el factorial de un número ingresado por el usuario."
    private static final String STATEMENT_5  = "Escribe un programa en C que encuentre el máximo entre tres números ingresados por el usuario."
    private static final String TITLE_1  = "Hola Mundo"
    private static final String TITLE_2  = "Suma"
    private static final String TITLE_3  = "Numero Par"
    private static final String TITLE_4  = "Calculo Factorial"
    private static final String TITLE_5  = "Funcion Maximo"

    static constraints = {
    }

    BeginnerLevel() {
        this.name = BEGINNER_NAME
        Exercise ex1  = new Exercise(TITLE_1, STATEMENT_1, 1)
        Exercise ex2  = new Exercise(TITLE_2, STATEMENT_2, 2)
        Exercise ex3  = new Exercise(TITLE_3, STATEMENT_3, 3)
        Exercise ex4  = new Exercise(TITLE_4, STATEMENT_4, 4)
        Exercise ex5  = new Exercise(TITLE_5, STATEMENT_5, 4)
        this.exercises = [ex1,ex2,ex3,ex4,ex5]
        this.exercises.each { exercise ->
            exercise.level = this
        }
        this.points = MIN_REQ_POINTS
    }

    BeginnerLevel(List<Exercise> exercises, int points) {
        assert exercises != null
        assert points != null
        assert points >= MIN_REQ_POINTS

        // Chequeamos que con los ejercicios pueda completarse
        // los puntos del nivel.
        assert this.calculateExTotalPoints(exercises) >= points
        
        exercises.each { exercise ->
            exercise.level = this
        }

        this.exercises  = exercises
        this.points     = points
        this.name       = BEGINNER_NAME
        
    }

    Level getNextLevelClass(){
        return new AdvancedLevel()
    }

}

class AdvancedLevel extends Level{
    private static final int MIN_REQ_POINTS = 8
    private static final String ADVANCED_NAME = "Advanced Level"
    private static final String STATEMENT_1 = "Escribe un programa en C que lea una cadena de caracteres ingresada por el usuario y determine si es un palíndromo (se lee igual de izquierda a derecha que de derecha a izquierda)."
    private static final String STATEMENT_2 = "Escribe un programa en C que genere los primeros n números de la serie de Fibonacci, donde n es ingresado por el usuario."
    private static final String STATEMENT_3 = "Escribe un programa en C que lea un archivo de texto y cuente el número de palabras en él. Considera que las palabras están separadas por espacios en blanco."
    private static final String STATEMENT_4 = "Escribe un programa en C que implemente una función recursiva para calcular el factorial de un número."
    private static final String STATEMENT_5 = "Escribe un programa en C que implemente una función para ordenar un arreglo de números enteros en orden ascendente utilizando el algoritmo de ordenamiento de selección."
    private static final String TITLE_1 = "Palindromo"
    private static final String TITLE_2 = "Fibonacci"
    private static final String TITLE_3 = "Procesamiento archivo texto"
    private static final String TITLE_4 = "Recursividad"
    private static final String TITLE_5 = "Algoritmo de ordenamiento"

    AdvancedLevel() {
        this.name = ADVANCED_NAME
        Exercise ex1  = new Exercise(TITLE_1, STATEMENT_1, 1)
        Exercise ex2  = new Exercise(TITLE_2, STATEMENT_2, 2)
        Exercise ex3  = new Exercise(TITLE_3, STATEMENT_3, 4)
        Exercise ex4  = new Exercise(TITLE_4, STATEMENT_4, 4)
        Exercise ex5  = new Exercise(TITLE_5, STATEMENT_5, 4)
        this.exercises = [ex1,ex2,ex3,ex4,ex5]
        this.exercises.each { exercise ->
            exercise.level = this
        }
        this.points = MIN_REQ_POINTS
    }

    AdvancedLevel(List<Exercise> exercises, int points) {
        assert exercises != null
        assert points != null
        assert points >= MIN_REQ_POINTS

        // Chequeamos que con los ejercicios pueda completarse
        // los puntos del nivel.
        assert this.calculateExTotalPoints(exercises) >= points
        
        exercises.each { exercise ->
            exercise.level = this
        }

        this.exercises  = exercises
        this.points     = points
        this.name       = ADVANCED_NAME
        
    }
}
