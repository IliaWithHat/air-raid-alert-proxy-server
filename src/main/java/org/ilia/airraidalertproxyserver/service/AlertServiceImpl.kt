package org.ilia.airraidalertproxyserver.service

import org.ilia.airraidalertproxyserver.data.RegionData
import org.ilia.airraidalertproxyserver.entity.Alert
import org.ilia.airraidalertproxyserver.exception.AlertFetchException
import org.ilia.airraidalertproxyserver.exception.AlertNotFoundException
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import java.util.concurrent.TimeUnit.SECONDS

@Service
class AlertServiceImpl(private val restClient: RestClient) : AlertService {

    private var alerts: List<Alert> = emptyList()

    override fun findAlerts() = alerts

    override fun findAlertById(id: Int) =
        alerts.find { it.regionId == id } ?: throw AlertNotFoundException("Alert not found with id: $id")

    @Scheduled(fixedRate = 8, timeUnit = SECONDS)
    fun updateAlerts() {
        val alertsString = fetchAlerts()
        val updatedAlerts: MutableList<Alert> = mutableListOf()
        repeat(alertsString.length) { i ->
            updatedAlerts.add(Alert(i + 1, RegionData.regions[i], alertsString[i] == 'A'))
        }
        alerts = updatedAlerts
    }

    private fun fetchAlerts(): String {
        return restClient.get()
            .uri("/v1/iot/active_air_raid_alerts_by_oblast.json")
            .retrieve()
            .body(String::class.java)
            ?.replace("\"", "") ?: throw AlertFetchException("Failed to fetch alerts from server")
    }
}