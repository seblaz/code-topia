package code.topia
import org.slf4j.LoggerFactory

class CreateUserParam {
    String  firstName
    String  lastName
    String  email

    static constraints = {
        firstName nullable: false, blank: false
        lastName nullable: false, blank: false
        email nullable: false, blank: false, email: true
    }
}

class UserController {

    def userService
    def logger = LoggerFactory.getLogger(getClass())

    static allowedMethods = [
        'index': ['Get'],
        'registerUser': ['Get'],
        'loginUser': ['Post'],
        'createUser': ['Post']
    ]

    def index() {
        logger.info("[UserController] index invocado")
        session.user_logged_id = 0
        flash.mostrarAlerta = false
        render(view: 'index')
    }

    def loginUser() {
        logger.info("[UserController] loginUser invocado")
        if (params?.email) {
            try {
                User user = userService.getUserByEmail(params.email)
                session.user_logged_id = user.id
                redirect(controller: 'home', action: 'index')
                return
            } catch (UserNotExistException e) {
                logger.error("[UserController] Usuario no existe; error: ${e}")
                flash.error = "Usuario invalido o inexistente"
                flash.mostrarAlerta = true
                render(view: "index")
                return
            }
        }
        logger.error("[UserController] no informaron el mail")
        flash.error = "Se necesita un mail para loguearse"
        flash.mostrarAlerta = true
        render(view: "index")
        return
    }

    def registerUser() {
        logger.info("[UserController] registerUser invocado")
        flash.mostrarAlerta = false
        render(view: 'register', model: [createUserParam: new CreateUserParam()])
    }

    def createUser(CreateUserParam p) {
        logger.info("[UserController] createUser invocado")
        if (!p.validate()) {
            flash.createError = "Hubo algún error en los datos proporcionados"
            render(view: "register", model: [createUserParam: p])
            return
        }

        if (!p.hasErrors()) {
            try {
                User user = userService.createUser(p.firstName, p.lastName, p.email)
                session.user_logged_id = user.id
                logger.info("[UserController] Usuario creado: ${user}")
                redirect(controller: 'home', action: 'index')
            }  catch (Exception e) {
                logger.error("[UserController] Error al crear usuario; error: ${e}")
                flash.createError = "error creando usuario"
                render(view: "register")
                return
            }
        } else {
            render "<h1>Hubo algun error ${p.errors}"
        }
    }
}
