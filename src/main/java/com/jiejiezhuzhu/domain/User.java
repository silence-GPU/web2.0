package com.jiejiezhuzhu.domain;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String remember;
    private String check_code;
}
