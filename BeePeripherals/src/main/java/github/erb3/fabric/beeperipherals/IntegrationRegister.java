package github.erb3.fabric.beeperipherals;

import dan200.computercraft.api.ComputerCraftAPI;
import github.erb3.fabric.beeperipherals.integrations.SignPeripheral;

public class IntegrationRegister {

    public static void registerPeripherals() {
        ComputerCraftAPI.registerGenericSource(new SignPeripheral());
    }

}
