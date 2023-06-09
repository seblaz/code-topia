package code.topia

class HomeController {

    def userService

    def index() {
        if (session?.user_logged_id && session.user_logged_id > 0) {
            // Logueado
            User user = User.get(session.user_logged_id)
            if (user) {
                List<Attempt> exerciseList = userService.getAllExercises(user)
                render(view: "index", model: [user: user, exerciseList: exerciseList])
                return
            }
        }
        // No esta logueado o no existe
        redirect(controller: 'user', action: 'index')
     }
}
