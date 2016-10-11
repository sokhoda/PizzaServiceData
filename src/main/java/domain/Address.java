package domain;

/**
 * Created by s_okhoda on 11.10.2016.
 */
public class Address {
    private String name;
    private String type;
    private String buildingNo;
    private String appNo;

    public Address(String name, String type, String buildingNo, String appNo) {
        this.name = name;
        this.type = type;
        this.buildingNo = buildingNo;
        this.appNo = appNo;
    }

    @Override
    public String toString() {
        return "Address{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", buildingNo='" + buildingNo + '\'' +
                ", appNo='" + appNo + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getAppNo() {
        return appNo;
    }

    public void setAppNo(String appNo) {
        this.appNo = appNo;
    }
}
