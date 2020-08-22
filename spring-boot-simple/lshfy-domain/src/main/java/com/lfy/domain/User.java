package com.lfy.domain;

import lombok.Data;

/**
 * Created by lishihao4 on 2019/1/16.
 * DESC TODO
 */

@Data
public class User {
    private Long id;
    private String name;
    private Integer age;

    private String account;

    private String password;
}
