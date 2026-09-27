
import java.util.Scanner;

public class Principal{

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Inventario inventario = new Inventario();
        cargarDatosIniciales(inventario);

        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            int opcion = leerEntero(teclado, "Seleccione una opción: ");

            switch (opcion) {

                case 1:
                    registrarMaquina(teclado, inventario);
                    break;

                case 2:
                    consultarInventario(teclado, inventario);
                    break;

                case 3:
                    cotizarAlquiler(teclado, inventario);
                    break;

                case 4:
                    confirmarAlquiler(teclado, inventario);
                    break;

                case 5:
                    registrarDevolucion(teclado, inventario);
                    break;

                case 6:
                    System.out.println(inventario.reporteTotal() + "\n");
                    break;

                case 7:
                    salir = true;
                    System.out.println("Saliendo del sistema.");
                    break;

                default:
                    System.out.println("Opción inválida. Ingrese un número del 1 al 7.\n");
            }
        }

      
    }

    private static void mostrarMenu() {
        System.out.println("Bienvenido a Dulce Estación.");
        System.out.println("1. Registrar máquina");
        System.out.println("2. Consultar inventario (por código)");
        System.out.println("3. Cotizar alquiler");
        System.out.println("4. Confirmar alquiler");
        System.out.println("5. Registrar devolución");
        System.out.println("6. Reporte general");
        System.out.println("7. Salir");
    }

    private static int leerEntero(Scanner teclado, String mensaje) {
        boolean valido = false;
        int valor = 0;

        while (!valido) {
            System.out.print(mensaje);
            try {
                valor = teclado.nextInt();
                teclado.nextLine();
                valido = true;
            } catch (Exception e) {
                System.out.println("Entrada inválida. Debe ingresar un número entero.");
                teclado.nextLine();
            }
        }
        return valor;
    }

    private static int leerEnteroPositivo(Scanner teclado, String mensaje) {
        int valor;
        do {
            valor = leerEntero(teclado, mensaje);
            if (valor <= 0) {
                System.out.println("El valor debe ser mayor que cero.");
            }
        } while (valor <= 0);
        return valor;
    }

    private static double leerDouble(Scanner teclado, String mensaje) {
        boolean valido = false;
        double valor = 0;

        while (!valido) {
            System.out.print(mensaje);
            try {
                valor = teclado.nextDouble();
                teclado.nextLine();
                valido = true;
            } catch (Exception e) {
                System.out.println("Entrada inválida. Debe ingresar un número.");
                teclado.nextLine();
            }
        }
        return valor;
    }

    private static double leerDoublePositivo(Scanner teclado, String mensaje) {
        double valor;
        do {
            valor = leerDouble(teclado, mensaje);
            if (valor <= 0) {
                System.out.println("El valor debe ser mayor que cero.");
            }
        } while (valor <= 0);
        return valor;
    }

    private static String leerCadena(Scanner teclado, String mensaje) {
        String valor;
        do {
            System.out.print(mensaje);
            valor = teclado.nextLine();
            if (valor.isEmpty()) {
                System.out.println("Este campo no puede estar vacío.");
            }
        } while (valor.isEmpty());
        return valor;
    }


    private static boolean leerSiNo(Scanner teclado, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String resp = teclado.nextLine();

            if (resp.equals("s") || resp.equals("S")) {
                return true;
            }

            if (resp.equals("n") || resp.equals("N")) {
                return false;
            }

            System.out.println("Respuesta inválida. Escriba 's' o 'n'.");
        }
    }

    private static int leerCategoria(Scanner teclado) {
        int cat;
        do {
            cat = leerEntero(teclado, "Categoría (1=Palomitas, 2=Algodón, 3=Chocolate): ");
            if (cat < 1 || cat > 3) {
                System.out.println("Categoría inválida. Ingrese 1, 2 o 3.");
            }
        } while (cat < 1 || cat > 3);
        return cat;
    }

    private static void registrarMaquina(Scanner teclado, Inventario inventario) {

        System.out.println("\nRegistrar máquina");

        try {
            int cat = leerCategoria(teclado);
            int codigo = leerEntero(teclado, "Código de inventario: ");
            String marca = leerCadena(teclado, "Marca: ");
            String modelo = leerCadena(teclado, "Modelo: ");
            double tarifa = leerDoublePositivo(teclado, "Tarifa diaria (Q): ");

            Maquina m;

        switch (cat) {
            case 1: {
                int porciones = leerEnteroPositivo(
                    teclado, "Porciones por hora: ");

                boolean carrito = leerSiNo(
                    teclado, "¿Carrito integrado? (s/n): ");

                m = new MaquinaPalomitas(codigo, marca, modelo, tarifa, porciones, carrito);

                break;
            }

            case 2: {
                int potencia = leerEnteroPositivo(teclado, "Potencia en vatios: ");

                m = new MaquinaAlgodon(codigo, marca, modelo, tarifa, potencia);

                break;
            }

            default: {
                double capacidad = leerDoublePositivo(teclado, "Capacidad máxima en kg: ");

                m = new MaquinaChocolate(codigo, marca, modelo, tarifa, capacidad);

                break;
            }
        }

        boolean ok = inventario.registrarMaquina(m);

        if (ok) {
            System.out.println("Máquina registrada correctamente.");
        } else {
            System.out.println(
                    "Ya existe una máquina con ese código.");
        }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Fin de la operación de registro.\n");
        }
    }


    private static void consultarInventario(Scanner teclado, Inventario inventario) {
        System.out.println("\nConsultar máquina");
        int codigo = leerEntero(teclado, "Código de la máquina: ");
        System.out.println(inventario.buscarMaquinaCodigo(codigo) + "\n");
    }

    private static void cotizarAlquiler(Scanner teclado, Inventario inventario) {
        System.out.println("\nCotizar alquiler");
        int codigo = leerEntero(teclado, "Código de la máquina: ");
        int dias = leerEnteroPositivo(teclado, "Cantidad de días: ");
        System.out.println(inventario.cotizar(codigo, dias) + "\n");
    }


    private static void confirmarAlquiler(Scanner teclado, Inventario inventario) {

        System.out.println("\nConfirmar alquiler");

        int codigo = leerEntero(teclado, "Código de la máquina: ");

        int dias = leerEnteroPositivo(teclado, "Cantidad de días: ");

        System.out.println(inventario.cotizar(codigo, dias));

        boolean confirmar = leerSiNo(teclado, "¿Confirmar alquiler? (s/n): ");

        if (confirmar) {
            boolean ok = inventario.alquilar(codigo, dias);

            if (ok) {
                System.out.println("Alquiler confirmado.\n");
            } else {
                System.out.println("No se pudo confirmar: el código no existe " + "o la máquina está ocupada.\n");
            }
        } else {
            System.out.println("Operación cancelada. No se realizaron cambios.\n");
        }
    }

    private static void registrarDevolucion(Scanner teclado, Inventario inventario) {

        System.out.println("\nRegistrar devolución");

        int codigo = leerEntero(teclado, "Código de la máquina: ");

        boolean ok = inventario.devolver(codigo);

        if (ok) {
            System.out.println("Devolución registrada.\n");
        } else {
            System.out.println("No se pudo registrar: el código no existe " + "o la máquina ya está disponible.\n");
        }
    }

    private static void cargarDatosIniciales(Inventario inventario) {
        inventario.registrarMaquina(new MaquinaPalomitas(67, "Maquina Palomita 1", "100", 100.0, 40, true));
        inventario.registrarMaquina(new MaquinaPalomitas(68, "Maquina Palomita 2", "200", 80.0, 30, false));
        inventario.registrarMaquina(new MaquinaAlgodon(69, "Maquina Algodon 1", "300", 90.0, 1200));
        inventario.registrarMaquina(new MaquinaAlgodon(70, "Maquina Algodon 2", "400", 70.0, 800));
        inventario.registrarMaquina(new MaquinaChocolate(71, "Maquina Chocolate 1", "500", 150.0, 2.5));
        inventario.registrarMaquina(new MaquinaChocolate(72, "Maquina Chocolate 2", "600", 100.0, 1.0));
    }


}
