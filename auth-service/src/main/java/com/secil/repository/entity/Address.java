package com.secil.repository.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Address extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String street;
    private String neighbourhood;
    private String district;
    private String province;
    private String country;
    private Integer buildingNo;
    private Integer apartmentNumber;
    private Integer postCode;


}
