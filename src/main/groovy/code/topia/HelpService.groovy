package code.topia



import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.Method.POST
import org.slf4j.LoggerFactory
import grails.util.Environment

class HelpService {
    def logger = LoggerFactory.getLogger(getClass())
    def grailsApplication

    def obtenerRespuesta(String consulta) {
        String apiUrl = grailsApplication.config.grails.profiles[Environment.current.getName()]['api-csm-gpt'].url
        String apiKey = grailsApplication.config.grails.profiles[Environment.current.getName()]['api-csm-gpt'].apiKey
        def http = new HTTPBuilder(apiUrl)
        logger.info("[HelpService] apiurl: ${apiUrl}")
        logger.info("[HelpService] apiKey: ${apiKey}")
        http.request(POST, ContentType.JSON) { req ->
            headers['Authorization'] = "Bearer ${apiKey}"
            body = [prompt: consulta, max_tokens: consulta.size()+10, n: 1, stop: null, temperature: 0.2]
            response.success = { resp, json ->
                logger.info("Tuvimos respuesta!")
                logger.info("Respuesta generada: ${json}")
                def respuestaGenerada = json.choices[0].text.strip()
                return respuestaGenerada
            }
            response.failure = { resp ->
                return null
            }
        }
    }

    String getHelpMessage(Exercise exercise) {
        String consulta = "Dado el enunciado\n" + exercise.statement +\
                          "\n" + "Dar una pista de resolución en 10 palabras, sin resolverlo"
        logger.info("[HelpService] Consulta: ${consulta}")
        String respuesta = obtenerRespuesta(consulta)
        logger.info("[HelpService] Respuesta: ${respuesta}")
        return respuesta
    }
}
