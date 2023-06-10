package code.topia
import org.slf4j.LoggerFactory

class PerformAttemptParam {
    int     exerciseAttemptId
    String  answer

    static constraints = {
        exerciseAttemptId   nullable: false, blank: false
        answer              nullable: false, blank: false
    }
}

class ExerciseController {

    def userService
    def logger = LoggerFactory.getLogger(getClass())

    def index() { 
        if (session?.user_logged_id && session.user_logged_id > 0) {
            // Logueado
            User user = User.get(session.user_logged_id)
            Attempt attempt = userService.getAttempt(user,params.exerciseAttemptId.toInteger())
            if (user) {
                render(view: "index", model: [user: user, attempt: attempt])
                return
            }
        }
        // No esta logueado o no existe
        redirect(controller: 'user', action: 'index')
    }

    def performAttempt(PerformAttemptParam p) {
        try {
            logger.info("[ExerciseController] Realizamos un intento de ejercicio")
            userService.performAttempt((int)session.user_logged_id,
                                        p.exerciseAttemptId, p.answer)
        } catch (Exception e) {
            //FIXME: log o algo mejor ademas de un mensaje en pantalla.
            logger.error("[ExerciseController] Error al realizar intento de ejercicio; error: ${e}")
        }
        redirect(controller: 'home', action: 'index')
    }
}
