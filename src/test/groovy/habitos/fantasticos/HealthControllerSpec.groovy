package habitos.fantasticos

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class HealthControllerSpec extends Specification implements ControllerUnitTest<HealthController> {

    void "test returns OK"() {
        when:"The index action is invoked"
        controller.index()

        then:"OK is returned"
        response.text == 'OK'
    }
}
