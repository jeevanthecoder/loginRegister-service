package com.jeevan.trainticketreservation.loginregisterservice.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class JwtRequest {
    private String userName;
    private String userPassword;


}