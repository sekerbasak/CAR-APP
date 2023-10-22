package com.example.car.dto.request;

import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdatePasswordRequest implements Serializable {
    private String oldPassword;
    private String newPassword;
    private String newPasswordVerify;

}
