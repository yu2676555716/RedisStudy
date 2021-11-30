package com.yu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.io.Serializable;

// redis保存序列化实体类示例
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class User1 implements Serializable {

    private String name;
    private int age;
}
