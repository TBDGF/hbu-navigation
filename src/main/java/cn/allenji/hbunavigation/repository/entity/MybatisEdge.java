package cn.allenji.hbunavigation.repository.entity;

import lombok.Data;

@Data
public class MybatisEdge {
    String source,target;
    int weight;
}
