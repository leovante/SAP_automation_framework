package com.constants.Helpers.Module;

public class MaterialData<T extends MaterialData<T>> {
    String
            material = "",
            factory = "",
            batch = "",
            storage = "",
            storageNumber = "",
            storagePlace = "",
            storageArea = "",
            storageType = "",
            storageUnit = "",
            typeEC = "",
            сommonStorage = "",
            EI = "",
            shtrihCode = "",
            SReserve = "",
            SReserveNumber = ""
    ;

    public String getStorageType() {
        return storageType;
    }

    public T setStorageType(String storageType) {
        this.storageType = storageType;
        return (T) this;
    }

    public String getTypeEC() {
        return typeEC;
    }

    public T setTypeEC(String typeEC) {
        this.typeEC = typeEC;
        return (T) this;
    }

    public String getStorageNumber() {
        return storageNumber;
    }

    public T setStorageNumber(String storageNumber) {
        this.storageNumber = storageNumber;
        return (T) this;
    }

    public String getСommonStorage() {
        return сommonStorage;
    }

    public T setСommonStorage(String сommonStorage) {
        this.сommonStorage = сommonStorage;
        return (T) this;
    }

    public String getShtrihCode() {
        return shtrihCode;
    }

    public T setShtrihCode(String shtrihCode) {
        this.shtrihCode = shtrihCode;
        return (T) this;
    }

    public String getStorageArea() {
        return storageArea;
    }

    public T setStorageArea(String storageArea) {
        this.storageArea = storageArea;
        return (T) this;
    }

    public String getCommonStorage() {
        return сommonStorage;
    }

    public T setCommonStorage(String сommonStorage) {
        this.сommonStorage = сommonStorage;
        return (T) this;
    }

    public String getStorageUnit() {
        return storageUnit;
    }

    public T setStorageUnit(String LENUM) {
        String msgTmp = LENUM;
        if (LENUM.length() > 18) {
            msgTmp = LENUM.substring(LENUM.length() - 18);
        }

        this.storageUnit = msgTmp;
        return (T) this;
    }

    public String getMaterial() {
        return material;
    }

    public T setMaterial(String material) {
        this.material = material;
        return (T) this;
    }

    public String getFactory() {
        return factory;
    }

    public T setFactory(String werks) {
        this.factory = werks;
        return (T) this;
    }

    public String getStorage() {
        return storage;
    }

    public T setStorage(String lgopt) {
        this.storage = lgopt;
        return (T) this;
    }

    public String getBatch() {
        return batch;
    }

    public T setBatch(String charg) {
        this.batch = charg;
        return (T) this;
    }

    public String getStoragePlace() {
        return storagePlace;
    }

    public T setStoragePlace(String lgpla) {
        this.storagePlace = lgpla;
        return (T) this;
    }

    public String getEI() {
        return EI;
    }

    public T setEI(String EI) {
        this.EI = EI;
        return (T) this;
    }

    public String getSReserve() {
        return SReserve;
    }

    public T setSReserve(String SReserve) {
        this.SReserve = SReserve;
        return (T) this;
    }

    public String getSReserveNumber() {
        return SReserveNumber;
    }

    public T setSReserveNumber(String SReserveNumber) {
        this.SReserveNumber = SReserveNumber;
        return (T) this;
    }
}
