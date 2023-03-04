package github.erb3.fabric.beeperipherals.integrations.vanilla;

import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class SignPeripheral implements IPeripheral {
    private final SignBlockEntity sign;

    public SignPeripheral(SignBlockEntity be) {
        this.sign = be;
    }

    @Override
    public String getType() {
        return "sign";
    }

    private void blockUpdate() {
        BlockPos signPos = this.sign.getPos();
        World signWorld = this.sign.getWorld();
        if (signWorld == null) return;
        BlockState signState = signWorld.getBlockState(signPos);

        this.sign.markDirty();
        signWorld.updateListeners(signPos, signState, signState, Block.NOTIFY_ALL);
    }

    @LuaFunction(mainThread = true)
    public final void setSignText(IArguments args) throws LuaException {
        for (int i = 0; i < 4; i++) {
            String line = args.optString(i, "");
            this.sign.setTextOnRow(i, Text.of(line));
        }

        blockUpdate();
    }

    @LuaFunction(mainThread = true)
    public final void editSignText(IArguments args) throws  LuaException {
        for (int i = 0; i < 4; i++) {
            String line = args.optString(i, "");
            if (!line.equals("")) this.sign.setTextOnRow(i, Text.of(line));
        }

        blockUpdate();
    }

    @LuaFunction(mainThread = true)
    public final void setSignLine(int line, String newLine) throws LuaException {
        if (!(line >= 0 && line < 4)) {
            throw new LuaException(String.format("Unexpected value for argument 1, got %n, expected to be in range 0-3."), line);
        }

        this.sign.setTextOnRow(line, Text.of(newLine));
        blockUpdate();
    }


    @LuaFunction(mainThread = true)
    public final ArrayList<String> getSignText() {
        ArrayList<String> lines = new ArrayList<>();

        for (int i=0; i<4; i++) {
            lines.add(this.sign.getTextOnRow(i, true).getString());
        }

        return lines;
    }

    @LuaFunction(mainThread = true)
    public final String getSignLine(int line) throws LuaException {
        if (!(line >= 0 && line < 4)) {
            throw new LuaException(String.format("Unexpected value for argument 1, got %n, expected to be in range 0-3."), line);
        }

        return this.sign.getTextOnRow(line, true).getString();
    }

    @Override
    public final boolean equals(@Nullable IPeripheral other) {
        return false;
    }
}
