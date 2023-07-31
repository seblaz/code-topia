package code.topia
import org.slf4j.LoggerFactory

class PerformAttemptParam {
    static final long MIN_ID = 1
    long    attemptId
    String  answer

    static constraints = {
        attemptId   min: MIN_ID
        answer      nullable: false, blank: false
    }
}

class ExerciseController {

    def userService
    def exerciseService
    def attemptService
    def userGamificationService
    def logger = LoggerFactory.getLogger(getClass())

    User getUserLoggedIn() {
        if (session?.user_logged_id && session.user_logged_id > 0) {
            try {
                User user = userService.getUserById(session.user_logged_id)
                return user
            } catch (UserNotExistException e) {
                logger.error("[ExerciseController] Usuario no existe; error: ${e}")
            } catch (Exception e) {
                logger.error("[ExerciseController] error: ${e}")
            }
        }
        return null
    }

    def index() { 
        User user = getUserLoggedIn()
        logger.info("[ExerciseController] user: ${user}")
        if (user != null) {
            try {
                Attempt attempt = userGamificationService.createEmptyAttempt(user.id, params.exerciseId.toInteger())
                logger.info("[ExerciseController] attempt vacio: ${params} - ${attempt.id}")
                List<Exercise> exerciseList = userService.getUserExercises(user)
                render(view: "index", model: [user: user, attempt: attempt, exerciseList: exerciseList, abrirModal: "false"])
                return
            } catch (Exception e) {
                logger.error("[ExerciseController] error: ${e}")
                redirect(controller: 'user', action: 'index')
                return
            }
        }
        redirect(controller: 'user', action: 'index')
    }


    def performAttempt(PerformAttemptParam p) {
        logger.info("[ExerciseController] attempt param recibido: ${p.attemptId} - ${p.answer}")
        User user = getUserLoggedIn()
        logger.info("[ExerciseController] user: ${user}")
        if (user != null) {
            List<Exercise> exerciseList = userService.getUserExercises(user)
            Attempt attempt = attemptService.getAttempt(p.attemptId)
            if (!p.validate()) {
                flash.createError = "Hubo algún error en los datos proporcionados"
                render(view: "index", model: [user: user, attempt: attempt,
                                            exerciseList: exerciseList,
                                            abrirModal: "false",performAttemptParam:p])
                return
            }
            try {
                logger.info("[ExerciseController] Realizamos un intento de ejercicio")
                def attemptRes = userGamificationService.performAttempt((int)session.user_logged_id, p.attemptId, p.answer)
                render(view: "index", model: [user: user, attempt: attempt, exerciseList: exerciseList, abrirModal: "true", attemptRes: attemptRes])
                return
            } catch (Exception e) {
                //FIXME: log o algo mejor ademas de un mensaje en pantalla.
                logger.error("[ExerciseController] Error al realizar intento de ejercicio; error: ${e}")
            }
        }
        redirect(controller: 'home', action: 'index')
    }


    def getHelp() {
        logger.info("[ExerciseController] attempt param recibido: ${params.attemptId}")
        User user = getUserLoggedIn()
        logger.info("[ExerciseController] user: ${user}")
        if (user != null) {
            try {
                logger.info("[ExerciseController] Solicitud de ayuda")
                Attempt attempt = attemptService.getAttempt(params.attemptId.toInteger())
                attemptService.getHelp(params.attemptId.toInteger())
                List<Exercise> exerciseList = userService.getUserExercises(user)
                render(view: "index", model: [user: user, attempt: attempt, exerciseList: exerciseList])
                return
            } catch (Exception e) {
                //FIXME: log o algo mejor ademas de un mensaje en pantalla.
                logger.error("[ExerciseController] Error al realizar la solicitud; error: ${e}")
            }
        }
        redirect(controller: 'home', action: 'index')
    }
}
