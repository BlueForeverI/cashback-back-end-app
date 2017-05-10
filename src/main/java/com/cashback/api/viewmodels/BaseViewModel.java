package com.cashback.api.viewmodels;

import org.modelmapper.ModelMapper;

/**
 * Created by George on 9.5.2017 г..
 */
public abstract class BaseViewModel<M, V> {

    private Long id;

    Class<M> modelClass;
    Class<V> vmClass;

    protected BaseViewModel() {}

    protected BaseViewModel(Class<M> modelClass, Class<V> vmClass) {
        this.modelClass = modelClass;
        this.vmClass = vmClass;
    }

    public V fromModel(M model) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(model, vmClass);
    }

    public M toModel() {
        ModelMapper mapper = new ModelMapper();
        return (mapper.map(this, modelClass));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
