package com.lcl;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service(value = "mystudent2")
public class Student2 {

    private String id;
    private String name ;
}
