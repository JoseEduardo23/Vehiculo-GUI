public class Vehiculos {
    public String Placa;
    public String Marca;
    public String Cilindraje;
    public String Tipo_Combustible;
    public String Color;
    public String Nombre_Propietario;

    public Vehiculos() {}

    public Vehiculos(String placa, String marca, String cilindraje, String tipo_Combustible, String color, String nombre_Propietario) {
        Placa = placa;
        Marca = marca;
        Cilindraje = cilindraje;
        Tipo_Combustible = tipo_Combustible;
        Color = color;
        Nombre_Propietario = nombre_Propietario;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String placa) {
        Placa = placa;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getCilindraje() {
        return Cilindraje;
    }

    public void setCilindraje(String cilindraje) {
        Cilindraje = cilindraje;
    }

    public String getTipo_Combustible() {
        return Tipo_Combustible;
    }

    public void setTipo_Combustible(String tipo_Combustible) {
        Tipo_Combustible = tipo_Combustible;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getNombre_Propietario() {
        return Nombre_Propietario;
    }

    public void setNombre_Propietario(String nombre_Propietario) {
        Nombre_Propietario = nombre_Propietario;
    }
}