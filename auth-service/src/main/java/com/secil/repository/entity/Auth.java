package com.secil.repository.entity;
import com.secil.repository.entity.enums.ERole;
import com.secil.repository.entity.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder

public class Auth extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authId;
    private String name;
    private String surname;
    @Column(unique = true)
    private String email;

    private String password;
    @Column(unique = true)
    private String username;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ERole role=ERole.USER;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus status = EStatus.PENDING;
    private Long addressId;
    private String activationCode;

}
