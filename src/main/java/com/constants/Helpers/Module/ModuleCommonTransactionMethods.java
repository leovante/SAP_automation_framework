package com.constants.Helpers.Module;

import com.sapTransactions.TRANSACTION;
import com.utils.sapElement.ElementsCollection;
import com.utils.sapElement.SapElement;

import java.util.List;

import static com.utils.sapElement.Saper.findAllByName;
import static com.utils.sapElement.Saper.findByName;

public class ModuleCommonTransactionMethods {
    public static void TRANS_NUM_1(ModuleTestData data, TRANSACTION oTRANSACTION, List<ModuleMaterialDataExcel> materials) {
        oTRANSACTION
                .fillStorageNumber(materials.get(0).getStorageNumber())
                .internalNumOfSign()
                .pressExecute();
    }

    public static void TRANS_NUM_2(ModuleTestData data, TRANSACTION oTRANSACTION, List<ModuleMaterialDataExcel> materials) {
        SapElement se = findByName(oTRANSACTION.storageNumber);
        se
                .setText(materials.get(0).getStorageNumber())
                .getText();

        ElementsCollection sec = findAllByName(oTRANSACTION.storageNumber);
        sec
                .get(0)
                .clickCurrentCell();

        findAllByName(oTRANSACTION.internalNumOfSign, 1).stream().forEach(i -> i.setText("заполнить значение в таблице"));
    }
}