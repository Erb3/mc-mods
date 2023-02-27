package github.erb3.fabric.catperipherals.peripherals.redstoneIntegrator;

import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IPeripheral;
import org.jetbrains.annotations.Nullable;

public class RedstoneIntegratorPeripheral implements IPeripheral {
    @Override
    public String getType() {
        return "redstone_integrator";
    }

    @Override
    public boolean equals(@Nullable IPeripheral peripheral) {
        return this == peripheral;
    }

    @LuaFunction
    public final String helloWorld() {
        return "Hello World";
    }
}
