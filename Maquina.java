public abstract class Maquina {

    private int codigo;
    private String marca;
    private String modelo;
    private double tarifaDiaria;
    private boolean disponibilidad;

    public Maquina(){

        codigo = 0;
        marca = "";
        modelo = "";
        tarifaDiaria = 0;
        disponibilidad = true;
    }


    public Maquina(int codigo, String marca, String modelo, double tarifaDiaria, boolean disponibilidad) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        setTarifaDiaria(tarifaDiaria);
        this.disponibilidad = true;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setTarifaDiaria(double tarifaDiaria) {
        if (tarifaDiaria <= 0) {
            throw new IllegalArgumentException("La tarifa diaria debe ser mayor que cero.");
        }

        this.tarifaDiaria = tarifaDiaria;
    }

    public double getTarifaDiaria() {
        return tarifaDiaria;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public boolean getDisponibilidad() {
        return disponibilidad;
    }

    public abstract double calcularTarifaDiaria(int dias);
    public abstract String mostrarCaracteristicas();
    public abstract String categoria();


    
}
