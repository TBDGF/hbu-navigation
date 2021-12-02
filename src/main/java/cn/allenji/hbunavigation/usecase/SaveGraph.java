package cn.allenji.hbunavigation.usecase;

import cn.allenji.hbunavigation.domain.port.GraphRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SaveGraph {
    private final GraphRepository repository;

    public void save(){
        repository.saveGraph();
    }
}
