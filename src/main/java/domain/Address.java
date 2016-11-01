package domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;


@Component
@Scope("prototype")
@Entity
@NamedQueries({
        @NamedQuery(name = "Address.findByCityName", query = "SELECT a from " +
                "Address a WHERE a.city = :city")
})
public class Address implements Serializable {
    @Id
    @TableGenerator(
            name = "addressGen",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            pkColumnValue = "ADDRESS_ID",
            valueColumnName = "GEN_VALUE",
            initialValue = 0,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "addressGen")
    private Long id;

    private String zipCode;
    private String city;
    private String strName;
    private String type;
    private String buildingNo;
    private String appNo;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Cust_ID")
    private Customer customer;

    public Address() {
    }

    public Address(String zipCode, String city, String strName, String type,
                   String buildingNo, String appNo, Customer customer) {
        this.zipCode = zipCode;
        this.city = city;
        this.strName = strName;
        this.type = type;
        this.buildingNo = buildingNo;
        this.appNo = appNo;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", strName='" + strName + '\'' +
                ", type='" + type + '\'' +
                ", buildingNo='" + buildingNo + '\'' +
                ", appNo='" + appNo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (zipCode != null ? !zipCode.equals(address.zipCode) : address.zipCode != null)
            return false;
        if (city != null ? !city.equals(address.city) : address.city != null)
            return false;
        if (strName != null ? !strName.equals(address.strName) : address.strName != null)
            return false;
        if (type != null ? !type.equals(address.type) : address.type != null)
            return false;
        if (buildingNo != null ? !buildingNo.equals(address.buildingNo) : address.buildingNo != null)
            return false;
        return appNo != null ? appNo.equals(address.appNo) : address.appNo == null;

    }

    @Override
    public int hashCode() {
        int result = zipCode != null ? zipCode.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (strName != null ? strName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (buildingNo != null ? buildingNo.hashCode() : 0);
        result = 31 * result + (appNo != null ? appNo.hashCode() : 0);
        return result;
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
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
