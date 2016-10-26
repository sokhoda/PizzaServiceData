package domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;


@Component
@Scope("prototype")
@Embeddable @Access(AccessType.FIELD)
public class Address {
//    @TableGenerator(
//            strName = "addressGen",
//            table = "ID_GEN",
//            pkColumnName = "GEN_KEY",
//            pkColumnValue = "ADDRESS_ID",
//            valueColumnName = "GEN_VALUE",
//            initialValue = 0,
//            allocationSize = 1)
//    @GeneratedValue(strategy= GenerationType.TABLE, generator = "addressGen")
//    private Long id;

    private String zipCode;
    private String City;
    private String strName;
    private String type;
    private String buildingNo;
    private String appNo;

    public Address() {
    }

    public Address(String zipCode, String city, String strName, String type, String buildingNo, String appNo) {
        this.zipCode = zipCode;
        City = city;
        this.strName = strName;
        this.type = type;
        this.buildingNo = buildingNo;
        this.appNo = appNo;
    }

    @Override
    public String toString() {
        return "Address{" +
                "zipCode='" + zipCode + '\'' +
                ", City='" + City + '\'' +
                ", strName='" + strName + '\'' +
                ", type='" + type + '\'' +
                ", buildingNo='" + buildingNo + '\'' +
                ", appNo='" + appNo + '\'' +
                '}';
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }
}
