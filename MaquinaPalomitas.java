public class MaquinaPalomitas extends Maquina{

    private int porcionesPorHora;
    private boolean carritoIntegrado;

    public MaquinaPalomitas() {
        porcionesPorHora = 0;
        carritoIntegrado = true;
    }

    public MaquinaPalomitas(int codigo, String marca, String modelo, double tarifaDiaria, int porcionesPorHora, boolean carritoIntegrado) { 
        super(codigo, marca, modelo, tarifaDiaria, true);
        this.porcionesPorHora = porcionesPorHora;
        this.carritoIntegrado = carritoIntegrado;
    }

    public void setPorcionesPorHora(int porcionesPorHora) {
        if (porcionesPorHora <= 0) {
            throw new IllegalArgumentException("Las porciones por hora deben ser mayores que cero.");
        }

        this.porcionesPorHora = porcionesPorHora;
    }

    public int getPorcionesPorHora() {
        return porcionesPorHora;
    }

    public void setCarritoIntegrado(boolean carritoIntegrado) {
        this.carritoIntegrado = carritoIntegrado;
    }

    public boolean getCarritoIntegrado() {
        return carritoIntegrado;
    }

    @Override
    public double calcularTarifaDiaria(int dias) {
        double total = getTarifaDiaria() * dias;
        if (carritoIntegrado) {
            total += 40.0 * dias;
        }
        return total;
    }

    @Override
    public String mostrarCaracteristicas() {
        String carrito;
        String estado;

        if (carritoIntegrado) {
            carrito = "Sí";
        } else {
            carrito = "No";
        }

        if (getDisponibilidad()) {
            estado = "Disponible";
        } else {
            estado = "Alquilada";
        }

        String informacion = String.format(
                "Código: %d | Marca: %s | Modelo: %s | Tarifa: Q%.2f | Porciones/hora: %d | Carrito integrado: %s | Estado: %s",
                getCodigo(), getMarca(), getModelo(), getTarifaDiaria(), porcionesPorHora, carrito, estado);

        return informacion;
    }

    @Override
    public String categoria() {
        return "Palomitas";
    }
    
}
