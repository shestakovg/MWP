package core;

import java.util.UUID;

/**
 * Created by g.shestakov on 26.05.2015.
 */
public class AppSettings {


    private String serviceUrl;
    private String routeName;
    private String employeeName;
    private UUID   routeId;
    private UUID employeeID;

    public AppSettings(String serviceUrl, String routeName, String employeeName, UUID routeId, UUID employeeID) {
        this.serviceUrl = serviceUrl;
        this.routeName = routeName;
        this.employeeName = employeeName;
        this.routeId = routeId;
        this.employeeID = employeeID;
    }

    public AppSettings() {

    }
}
