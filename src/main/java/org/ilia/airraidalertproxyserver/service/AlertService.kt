package org.ilia.airraidalertproxyserver.service

import org.ilia.airraidalertproxyserver.entity.Alert

interface AlertService {

    fun findAlerts(): List<Alert>

    fun findAlertById(id: Int): Alert
}