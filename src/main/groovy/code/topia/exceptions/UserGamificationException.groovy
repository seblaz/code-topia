package code.topia

import groovy.transform.InheritConstructors

@InheritConstructors
class CodeTopiaException extends RuntimeException {
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