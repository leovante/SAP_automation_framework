package com.constants.Helpers.Module;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class ModuleMaterialDataExcel extends MaterialData<ModuleMaterialDataExcel> implements Cloneable {
    String
            id = "",                
            storageSection = "",    
            barcodeFlag = "",       
            prvdDate = "",          
            storageTwo = "",        
            storage_MM = "",        
            storagePlaceTwo = ""    
                    ;



    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public ModuleMaterialDataExcel clone() {
        ModuleMaterialDataExcel data = null;
        try {
            data = (ModuleMaterialDataExcel) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        data.id = id;
        data.material = material;
        data.factory = factory;
        data.storage = storage;
        data.storageTwo = storageTwo;
        data.batch = batch;
        data.storagePlace = storagePlace;
        data.storagePlaceTwo = storagePlaceTwo;
        data.storageUnit = storageUnit;
        data.storageArea = storageArea;
        data.storage_MM = storage_MM;
        data.storageNumber = storageNumber;
        data.сommonStorage = сommonStorage;
        data.shtrihCode = shtrihCode;
        data.typeEC = typeEC;
        data.storageType = storageType;
        data.barcodeFlag = barcodeFlag;
        data.EI = EI;
        data.prvdDate = prvdDate;
        data.SReserve = SReserve;
        data.SReserveNumber = SReserveNumber;
        data.storageSection = storageSection;
        return data;
    }
}
