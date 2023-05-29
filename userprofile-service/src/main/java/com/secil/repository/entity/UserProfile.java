package com.secil.repository.entity;
import com.secil.repository.entity.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document
public class UserProfile extends Base implements Serializable {
    @Id
    private String userId;
    private Long authId;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String username;
    private String province;
    private String district;
    private String neighbourhood;
    private String street;
    private String country;
    private Integer buildingNo;
    private Integer apartmentNumber;
    private Integer postCode;
    private String avatar;
    private List<String> categoryId=new ArrayList<>();
    private List<String> recipeId=new ArrayList<>();
    @Builder.Default //bir property' ye başlangıç değeri atandığında kullanılır, new'lendiğinde kullanılmaz
    private EStatus status = EStatus.ACTIVE;
}
