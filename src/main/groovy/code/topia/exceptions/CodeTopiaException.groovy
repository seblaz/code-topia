package code.topia

import groovy.transform.InheritConstructors

@InheritConstructors
class CodeTopiaException extends RuntimeException {
}


@InheritConstructors
class AttemptAlreadyCompleteException extends CodeTopiaException {
}

@InheritConstructors
class LevelNotCompleteException extends CodeTopiaException {
}

@InheritConstructors
class MaxHelpException extends CodeTopiaException {
}

@InheritConstructors
class UserAlreadyExistException extends CodeTopiaException {
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

