package code.topia

class Help {

    String helpMessage
    Attempt attempt

    static belongsTo = [attempt: Attempt]

    static constraints = {
        helpMessage nullable: false
        attempt column: 'attempt_id', insertable: false, updateable: false
    }

    String getHelpMessage(HelpService helpService) {
        assert helpService != null
        this.setHelpMessage(helpService.getHelpMessage(this.attempt.exercise))
        return this.helpMessage
    }
    
}
