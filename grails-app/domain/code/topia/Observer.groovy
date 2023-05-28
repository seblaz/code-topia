package code.topia

abstract class Observer {

    abstract void notifyObserver(User user, List<Exercise> exerciseList)
}

class ExerciseAttemptObserver extends Observer {
    void notifyObserver(User user, List<Exercise> exerciseList) {
        exerciseList.each { exercise ->
            ExerciseAttempt exAttmpt = new ExerciseAttempt(user,exercise)
            exAttmpt.save(failOnError: true)
        }
    }
}