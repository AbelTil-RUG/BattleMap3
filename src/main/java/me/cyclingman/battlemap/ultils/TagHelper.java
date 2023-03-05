package me.cyclingman.battlemap.ultils;

public class TagHelper {

    public static final String CONTROL_POINT = "bm_control_point";
    public static final String ACTIVE = "bm_active";
    public static final String RADIUS = "bm_radius";
    public static final String NAME = "bm_name";

    public String setVariable(String baseTag, String variable) {
        return baseTag + "-" + variable;
    }

    public String getVariable(String fullTag) {
        return fullTag.split("-",2)[1];
    }
}
