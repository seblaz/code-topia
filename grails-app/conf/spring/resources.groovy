
import code.topia.*

beans = {
    helpService(HelpService)
    helpService(HelpService) {
        grailsApplication = ref('grailsApplication')
    }
    exerciseValidator(ExerciseValidator)
    exerciseValidator(ExerciseValidator) {
        grailsApplication = ref('grailsApplication')
    }
}
