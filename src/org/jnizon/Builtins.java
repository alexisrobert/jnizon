package org.jnizon;

public class Builtins {

	public static final Symbol basicForm = new Symbol("BasicForm");

	public static final Symbol plus = new Symbol("Plus");

	public static final Symbol times = new Symbol("Times");

	public static final Symbol list = new Symbol("List");

	public static final Symbol blank = new Symbol("Blank");

	public static final Symbol pattern = new Symbol("Pattern");

	public static final Symbol rule = new Symbol("Rule");

	public static final Symbol holdFirst = new Symbol("HoldFirst");
	public static final Symbol holdAll = new Symbol("HoldAll");
	public static final Symbol holdRest = new Symbol("HoldRest");

	public static final Symbol set = new Symbol("Set");
	public static final Symbol setDelayed = new Symbol("SetDelayed");

	public static final Symbol time = new Symbol("Time");

	public static final Symbol ifCondition = new Symbol("If");

	public static final Symbol whileLoop = new Symbol("While");

	public static final Symbol print = new Symbol("Print");

	public static final Symbol greater = new Symbol("Greater");
	public static final Symbol less = new Symbol("Less");

	public static final Symbol part = new Symbol("Part");
	public static final Symbol length = new Symbol("Length");

	public static final Symbol flat = new Symbol("Flat");
	
	public static final Symbol sameq = new Symbol("SameQ");
	public static final Symbol not = new Symbol("Not");
	public static final Symbol clear = new Symbol("Clear");
	public static final Symbol condition = new Symbol("Condition");
}
