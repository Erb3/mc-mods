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

    /**
     * A function which will replace the sign text with the incoming four arguments.
     * If you only want to change the first two lines, and last two empty, just do two args instead.
     * @param sign The sign you want to replace text on.
     * @param args The new sign text, four string arguments, default is empty string.
     * @throws LuaException Anything can happen, so we throw lua exceptions.
     */
    @LuaFunction(mainThread = true)
    public static void setSignText(SignBlockEntity sign, IArguments args) throws LuaException {
        for (int i = 0; i < 4; i++) {
            String line = args.optString(i, "");
            sign.setTextOnRow(i, Text.of(line));
        }

        blockUpdate(sign);
    }

    /**
     * A function which will edit sign text, essentially setSignText just that default is to not edit the line.
     * @param sign The sign you want to edit text on.
     * @param args The new sign text, four string arguments, empty string will skip the line.
     * @throws LuaException Anything can happen in lua!
     */
    @LuaFunction(mainThread = true)
    public static void editSignText(SignBlockEntity sign, IArguments args) throws  LuaException {
        for (int i = 0; i < 4; i++) {
            String line = args.optString(i, "");
            if (!line.equals("")) sign.setTextOnRow(i, Text.of(line));
        }

        blockUpdate(sign);
    }

    /**
     * A function which will set the text of a specific line.
     * @param sign The sign you want to edit a line on.
     * @param line The line number (0 -> 3).
     * @param newLine The new text you want on the line.
     * @throws LuaException Anything can happen!
     */
    @LuaFunction(mainThread = true)
    public static void setSignLine(SignBlockEntity sign, int line, String newLine) throws LuaException {
        if (!(line >= 0 && line < 4)) {
            throw new LuaException("Unexpected value for argument 1, got ${line}, expected in range 0-3");
        }

        sign.setTextOnRow(line, Text.of(newLine));
        blockUpdate(sign);
    }


    /**
     * Function to get all the text on the sign.
     * @param sign The sign you want to read text off.
     * @return A table containing strings of the different lines.
     */
    // Main thread due to world related
    @LuaFunction(mainThread = true)
    public static ArrayList<String> getSignText(SignBlockEntity sign) {
        ArrayList<String> lines = new ArrayList<>();

        for (int i=0; i<4; i++) {
            lines.add(sign.getTextOnRow(i, true).getString());
        }

        return lines;
    }

    /**
     * Gets the text on the specified line.
     * @param sign The sign you want to read a line off.
     * @param line The line number you want to read (0 -> 3).
     * @return A string with the text on that line.
     * @throws LuaException Throws if you have bad arguments
     */
    @LuaFunction(mainThread = true)
    public static String getSignLine(SignBlockEntity sign, int line) throws LuaException {
        if (!(line >= 0 && line < 4)) {
            throw new LuaException("Unexpected value for argument 1, got ${line}, expected in range 0-3");
        }

        return sign.getTextOnRow(line, true).getString();
    }
}
