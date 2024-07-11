import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Form1 extends Vehiculos {
    public JPanel MainPanel;
    public JTextField IPlaca;
    public JTextField IMarc;
    public JTextField ICil;
    public JTextField IComb;
    public JTextField ICol;
    public JTextField IProp;
    public JButton B1;
    private JButton B2;
    private JTextField BPL;
    private JButton B3;
    private JTextField CPL;
    private JTextField CMar;
    private JTextField CCil;
    private JTextField CComb;
    private JTextField CCOL;
    private JTextField CProp;

    public Form1() {
        B1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores de los campos de texto
                String placa = IPlaca.getText().trim();
                String marca = IMarc.getText().trim();
                String cilindraje = ICil.getText().trim();
                String tipoCombustible = IComb.getText().trim();
                String color = ICol.getText().trim();
                String propietario = IProp.getText().trim();

                // Validar que no haya campos vacíos
                if (placa.isEmpty() || marca.isEmpty() || cilindraje.isEmpty() || tipoCombustible.isEmpty() || color.isEmpty() || propietario.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                    return; // Salir del método actionPerformed sin hacer la inserción
                }

                // Si todos los campos están llenos, proceder con la inserción en la base de datos
                String url = "jdbc:mysql://localhost:3306/vehiculos";
                String user = "root";
                String password = "j.eduardo23";

                Vehiculos vehiculos1 = new Vehiculos(placa, marca, cilindraje, tipoCombustible, color, propietario);
                String sql = "INSERT INTO vehiculos (Placa_Vehiculo, Marca_Vehiculo, Cilindraje, Tipo_Combustible, Color_Vehiculo, Propietario_Vehiculo) VALUES (?, ?, ?, ?, ?, ?)";

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    PreparedStatement cadenaPreparada = connection.prepareStatement(sql);

                    cadenaPreparada.setString(1, vehiculos1.getPlaca());
                    cadenaPreparada.setString(2, vehiculos1.getMarca());
                    cadenaPreparada.setString(3, vehiculos1.getCilindraje());
                    cadenaPreparada.setString(4, vehiculos1.getTipo_Combustible());
                    cadenaPreparada.setString(5, vehiculos1.getColor());
                    cadenaPreparada.setString(6, vehiculos1.getNombre_Propietario());

                    int filasInsertadas = cadenaPreparada.executeUpdate();
                    if (filasInsertadas > 0) {
                        System.out.println("Se han insertado correctamente los datos.");
                        JOptionPane.showMessageDialog(null, "DATOS INGRESADOS", null, JOptionPane.WARNING_MESSAGE);
                    } else {
                        System.out.println("No se han podido insertar los datos.");
                    }
                } catch (SQLException exception) {
                    throw new RuntimeException(exception);
                }
            }
        });

        B2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IPlaca.setText(null);
                ICil.setText(null);
                IMarc.setText(null);
                ICol.setText(null);
                IComb.setText(null);
                IProp.setText(null);

                BPL.setText(null);
                CPL.setText(null);
                CMar.setText(null);
                CCil.setText(null);
                CComb.setText(null);
                CCOL.setText(null);
                CProp.setText(null);

            }
        });
        B3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/vehiculos";
                String user = "root";
                String password = "j.eduardo23";
                String query = "SELECT * FROM vehiculos WHERE Placa_Vehiculo = ?";

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    System.out.println("Conectado a la base de datos");

                    PreparedStatement statement = connection.prepareStatement(query);
                    String placa = BPL.getText().trim();
                    System.out.println("Buscando placa: " + placa);
                    statement.setString(1, placa);
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        String Cplaca = resultSet.getString("Placa_Vehiculo");
                        String marca = resultSet.getString("Marca_Vehiculo");
                        String cilindraje = resultSet.getString("Cilindraje");
                        String tipoCombustible = resultSet.getString("Tipo_Combustible");
                        String color = resultSet.getString("Color_Vehiculo");
                        String propietario = resultSet.getString("Propietario_Vehiculo");

                        System.out.println("Vehículo encontrado: " + Cplaca);
                        CPL.setText(Cplaca);
                        CMar.setText(marca);
                        CCil.setText(cilindraje);
                        CComb.setText(tipoCombustible);
                        CCOL.setText(color);
                        CProp.setText(propietario);
                    } else {
                        JOptionPane.showMessageDialog(null, "Vehículo no encontrado", null, JOptionPane.WARNING_MESSAGE);
                        System.out.println("Vehículo no encontrado: " + placa);
                        CMar.setText("");
                        CCil.setText("");
                        CComb.setText("");
                        CCOL.setText("");
                        CProp.setText("");
                    }
                } catch (SQLException e1) {
                    System.out.println("Error en la conexión o consulta SQL:");
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos", null, JOptionPane.ERROR_MESSAGE);
                    CMar.setText("");
                    CCil.setText("");
                    CComb.setText("");
                    CCOL.setText("");
                    CProp.setText("");
                }
            }
        });
    }
}