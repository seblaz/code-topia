package code.topia
import org.slf4j.LoggerFactory

class HomeController {

    def userService
    def logger = LoggerFactory.getLogger(getClass())

    def index() {
        logger.info("[HomeController] index invocado")
        if (session?.user_logged_id && session.user_logged_id > 0) {
            // Logueado
            try {
                logger.info("[HomeController] buscamos el usuario")
                User user = userService.getUserById(session.user_logged_id)
                logger.info("[HomeController] usuario recuperado: ${user}")
                List<Exercise> exerciseList = userService.getUserExercises(user)
                logger.info("[HomeController] lista ejercicios recuperada: ${exerciseList}")
                render(view: "index", model: [user: user, exerciseList: exerciseList])
                return
            } catch (UserNotExistException e) {
                // no deberiamos entrar aca
                logger.error("[HomeController] Usuario no existe; error: ${e}")
                redirect(controller: 'user', action: 'index')
                return
            }
        }
        // No esta logueado o no existe
        logger.info("[HomeController] no esta logueado")
        redirect(controller: 'user', action: 'index')
     }
}
