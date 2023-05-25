package code.topia


class BootStrap {

    def init = { servletContext ->
        def dataSeed = new DataSeed()
        dataSeed.init(servletContext)
    }

    
    def destroy = {
    }
}
