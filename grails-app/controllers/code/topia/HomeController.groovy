package code.topia

class HomeController {

    def index() {
        println(session.user_logged_id)
        if (session?.user_logged_id && session.user_logged_id > 0) {
            // Logueado
            User user = User.get(session.user_logged_id)
            if (user) {
                render(view: "index", model: [user: user])
                return
            }
        }
        // No esta logueado o no existe
        redirect(controller: 'login', action: 'index')
     }
}
