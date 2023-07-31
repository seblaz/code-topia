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
class UserNotExistException extends CodeTopiaException {
}

@InheritConstructors
class AttemptNotBelongToUserException extends CodeTopiaException {
}





