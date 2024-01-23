package project_files;

import javax.swing.table.DefaultTableModel;

public class MultiplicationTableModel extends DefaultTableModel {

    public MultiplicationTableModel() {
        super();
        addColumn("Paso");
        addColumn("Acción");
        addColumn("A");
        addColumn("Q");
        addColumn("M");
        addColumn("Resultado");
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        // Hacer todas las celdas no editables, para solo mostrar resultados
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // Asegurarse de que el renderizado de la columna se maneje adecuadamente
        return String.class;
    }


}
