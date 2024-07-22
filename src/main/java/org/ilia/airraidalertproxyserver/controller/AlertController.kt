package org.ilia.airraidalertproxyserver.controller

import org.ilia.airraidalertproxyserver.entity.Alert
import org.ilia.airraidalertproxyserver.service.AlertService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/alerts")
class AlertController(private val alertService: AlertService) {

    @GetMapping
    fun find(): ResponseEntity<List<Alert>> =
        ResponseEntity.ok(alertService.findAlerts())

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): ResponseEntity<Alert> =
        ResponseEntity.ok(alertService.findAlertById(id))
}