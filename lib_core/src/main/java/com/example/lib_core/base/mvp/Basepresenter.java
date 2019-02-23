package com.example.lib_core.base.mvp;

import java.lang.ref.WeakReference;

public abstract class Basepresenter<M,V> {

    public M model;
    public V view;
    private WeakReference<V> vWeakReference;

    public abstract M getModel();

    public void attach(M model,V view){
        this.model= model;
        vWeakReference = new WeakReference<>(view);
        this.view = (V) vWeakReference.get();
    }
    public void desttach(){
        if (vWeakReference!=null){
            vWeakReference.clear();
            vWeakReference = null;
            view=null;
        }
    }
}
