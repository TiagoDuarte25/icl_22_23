package util;

import types.IType;
import values.*;

public record Coordinates(int envDepth, String varId, String type) implements IValue {}
