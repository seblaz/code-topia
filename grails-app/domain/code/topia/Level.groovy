package code.topia


enum LevelType {
    BEGINNER {
        String getName() {
            return "Beginner"
        }

        int getLevelTypePoints() {
            return 5
        }

        List<Exercise> getExercises() {
            String STATEMENT_1  = "Escribe un programa en C que imprima '¡Hola, mundo!' en la pantalla"
            String STATEMENT_2  = "Escribe un programa en C que le pida al usuario ingresar dos números enteros e imprima la suma de esos dos números."
            String STATEMENT_3  = "Escribe un programa en C que verifique si un número ingresado por el usuario es par o impar."
            String STATEMENT_4  = "Escribe un programa en C que calcule el factorial de un número ingresado por el usuario."
            String STATEMENT_5  = "Escribe un programa en C que encuentre el máximo entre tres números ingresados por el usuario."
            String TITLE_1  = "Hola Mundo"
            String TITLE_2  = "Suma"
            String TITLE_3  = "Numero Par"
            String TITLE_4  = "Calculo Factorial"
            String TITLE_5  = "Funcion Maximo"
            Exercise ex1  = new Exercise(TITLE_1, STATEMENT_1, 1)
            Exercise ex2  = new Exercise(TITLE_2, STATEMENT_2, 2)
            Exercise ex3  = new Exercise(TITLE_3, STATEMENT_3, 3)
            Exercise ex4  = new Exercise(TITLE_4, STATEMENT_4, 4)
            Exercise ex5  = new Exercise(TITLE_5, STATEMENT_5, 4)
            return [ex1,ex2,ex3,ex4,ex5]
        }

        LevelType getNextLevel() {
            return LevelType.ADVANCED
        }
    },
    ADVANCED {
        String getName() {
            return "Advanced"
        }

        int getLevelTypePoints() {
            return 8
        }

        List<Exercise> getExercises() {
            String STATEMENT_1 = "Escribe un programa en C que lea una cadena de caracteres ingresada por el usuario y determine si es un palíndromo (se lee igual de izquierda a derecha que de derecha a izquierda)."
            String STATEMENT_2 = "Escribe un programa en C que genere los primeros n números de la serie de Fibonacci, donde n es ingresado por el usuario."
            String STATEMENT_3 = "Escribe un programa en C que lea un archivo de texto y cuente el número de palabras en él. Considera que las palabras están separadas por espacios en blanco."
            String STATEMENT_4 = "Escribe un programa en C que implemente una función recursiva para calcular el factorial de un número."
            String STATEMENT_5 = "Escribe un programa en C que implemente una función para ordenar un arreglo de números enteros en orden ascendente utilizando el algoritmo de ordenamiento de selección."
            String TITLE_1 = "Palindromo"
            String TITLE_2 = "Fibonacci"
            String TITLE_3 = "Procesamiento archivo texto"
            String TITLE_4 = "Recursividad"
            String TITLE_5 = "Algoritmo de ordenamiento"
            Exercise ex1  = new Exercise(TITLE_1, STATEMENT_1, 1)
            Exercise ex2  = new Exercise(TITLE_2, STATEMENT_2, 2)
            Exercise ex3  = new Exercise(TITLE_3, STATEMENT_3, 4)
            Exercise ex4  = new Exercise(TITLE_4, STATEMENT_4, 4)
            Exercise ex5  = new Exercise(TITLE_5, STATEMENT_5, 4)
            return [ex1,ex2,ex3,ex4,ex5]
        }

        LevelType getNextLevel() {
            return null
        }
    }

    public String getName() {
        return null
    }
    public int getLevelTypePoints() {
        return 0
    }
    public List<Exercise> getExercises() {
        return null
    }
    public LevelType getNextLevel() {
        return null
    }
}








class Level {
    static final int MIN_LVL_POINTS = 5
    static final int MIN_EXERCISES  = 5
    static final int MAX_EXERCISES  = 15

    LevelType           type
    String              name
    int                 points
    List<Exercise>      exercises
    User                user
    int                 userPoints
    UserGamification    userGamification
    
    static hasMany = [exercises: Exercise]
    static belongsTo = [userGamification: UserGamification]

    static constraints = {
        name                nullable: false, unique: true
        points              min: MIN_LVL_POINTS
        exercises           size: MIN_EXERCISES..MAX_EXERCISES
    }

    private static int calculateExTotalPoints(List<Exercise> exercises) {
        int totalExPoints = 0

        for (Exercise exercise : exercises) {
            totalExPoints += exercise.points
        }

        return totalExPoints
    }

    Level() {
        this.type = LevelType.BEGINNER
        this.name = this.type.getName()
        this.exercises = this.type.getExercises()
        this.points = this.type.getLevelTypePoints()
        assert calculateExTotalPoints(this.exercises) >= this.points
        this.exercises.each { exercise ->
            exercise.level = this
        }
    }

    void setUser(User user) {
        this.user = user
    }

    String getName() {
        return this.name
    }

    List<Exercise> getExercises() {
        return this.exercises
    }

    int getPoints() {
        return this.points
    }

    int getUserPoints() {
        return this.userPoints
    }

    LevelType getLevelType() {
        return this.type
    }

    void acumulateUserPoints(int points) {
        if (points <= 0 ){
            throw new LevelInvalidUserPointsException()
        }
        this.userPoints += points
        if ( this.userPoints >= this.points && 
            this.type.getNextLevel() != null) {
            this.userPoints = this.userPoints - this.points
            this.type = this.type.getNextLevel()
            this.points = this.type.getLevelTypePoints()
            this.exercises = this.type.getExercises()
        }
    }


    ////////////////////////////////////////////////
    boolean isLevelComplete() {
        return ( this.userPoints >= this.points && 
                 this.type.getNextLevel() != null)
    }

    LevelType getNextLevel() {
        //TODO: solo si esta completo el nivel
        return this.type.getNextLevel()
    }

    int getLevelTypePoints() {
        return this.type.getLevelTypePoints()
    }

    List<Exercise> getTypeExercises() {
        return this.type.getExercises()
    }

}
