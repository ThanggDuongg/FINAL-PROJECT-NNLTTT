package Globals;

public class Globals {
    private static Integer GlobalCustomerId;
    private static Integer GlobalShipperId;
    private static Integer GlobalAdministratorId;

    public static Integer getGlobalCustomerId() {
        return GlobalCustomerId;
    }

    public static void setGlobalCustomerId(Integer globalCustomerId) {
        GlobalCustomerId = globalCustomerId;
    }

    public static Integer getGlobalShipperId() {
        return GlobalShipperId;
    }

    public static void setGlobalShipperId(Integer globalShipperId) {
        GlobalShipperId = globalShipperId;
    }

    public static Integer getGlobalAdministratorId() {
        return GlobalAdministratorId;
    }

    public static void setGlobalAdministratorId(Integer globalAdministratorId) {
        GlobalAdministratorId = globalAdministratorId;
    }
}
