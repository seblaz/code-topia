package code.topia

class Help {

    String message

    static belongsTo = [exercise: Exercise]

    static constraints = {
        message nullable: false
    }
}
