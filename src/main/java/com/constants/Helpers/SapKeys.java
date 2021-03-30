package com.constants.Helpers;

/**
 * Enum со всеми сочетаниями клавиш в SAP GUI
 * Взяты из спарвки по Sap Scripting: Table GUI_FKEY
 */
public enum SapKeys {
	Enter(0),
	F1(1),
	F2(2),
	F3(3),
	F4(4),
	F5(5),
	F6(6),
	F7(7),
	F8(8),
	F9(9),
	F10(10),
	CtrlPlusS(11),
	F12(12),
	ShiftPlusF1(13),
	ShiftPlusF2(14),
	ShiftPlusF3(15),
	ShiftPlusF4(16),
	ShiftPlusF5(17),
	ShiftPlusF6(18),
	ShiftPlusF7(19),
	ShiftPlusF8(20),
	ShiftPlusF9(21),
	ShiftPlusCtrlPlus0(22),
	ShiftPlusF11(23),
	ShiftPlusF12(24),
	CtrlPlusF1(25),
	CtrlPlusF2(26),
	CtrlPlusF3(27),
	CtrlPlusF4(28),
	CtrlPlusF5(29),
	CtrlPlusF6(30),
	CtrlPlusF7(31),
	CtrlPlusF8(32),
	CtrlPlusF9(33),
	CtrlPlusF10(34),
	CtrlPlusF11(35),
	CtrlPlusF12(36),
	CtrlPlusShiftPlusF1(37),
	CtrlPlusShiftPlusF2(38),
	CtrlPlusShiftPlusF3(39),
	CtrlPlusShiftPlusF4(40),
	CtrlPlusShiftPlusF5(41),
	CtrlPlusShiftPlusF6(42),
	CtrlPlusShiftPlusF7(43),
	CtrlPlusShiftPlusF8(44),
	CtrlPlusShiftPlusF9(45),
	CtrlPlusShiftPlusF10(46),
	CtrlPlusShiftPlusF11(47),
	CtrlPlusShiftPlusF12(48),
	CtrlPlusE(70),
	CtrlPlusF(71),
	CtrlPlusSlash(72),
	CtrlPlusBackslash(73),
	CtrlPlusN(74),
	CtrlPlusO(75),
	CtrlPlusX(76),
	CtrlPlusC(77),
	CtrlPlusV(78),
	CtrlPlusZ(79),
	CtrlPlusPageUp(80),
	PageUp(81),
	PageDown(82),
	CtrlPlusPageDown(83),
	CtrlPlusG(84),
	CtrlPlusR(85),
	CtrlPlusP(86),
	;

	private int key;

	SapKeys(int key) {
		this.key = key;
	}

	public int getKey() {
		return this.key;
	}

	@Override
	public String toString() {
		return this.name()
				.replace("Plus", "+")
				.replace("Slash", "/")
				.replace("Backslash", "\\");
	}

	public static String getNameByKey(int key) {
		for (int i = 0; i < values().length; i++) {
			if (key == values()[i].getKey()) {
				return values()[i].toString();
			}
		}
		return "";
	}
}
