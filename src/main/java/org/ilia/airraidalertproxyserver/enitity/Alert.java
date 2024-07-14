package org.ilia.airraidalertproxyserver.enitity;

import lombok.Value;

@Value
public class Alert {

    int regionId;
    String regionName;
    boolean alert;
}
