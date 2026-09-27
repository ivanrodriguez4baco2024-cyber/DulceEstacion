public class MaquinaAlgodon extends Maquina {

    private int potenciaAlgodon;

    public MaquinaAlgodon() {
        potenciaAlgodon = 0;
    }

    public MaquinaAlgodon(int codigo, String marca, String modelo, double tarifaDiaria, int potenciaAlgodon) {
        super(codigo, marca, modelo, tarifaDiaria, true);
        this.potenciaAlgodon = potenciaAlgodon;
    }

    public void setPotenciaAlgodon(int potenciaAlgodon) {
        if (potenciaAlgodon <= 0) {
            throw new IllegalArgumentException("La potencia debe ser mayor que cero.");
        }
        this.potenciaAlgodon = potenciaAlgodon;
    }

    public int getPotenciaAlgodon() {
        return potenciaAlgodon;
    }

    @Override
    public double calcularTarifaDiaria(int dias) {

        double total = getTarifaDiaria() * dias;

        if (potenciaAlgodon > 1000) {
            total += 60.0;
        }
        return total;
    }

    @Override
    public String mostrarCaracteristicas() {
       String estado;

        if (getDisponibilidad()) {
        estado = "Disponible";
        }  else {
        estado = "Alquilada";
        }

        String informacion = String.format(
                "Código: %d | Marca: %s | Modelo: %s | Tarifa: Q%.2f | Potencia: %d W | Estado: %s",
                getCodigo(), getMarca(), getModelo(), getTarifaDiaria(), potenciaAlgodon, estado);

        return informacion;
    }

    @Override
    public String categoria() {
        return "Algodón de azúcar";
    }
    
}
