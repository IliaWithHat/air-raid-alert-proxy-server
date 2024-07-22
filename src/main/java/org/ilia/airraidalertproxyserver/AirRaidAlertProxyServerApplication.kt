package org.ilia.airraidalertproxyserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AirRaidAlertProxyServerApplication

fun main(args: Array<String>) {
    runApplication<AirRaidAlertProxyServerApplication>(*args)
}
