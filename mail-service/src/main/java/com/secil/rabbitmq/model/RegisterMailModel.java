package com.secil.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterMailModel implements Serializable {
    private String authId;
    private String username;
    private String email;
    private String activationCode;
}
