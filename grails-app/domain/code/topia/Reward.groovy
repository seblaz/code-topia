package code.topia

abstract class Reward {
    String  name
    String  content
    static constraints = {
        name    nullable: false
        content nullable: false
    }
}


class BeginnerCertificate extends Reward {
    static final String BEGINNER_NAME = "Beginner Certificate"
    static final String BEGINNER_CONTENT = "Beginner content ..."

    BeginnerCertificate() {
        this.name       = BEGINNER_NAME
        this.content    = BEGINNER_CONTENT
    }
}


class AdvancedCertificate extends Reward {
    static final String ADVANCED_NAME = "Advanced Certificate"
    static final String ADVANCED_CONTENT = "Advanced content ..."

    AdvancedCertificate() {
        this.name       = ADVANCED_NAME
        this.content    = ADVANCED_CONTENT
    }
} 






