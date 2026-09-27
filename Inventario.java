import java.util.ArrayList;

public class Inventario {

    private ArrayList<Maquina> maquinas;
    private double ingresosAcumulados;

     public Inventario() {

        this.maquinas = new ArrayList<>();
        this.ingresosAcumulados = 0.0;

    }

    public Maquina buscarPorCodigo(int codigo) {
        for (Maquina m : maquinas) {
            if (m.getCodigo() == codigo) {
                return m;
            }
        }
        return null;
    }

    public String buscarMaquinaCodigo(int codigo) {
        Maquina m = buscarPorCodigo(codigo);
        if (m == null) {
            return "No existe una máquina con el código " + codigo + ".";
        }
        return m.mostrarCaracteristicas();
    }

    public boolean registrarMaquina(Maquina maquina) {
        if (maquina == null||buscarPorCodigo(maquina.getCodigo())!=null) {
            return false;
        }

        maquina.setDisponibilidad(true);

        maquinas.add(maquina);
        return true;
    }


    public String cotizar(int codigo, int dias) {
        if (dias <= 0) {
            return "La cantidad de días debe ser un entero positivo.";
        }

        Maquina m = buscarPorCodigo(codigo);

        if (m == null) {
            return "No existe una máquina con el código " + codigo + ".";
        }

        double total = m.calcularTarifaDiaria(dias);

        String cotizacion = String.format("%s%nDías solicitados: %d | Costo total: Q%.2f",
                m.mostrarCaracteristicas(), dias, total);

        return cotizacion;
    }


    public boolean alquilar(int codigo, int dias) {
        if (dias <= 0) {
            return false;
        }
        Maquina m = buscarPorCodigo(codigo);
        if (m == null || !m.getDisponibilidad()) {
            return false;
        }
        double total = m.calcularTarifaDiaria(dias);
        m.setDisponibilidad(false);
        ingresosAcumulados += total;
        return true;
    }

    public boolean devolver(int codigo) {
        Maquina m = buscarPorCodigo(codigo);
        if (m == null || m.getDisponibilidad()) {
            return false;
        }
        m.setDisponibilidad(true);
        return true;
    }

    public String reporteTotal() {
        int totalPalomitas = 0;
        int dispPalomitas = 0;
        int totalAlgodon = 0;
        int dispAlgodon = 0;
        int totalChocolate = 0;
        int dispChocolate = 0;

    for (Maquina m : maquinas) {
        if (m instanceof MaquinaPalomitas) {
            totalPalomitas++;

            if (m.getDisponibilidad()) {
                dispPalomitas++;
            }
        } else if (m instanceof MaquinaAlgodon) {
            totalAlgodon++;

            if (m.getDisponibilidad()) {
                dispAlgodon++;
            }
        } else if (m instanceof MaquinaChocolate) {
            totalChocolate++;

            if (m.getDisponibilidad()) {
                dispChocolate++;
            }
        }
    }

    String reporte = "Reporte general\n";

    reporte += String.format("Palomitas: %d registradas | %d disponibles | %d alquiladas%n",
            totalPalomitas, dispPalomitas, totalPalomitas - dispPalomitas);

    reporte += String.format("Algodón de azúcar: %d registradas | %d disponibles | %d alquiladas%n",
            totalAlgodon, dispAlgodon, totalAlgodon - dispAlgodon);

    reporte += String.format("Fuentes de chocolate: %d registradas | %d disponibles | %d alquiladas%n",
            totalChocolate, dispChocolate, totalChocolate - dispChocolate);

    reporte += String.format("Ingresos acumulados por alquileres confirmados: Q%.2f", ingresosAcumulados);

    return reporte;
}

public ArrayList<Maquina> getMaquinas() {
    return maquinas;
}



    
}
