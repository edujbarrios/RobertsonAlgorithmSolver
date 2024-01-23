package project_files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.table.DefaultTableModel;
import static org.junit.jupiter.api.Assertions.*;

class MultiplicationLogicTest {
	private DefaultTableModel tableModel;

	@BeforeEach
	void setUp() {
		tableModel = new DefaultTableModel();
	}

	// Pruebas para toTwoComplement
	@Test
	void testToTwoComplementPositive() {
		assertEquals(5, MultiplicationLogic.toTwoComplement(5, 3));
		assertEquals(2, MultiplicationLogic.toTwoComplement(2, 2));
		assertEquals(0, MultiplicationLogic.toTwoComplement(0, 1));
	}

	@Test
	void testToTwoComplementNegative() {
		assertEquals(6, MultiplicationLogic.toTwoComplement(-2, 3));
		assertEquals(4, MultiplicationLogic.toTwoComplement(-4, 3));
		assertEquals(0xFFFFFFFE, MultiplicationLogic.toTwoComplement(-2, 32));
	}

	// Pruebas para calculateBitRange
	@Test
	void testCalculateBitRange() {
		assertEquals(4, MultiplicationLogic.calculateBitRange(3, -4));
		assertEquals(3, MultiplicationLogic.calculateBitRange(2, 3));
		assertEquals(2, MultiplicationLogic.calculateBitRange(1, -1));
	}

	// Pruebas para arithmeticRightShift
	@Test
	void testArithmeticRightShift() {
		assertEquals(2, MultiplicationLogic.arithmeticRightShift(5, 3));
		assertEquals(1, MultiplicationLogic.arithmeticRightShift(2, 2));
		assertEquals(0x7FFFFFFF, MultiplicationLogic.arithmeticRightShift(-1, 32));
	}

	// Pruebas para calculateResult
	@Test
	void testCalculateResult() {
		assertEquals("000110", MultiplicationLogic.calculateResult(3, 2, 3));
		assertEquals("000010", MultiplicationLogic.calculateResult(1, 2, 3));
		assertEquals("11111111111111111111111111111110", MultiplicationLogic.calculateResult(-1, -2, 32));
	}

	// Pruebas de integración para performMultiplication
	@Test
	void testPerformMultiplication() {
		MultiplicationLogic.performMultiplication(3, -4, tableModel);
		String result = tableModel.getValueAt(tableModel.getRowCount() - 1, 5).toString();
		assertEquals("-12", result); // 3 * -4 = -12

		tableModel.setRowCount(0);
		MultiplicationLogic.performMultiplication(2, 3, tableModel);
		result = tableModel.getValueAt(tableModel.getRowCount() - 1, 5).toString();
		assertEquals("6", result); // 2 * 3 = 6

		tableModel.setRowCount(0);
		MultiplicationLogic.performMultiplication(-1, -1, tableModel);
		result = tableModel.getValueAt(tableModel.getRowCount() - 1, 5).toString();
		assertEquals("1", result); // -1 * -1 = 1
	}
}
