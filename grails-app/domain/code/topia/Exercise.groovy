package code.topia

class Exercise {

    String  statement
    String  anwser
    int  points
    Boolean approved

    static belongsTo = [level: Level]

    static constraints = {
        statement nullable: false
        anwser    nullable: true
        points    nullable: false, min: 1
        approved  nullable: true
    }
}
