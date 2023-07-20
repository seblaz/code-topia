package code.topia

import groovy.transform.InheritConstructors

@InheritConstructors
class CodeTopiaException extends RuntimeException {
}


@InheritConstructors
class AttemptAlreadyApprovedException extends CodeTopiaException {
}


@InheritConstructors
class AttemptWithInvalidExerciseLevelException extends CodeTopiaException {
}

@InheritConstructors
class UserGamificationInvalidLevelException extends CodeTopiaException {
}

@InheritConstructors
class UserGamificationInvalidPointsException extends CodeTopiaException {
}

@InheritConstructors
class LevelInvalidUserPointsException extends CodeTopiaException {
}

@InheritConstructors
class UserNotExistException extends CodeTopiaException {
}

@InheritConstructors
class ExerciseNotExistException extends CodeTopiaException {
}

