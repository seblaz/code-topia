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
        session.user_logged_id = 0
        render(view: 'index')
    }

    def loginUser() {
        if (params?.email) {
            User user = User.findByEmail(params.email)
            if (user) {
                session.user_logged_id = user.id
                redirect(controller: 'home', action: 'index')
                return
            }
        }
        render "<h1>WIP: create user</h1>"
    }

    def registerUser() {
        logger.info("estamos registrando un usuario")        
        render(view: 'register')
    }

    def createUser(CreateUserParam p) {
    
        if (!p.validate()) {
            flash.createError = "Hubo algún error en los datos proporcionados"
            println(p)
            render(view: "register", model: [createUserParam: p])
            return
        }

        if (!p.hasErrors()) {
            try {
                User user = userService.createUser(p.firstName, p.lastName, p.email)
                session.user_logged_id = user.id
                redirect(controller: 'home', action: 'index')
            }  catch (Exception e) {
                println("tuvimos error al crear usuario")
                println(e)
                flash.createError = "error creando usuario"
                render(view: "register")
                return
            }
        } else {
            render "<h1>Hubo algun error ${p.errors}"
        }
    }
}
