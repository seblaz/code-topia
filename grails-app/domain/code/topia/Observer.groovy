package code.topia

abstract class Observer {

    abstract void notifyObserver(User user, List<Exercise> exerciseList)
}

class ExerciseAttemptObserver extends Observer {
    void notifyObserver(User user, List<Exercise> exerciseList) {
        exerciseList.each { exercise ->
            Attempt exAttmpt = new Attempt(user,exercise)
            exAttmpt.save(failOnError: true)
        }
    }
}