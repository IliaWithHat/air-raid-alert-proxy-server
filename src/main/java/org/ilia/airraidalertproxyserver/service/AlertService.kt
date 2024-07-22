package org.ilia.airraidalertproxyserver.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.ilia.airraidalertproxyserver.enitity.Alert;
import org.ilia.airraidalertproxyserver.exception.AlertNotFoundException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static lombok.AccessLevel.PRIVATE;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AlertService {

    RestClient restClient;
    List<String> regions = List.of(
            "Автономна Республіка Крим", "Волинська область", "Вінницька область",
            "Дніпропетровська область", "Донецька область", "Житомирська область",
            "Закарпатська область", "Запорізька область", "Івано-Франківська область",
            "м. Київ", "Київська область", "Кіровоградська область", "Луганська область",
            "Львівська область", "Миколаївська область", "Одеська область", "Полтавська область",
            "Рівненська область", "м. Севастополь", "Сумська область", "Тернопільська область",
            "Харківська область", "Херсонська область", "Хмельницька область", "Черкаська область",
            "Чернівецька область", "Чернігівська область"
    );

    @NonFinal
    List<Alert> alerts;

    public List<Alert> getAlerts() {
        return alerts;
    }

    public Alert getAlertById(int id) {
        return alerts.stream()
                .filter(alert -> alert.getRegionId() == id)
                .findFirst()
                .orElseThrow(() -> new AlertNotFoundException("Alert not found with id: " + id));
    }

    @Scheduled(fixedRate = 8, timeUnit = SECONDS)
    public void updateAlerts() {
        String alertsString = fetchAlertsString();
        log.debug("Alerts string: {}", alertsString);

        List<Alert> updatedAlerts = new ArrayList<>();
        for (int i = 0; i < alertsString.length(); i++) {
            Alert alert = new Alert(i + 1, regions.get(i), alertsString.charAt(i) == 'A');
            log.debug("Alert: {}", alert);
            updatedAlerts.add(alert);
        }

        alerts = Collections.unmodifiableList(updatedAlerts);
    }

    private String fetchAlertsString() {
        return restClient.get()
                .uri("/v1/iot/active_air_raid_alerts_by_oblast.json")
                .retrieve()
                .body(String.class)
                .replace("\"", "");
    }
}
