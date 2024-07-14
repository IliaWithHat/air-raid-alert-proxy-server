package org.ilia.airraidalertproxyserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.ilia.airraidalertproxyserver.enitity.Alert;
import org.ilia.airraidalertproxyserver.service.AlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AlertController {

    AlertService alertService;

    @GetMapping
    public ResponseEntity<List<Alert>> getAlerts() {
        return ResponseEntity.ok(alertService.getAlerts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alert> getAlert(@PathVariable Integer id) {
        return ResponseEntity.ok(alertService.getAlertById(id));
    }
}
