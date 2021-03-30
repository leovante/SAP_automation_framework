package S4;


import com.annotations.TransactionSAP;
import com.annotations.UserSAP;
import com.constants.Credentials;
import com.constants.Helpers.Module.ModuleCommonTransactionMethods;
import com.constants.Helpers.Module.ModuleTestData;
import com.constants.SystemTransactions;
import com.sapTransactions.TRANSACTION;
import com.utils.states.ModuleBaseState;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Test_test extends ModuleBaseState {

    @Test
    @Order(0)
    @UserSAP(user = Credentials.user)
    @TransactionSAP(transaction = SystemTransactions.transactionCode)
    void test_1(ModuleTestData data) {
        ModuleCommonTransactionMethods.TRANS_NUM_1(data, new TRANSACTION(), data.materials_excel);
    }

    @Test
    @Order(1)
    @UserSAP(user = Credentials.user)
    @TransactionSAP(transaction = SystemTransactions.transactionCode)
    void test_2(ModuleTestData data) {
        ModuleCommonTransactionMethods.TRANS_NUM_2(data, new TRANSACTION(), data.materials_excel);
    }
}
