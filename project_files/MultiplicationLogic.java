package project_files;

import javax.swing.table.DefaultTableModel;

public class MultiplicationLogic {

    public static void performMultiplication(int multiplicand, int multiplier, DefaultTableModel tableModel) {
        int n = calculateBitRange(multiplicand, multiplier);
        int M = toTwoComplement(multiplicand, n + 1);
        int Q = toTwoComplement(multiplier, n);
        int A = 0;
        int bit;

        // Mostrar las conversiones en complemento a dos para depuración
        tableModel.addRow(new Object[]{"Debug", "Multiplicando en complemento a 2", toBinaryStringWithPadding(M, n + 1), "", "", ""});
        tableModel.addRow(new Object[]{"Debug", "Multiplicador en complemento a 2", toBinaryStringWithPadding(Q, n), "", "", ""});

        tableModel.setRowCount(0);

        for (int i = 0; i < n - 1; i++) { // Bucle n-1 veces
            bit = Q & 1;
            String action = "Inicio";
            if (bit == 1) {
                A += M;
                action = "Sumar M a A";
            }

            // Desplazamiento aritmético a la derecha
            A = arithmeticRightShift(A, n + 1);
            Q = arithmeticRightShift(Q, n);

            action += " y desplazamiento a la derecha";
            tableModel.addRow(new Object[]{"Paso " + (i + 1), action, toBinaryStringWithPadding(A, n + 1), toBinaryStringWithPadding(Q, n), toBinaryStringWithPadding(M, n + 1), ""});
        }

        // Verificar bit menos significativo después del bucle
        bit = Q & 1;
        if (bit == 1) {
            A -= M; // Restar M de A
            tableModel.addRow(new Object[]{"Paso Final", "Restar M de A", toBinaryStringWithPadding(A, n + 1), toBinaryStringWithPadding(Q, n), toBinaryStringWithPadding(M, n + 1), ""});
        }

        // Desplazamiento aritmético final a la derecha
        A = arithmeticRightShift(A, n + 1);
        Q = arithmeticRightShift(Q, n);
        String resultadoFinal = calculateResult(A, Q, n);
        tableModel.addRow(new Object[]{"Resultado", "", toBinaryStringWithPadding(A, n + 1), toBinaryStringWithPadding(Q, n), toBinaryStringWithPadding(M, n + 1), resultadoFinal});
    }

    static int toTwoComplement(int number, int bits) {
        if (number >= 0) {
            return number; // El número positivo ya está en complemento a dos
        }
        // Para negativos, convertimos el número a su representación en complemento a dos
        return (1 << bits) + number; // Agrega el bit de signo a la izquierda
    }

    static int calculateBitRange(int multiplicand, int multiplier) {
        // Calculamos cuántos bits se necesitan para el número más grande (en valor absoluto)
        int maxBitLength = Math.max(
                Integer.toBinaryString(Math.abs(multiplicand)).length(),
                Integer.toBinaryString(Math.abs(multiplier)).length()
        );
        // Agregamos 1 al rango para acomodar el bit de signo
        return maxBitLength + 1;
    }

    static int arithmeticRightShift(int number, int bits) {
        return number >> 1 & ~(-1 << (bits - 1));
    }

    static String calculateResult(int A, int Q, int n) {
        int result = ((A & ((1 << n) - 1)) << n) | Q;
        return toBinaryStringWithPadding(result, 2 * n);
    }

    private static String toBinaryStringWithPadding(int number, int totalBits) {
        String binaryString = Integer.toBinaryString(number);
        // Asegurarse de que la cadena tenga el total de bits requerido, rellenando con ceros a la izquierda si es necesario
        while (binaryString.length() < totalBits) {
            binaryString = "0" + binaryString;
        }
        return binaryString;
    }
}
