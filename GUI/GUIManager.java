package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Building.Building;
import Building.BuildingFactory;
import Resource.Resource;

public class GUIManager {
    private static GUIManager self;
    private JFrame frame;
    private int frameWidth = 2000;
    private int frameHeight = 1500;
    private int fontSize = 24;
    private int rowHeight = 32;
    private JTable resourcesTable, buildingsTable;
    private JLabel dayCounterLabel;

    private GUIManager(){
        FontUIResource font = new FontUIResource("Verdana", Font.PLAIN, this.fontSize);
        UIManager.put("Table.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("Button.font", font);
        UIManager.getLookAndFeelDefaults().put("TableHeader.font", font);
        this.frame = new JFrame("My Game");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(this.frameWidth, this.frameHeight);
        this.frame.setVisible(true);
    }

    public static GUIManager getInstance(){
        if(GUIManager.self == null){
            GUIManager.self = new GUIManager();
        }
        return GUIManager.self;
    }

    private void initializeResourceTable(ArrayList<Resource> resources){
        String[] columnNames = {"Resource", "QTY", "Consumption", "Production"};
        Object[][] data = new Object[resources.size()][4];
        for (int i = 0; i < resources.size(); i++) {
            data[i] = new Object[]{resources.get(i).getType().toString(), resources.get(i).getQty(), 0, 0};
        }
        this.resourcesTable = new JTable(new DefaultTableModel(data, columnNames));
    }

    private void initializeBuildingsTable(){
        String[] columnNames = {"ID","Building","Nbr Habitant","Nbr Travailleurs","Consommation","Production", "Construction", "Delete"};
        Object[][] data = new Object[0][8];
        
        this.buildingsTable = new JTable(new DefaultTableModel(data, columnNames));
        TableColumnModel columnModel = this.buildingsTable.getColumnModel();

        this.buildingsTable.getColumn("Construction").setCellRenderer(new ProgressRenderer());
        this.buildingsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        columnModel.getColumn(0).setMaxWidth(40);
        columnModel.getColumn(1).setMinWidth(240);
        columnModel.getColumn(1).setMaxWidth(240);
        columnModel.getColumn(2).setMinWidth(180);
        columnModel.getColumn(2).setMaxWidth(180);
        columnModel.getColumn(3).setMinWidth(200);
        columnModel.getColumn(3).setMaxWidth(200);
        columnModel.getColumn(6).setMinWidth(250);
        columnModel.getColumn(6).setMaxWidth(250);
        columnModel.getColumn(7).setMinWidth(100);
        columnModel.getColumn(7).setMaxWidth(100);

        TableColumn workersColumn = columnModel.getColumn(3);

        workersColumn.setCellRenderer(new AddRemoveRenderer());
        workersColumn.setCellEditor(new AddRemoveEditor());

        TableColumn deleteColumn = columnModel.getColumn(7);
        deleteColumn.setCellRenderer(new DeleteButtonRenderer());
        deleteColumn.setCellEditor(new DeleteButtonEditor());
    }

    private void initializeDayLabel(){
        this.dayCounterLabel = new JLabel("Day: 0", JLabel.CENTER);
        this.dayCounterLabel.setVerticalTextPosition(JLabel.BOTTOM);
        this.dayCounterLabel.setHorizontalTextPosition(JLabel.CENTER);
        this.frame.add(this.dayCounterLabel);
    }

    public void initializeGUI(ArrayList<Resource> resources){
        this.initializeDayLabel();
        this.initializeResourceTable(resources);
        this.initializeBuildingsTable();
        this.resourcesTable.setRowHeight(this.rowHeight);
        this.buildingsTable.setRowHeight(this.rowHeight);
        
        Container c = this.frame.getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.add(this.resourcesTable.getTableHeader());
        c.add(this.resourcesTable);
        c.add(this.buildingsTable.getTableHeader());
        c.add(this.buildingsTable);
        c.add(new NewBuildingPanel(BuildingFactory.getAllBuildingNames(), this.frame), BorderLayout.PAGE_END);
        try{
            SwingUtilities.updateComponentTreeUI(this.frame);
        }catch(Exception e){}
    }

    public void updateResourcesTable(ArrayList<Resource> resources, ArrayList<Resource> consumptions, ArrayList<Resource> productions){
        DefaultTableModel tableModel = (DefaultTableModel)this.resourcesTable.getModel();

        for (int i = 0; i < resources.size(); i++) {
            tableModel.setValueAt(resources.get(i).getQty(), i, 1);
            tableModel.setValueAt(consumptions.get(i).getQty(), i, 2);
            tableModel.setValueAt(productions.get(i).getQty(), i, 3);
        }
        tableModel.fireTableDataChanged();
    }

    public void updateBuildingsTable(ArrayList<Building> buildings){
        DefaultTableModel tableModel = (DefaultTableModel)this.buildingsTable.getModel();

        for (int i = 0; i < buildings.size(); i++) {
            Building building = buildings.get(i);
            
            tableModel.setValueAt(i + 1, i, 0);
            tableModel.setValueAt(building.toString(), i, 1);
            tableModel.setValueAt(building.getNbrHabitants(), i, 2);
            tableModel.setValueAt(building.getNbrTravailleurs(), i, 3);
            tableModel.setValueAt(building.getConsumption(), i, 4);
            tableModel.setValueAt(building.getProduction(), i, 5);
            tableModel.setValueAt(building.getConstructionProgress(), i, 6);
        }
        tableModel.fireTableDataChanged();
    }

    public void addBuildingToTable(ArrayList<Building> buildings){
        DefaultTableModel tableModel = (DefaultTableModel)this.buildingsTable.getModel();
        Building building = buildings.get(buildings.size() - 1);

        Object[] data = {
            buildings.size(),
            building.toString(),
            building.getNbrHabitants(),
            building.getNbrTravailleurs(),
            building.getConsumption(),
            building.getProduction(),
            building.getConstructionProgress(),
            ""
        };
        tableModel.addRow(data);
        tableModel.fireTableDataChanged();
    }

    public void removeBuildingFromTable(int index){
        DefaultTableModel tableModel = (DefaultTableModel)this.buildingsTable.getModel();
        tableModel.removeRow(index);
        tableModel.fireTableDataChanged();
    }

    public void updateDay(int dayCounter){
        this.dayCounterLabel.setText("Day: " + dayCounter);
    }

}
