package code.topia

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        

        "/"(controller: 'user') {
            action = [GET: 'index']
        }
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
