package code.topia

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

    def index() { 
        if (session?.user_logged_id && session.user_logged_id > 0) {
            // Logueado
            User user = User.get(session.user_logged_id)
            Attempt attempt = Attempt.get(params.exerciseAttemptId)
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
            userService.performAttempt((int)session.user_logged_id,
                                        p.exerciseAttemptId, p.answer)
        } catch (Exception e) {
            println(e)
        }
        redirect(controller: 'home', action: 'index')
    }
}
