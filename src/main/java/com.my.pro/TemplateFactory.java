package com.my.pro;

public class TemplateFactory {

    static ObjectCreator<Object> creator;

    public static void main(String[] args) {

        Object o = creator.create(ScopeObject.SINGLETON);
    }


    interface ObjectCreator<T> {

        T createSingleton();

        T createPrototype();

        default T create(ScopeObject scopeObject) {
            if (scopeObject == ScopeObject.SINGLETON) return createSingleton();
            if (scopeObject == ScopeObject.PROTOTYPE) return createPrototype();
            throw new RuntimeException("Unknown object scope");
        }
    }

    enum ScopeObject {
        SINGLETON,
        PROTOTYPE
    }

}
