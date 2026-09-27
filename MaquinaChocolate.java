public class MaquinaChocolate extends Maquina{

    private double cantMaxKG;

    public MaquinaChocolate() {
        cantMaxKG = 0;
    }

    public MaquinaChocolate(int codigo, String marca, String modelo, double tarifaDiaria, double cantMaxKG) {
        super(codigo, marca, modelo, tarifaDiaria, true);
        this.cantMaxKG = cantMaxKG;
    }

    public void setCantMaxKG(double cantMaxKG) {
        if (cantMaxKG <= 0) {
            throw new IllegalArgumentException("La capacidad máxima debe ser mayor que cero.");
        }
        this.cantMaxKG = cantMaxKG;
    }

    public double getCantMaxKG() {
        return cantMaxKG;
    }

    @Override
    public double calcularTarifaDiaria(int dias) {
        double total = getTarifaDiaria() * dias;
        total += 20.0 * cantMaxKG * dias;
        return total;
    }

    @Override
    public String mostrarCaracteristicas() {
        String estado;

        if (getDisponibilidad()) {
            estado = "Disponible";
        } else {
            estado = "Alquilada";
        }

        String informacion = String.format(
                "Código: %d | Marca: %s | Modelo: %s | Tarifa: Q%.2f | Capacidad máxima: %.2f kg | Estado: %s",
                getCodigo(), getMarca(), getModelo(), getTarifaDiaria(), cantMaxKG, estado);

        return informacion;
    }  

    @Override
    public String categoria() {
        return "Fuente de chocolate";
    }


    
}
