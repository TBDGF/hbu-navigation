package cn.allenji.hbunavigation.usecase;
import cn.allenji.hbunavigation.domain.port.GraphRepository;

public class UpdateGraph {
    private GraphRepository repository;

    public void update(){
        repository.updateGraph();
    }
}
