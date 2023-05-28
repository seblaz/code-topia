package code.topia

class ExerciseController {

    def index() { 
        if (session?.user_logged_id && session.user_logged_id > 0) {
            // Logueado
            User user = User.get(session.user_logged_id)
            ExerciseAttempt attempt = ExerciseAttempt.get(params.exerciseAttemptId)
            if (user) {
                render(view: "index", model: [user: user, attempt: attempt])
                return
            }
        }
        // No esta logueado o no existe
        redirect(controller: 'login', action: 'index')
    }
}
