package issue

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
@Configuration
class MmaApplication {
    @Bean
    fun docket(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
    }
}

@RestController
class MyController {
    @GetMapping("/correct")
    fun correct(
        @RequestParam(required = false) param: Int?
    ): String {
        return "You can execute me without request param from both swagger and curl"
    }

    @GetMapping("/wrong")
    fun wrong(
        @RequestParam param: Int?
    ): String {
        return "You can execute me without request param via curl only"
    }
}

fun main(args: Array<String>) {
    runApplication<MmaApplication>(*args)
}
