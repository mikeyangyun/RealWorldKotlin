package com.tw.yys.backendrealworld

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class BackendRealWorldApplication

fun main(args: Array<String>) {
    runApplication<BackendRealWorldApplication>(*args)
}
