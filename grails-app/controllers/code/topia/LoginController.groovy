package code.topia

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



class LoginController {

    def userService

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
       //render "WIP: login ok - ${params.email}"
       //render(view: '/home')
       //TODO: Buscar el usuario si introdujo el email
       if (params?.email) {
        session.user_logged_id = 1
        redirect(controller: 'home', action: 'index')
       } else {
            render "<h1>WIP: create user</h1>"
       }
    }

    def registerUser() {
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
                flash.createError = "error creando usuario"
                flash.errors.rejectValue("firstName", e.message)
                render(view: "register")
                return
            }
        } else {
            render "<h1>Hubo algun error ${p.errors}"
        }
    }
}
