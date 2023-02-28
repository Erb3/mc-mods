package github.erb3.fabric.beeperipherals.integrations;

import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.GenericPeripheral;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public class SignPeripheral implements GenericPeripheral {
    @Override
    public String id() {
        return "sign";
    }

    private static void blockUpdate(SignBlockEntity sign) {
        BlockPos signPos = sign.getPos();
        World signWorld = sign.getWorld();
        if (signWorld == null) return;
        BlockState signState = signWorld.getBlockState(signPos);

        sign.markDirty();
        signWorld.updateListeners(signPos, signState, signState, Block.NOTIFY_ALL);
    }

    @LuaFunction(mainThread = true)
    public static void setSignText(SignBlockEntity sign, IArguments args) throws LuaException {
        for (int i = 0; i < 4; i++) {
            String line = args.optString(i, "");
            sign.setTextOnRow(i, Text.of(line));
        }

        blockUpdate(sign);
    }

    @LuaFunction(mainThread = true)
    public static void editSignText(SignBlockEntity sign, IArguments args) throws  LuaException {
        for (int i = 0; i < 4; i++) {
            String line = args.optString(i, "");
            if (!line.equals("")) sign.setTextOnRow(i, Text.of(line));
        }

        blockUpdate(sign);
    }

    @LuaFunction(mainThread = true)
    public static void setSignLine(SignBlockEntity sign, int line, String newLine) throws LuaException {
        if (!(line >= 0 && line < 4)) {
            throw new LuaException("Unexpected value for argument 1, got ${line}, expected in range 0-3");
        }

        sign.setTextOnRow(line, Text.of(newLine));
        blockUpdate(sign);
    }

    // Main thread due to world related
    @LuaFunction(mainThread = true)
    public static ArrayList<String> getSignText(SignBlockEntity sign) {
        ArrayList<String> lines = new ArrayList<>();

        for (int i=0; i<4; i++) {
            lines.add(sign.getTextOnRow(i, true).getString());
        }

        return lines;
    }

    @LuaFunction(mainThread = true)
    public static String getSignLine(SignBlockEntity sign, int line) throws LuaException {
        if (!(line >= 0 && line < 4)) {
            throw new LuaException("Unexpected value for argument 1, got ${line}, expected in range 0-3");
        }

        return sign.getTextOnRow(line, true).getString();
    }
}
