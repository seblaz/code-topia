package code.topia

class HomeController {

    def index() {
        println(session.user_logged_id)
        if (session?.user_logged_id && session.user_logged_id > 0) {
            // Logueado
            //TODO:
            
        } else {
            // No esta logueado
            redirect(controller: 'login', action: 'index')
        }
     }
}
