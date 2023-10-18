package com.project.indistraw

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDateTime
import java.util.*
import javax.annotation.PostConstruct

private val log = KotlinLogging.logger {}

@SpringBootApplication
class IndiStrawApplication {

	@PostConstruct
	fun applicationTimeZoneSetter() {
		val timeZone = TimeZone.getTimeZone("Asia/Seoul")
		TimeZone.setDefault(timeZone)

		log.info { "IndiStraw Application TimeZone was set: ${LocalDateTime.now()}" }
	}

}

fun main(args: Array<String>) {
	runApplication<IndiStrawApplication>(*args)
}
