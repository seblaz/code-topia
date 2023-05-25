package code.topia

abstract class Level {
    String  name
    int     points
    Exercise exercises

    static hasMany = [exercises: Exercise]

    static constraints = {
        name    nullable: false
        points  nullable: false, min: 1
    }
}

class EntryLevel extends Level{

    static constraints = {
    }
}

class AdvanceLevel extends Level{

    static constraints = {
    }
}
