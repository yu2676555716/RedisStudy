package com.yu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

// redis保存未序列化实体类示例
@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User{

    private String name;
    private int age;
}
