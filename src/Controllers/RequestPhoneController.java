package Controllers;

import MainClasses.*;
import java.util.List;

public class RequestPhoneController {

    private PhoneStore phoneStore = PhoneStore.getInstance();

    public boolean isPhoneAvailable(String modelName) {
        List<Phone> allPhones = phoneStore.getAllPhones();
        for (Phone phone : allPhones) {
            if (PhoneStore.isSameModel(phone.getModelName(), modelName)) {
                return true;
            }
        }
        return false;
    }

    public void requestPhone(Client client, String modelName) {
        phoneStore.requestPhone(client, modelName);
    }
}
