package CalculoNotas;

import java.util.ArrayList;
import java.util.Scanner;

public class ControlNotasLista {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> estudiantes = new ArrayList<>();
        ArrayList<ArrayList<Double>> notasEstudiantes = new ArrayList<>();
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Agregar estudiante");
            System.out.println("2. Agregar nota a un estudiante");
            System.out.println("3. Modificar una nota");
            System.out.println("4. Eliminar una nota");
            System.out.println("5. Mostrar todas las notas");
            System.out.println("6. Calcular promedio y estado de cada estudiante");
            System.out.println("7. Salir");
            System.out.print("Elige una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    // Agregar estudiante
                    System.out.print("Ingrese el nombre del estudiante: ");
                    String nombre = sc.nextLine();
                    estudiantes.add(nombre);
                    notasEstudiantes.add(new ArrayList<>());
                    System.out.println("Estudiante agregado.");
                    break;

                case 2:
                    // Agregar nota
                    if (estudiantes.isEmpty()) {
                        System.out.println("No hay estudiantes registrados.");
                        break;
                    }
                    mostrarEstudiantes(estudiantes);
                    System.out.print("Seleccione el estudiante: ");
                    int idxEstAdd = sc.nextInt() - 1;
                    if (idxEstAdd >= 0 && idxEstAdd < estudiantes.size()) {
                        System.out.print("Ingrese la nota: ");
                        double notaAdd = sc.nextDouble();
                        notasEstudiantes.get(idxEstAdd).add(notaAdd);
                        System.out.println("Nota agregada.");
                    } else {
                        System.out.println("Estudiante no válido.");
                    }
                    break;

                case 3:
                    // Modificar nota
                    if (estudiantes.isEmpty()) {
                        System.out.println("No hay estudiantes registrados.");
                        break;
                    }
                    mostrarEstudiantes(estudiantes);
                    System.out.print("Seleccione el estudiante: ");
                    int idxEstMod = sc.nextInt() - 1;
                    if (idxEstMod >= 0 && idxEstMod < estudiantes.size()) {
                        if (notasEstudiantes.get(idxEstMod).isEmpty()) {
                            System.out.println("El estudiante no tiene notas registradas.");
                            break;
                        }
                        mostrarNotas(idxEstMod, estudiantes, notasEstudiantes);
                        System.out.print("Seleccione el número de nota a modificar: ");
                        int idxNotaMod = sc.nextInt() - 1;
                        if (idxNotaMod >= 0 && idxNotaMod < notasEstudiantes.get(idxEstMod).size()) {
                            System.out.print("Ingrese la nueva nota: ");
                            double nuevaNota = sc.nextDouble();
                            notasEstudiantes.get(idxEstMod).set(idxNotaMod, nuevaNota);
                            System.out.println("Nota modificada.");
                        } else {
                            System.out.println("Número de nota inválido.");
                        }
                    }
                    break;

                case 4:
                    // Eliminar nota
                    if (estudiantes.isEmpty()) {
                        System.out.println("No hay estudiantes registrados.");
                        break;
                    }
                    mostrarEstudiantes(estudiantes);
                    System.out.print("Seleccione el estudiante: ");
                    int idxEstDel = sc.nextInt() - 1;
                    if (idxEstDel >= 0 && idxEstDel < estudiantes.size()) {
                        if (notasEstudiantes.get(idxEstDel).isEmpty()) {
                            System.out.println("El estudiante no tiene notas registradas.");
                            break;
                        }
                        mostrarNotas(idxEstDel, estudiantes, notasEstudiantes);
                        System.out.print("Seleccione el número de nota a eliminar: ");
                        int idxNotaDel = sc.nextInt() - 1;
                        if (idxNotaDel >= 0 && idxNotaDel < notasEstudiantes.get(idxEstDel).size()) {
                            notasEstudiantes.get(idxEstDel).remove(idxNotaDel);
                            System.out.println("Nota eliminada.");
                        } else {
                            System.out.println("Número de nota inválido.");
                        }
                    }
                    break;

                case 5:
                    // Mostrar todas las notas
                    if (estudiantes.isEmpty()) {
                        System.out.println("No hay estudiantes registrados.");
                    } else {
                        for (int i = 0; i < estudiantes.size(); i++) {
                            System.out.println(estudiantes.get(i) + ": " + notasEstudiantes.get(i));
                        }
                    }
                    break;

                case 6:
                    // Calcular promedio y estado
                    if (estudiantes.isEmpty()) {
                        System.out.println("No hay estudiantes registrados.");
                    } else {
                        for (int i = 0; i < estudiantes.size(); i++) {
                            double promedio = calcularPromedio(notasEstudiantes.get(i));
                            String estado = promedio >= 6.0 ? "Aprobado ✅" : "Reprobado ❌";
                            System.out.println(estudiantes.get(i) + " - Promedio: " + promedio + " - " + estado);
                        }
                    }
                    break;

                case 7:
                    salir = true;
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida.");

            }


        }
        sc.close();


    }
    public static void mostrarEstudiantes(ArrayList<String> estudiantes) {
        for (int i = 0; i < estudiantes.size(); i++) {
            System.out.println((i + 1) + ". " + estudiantes.get(i));
        }
    }
    public static void mostrarNotas(int idx, ArrayList<String> estudiantes, ArrayList<ArrayList<Double>> notas) {
        System.out.println("Notas de " + estudiantes.get(idx) + ":");
        for (int i = 0; i < notas.get(idx).size(); i++) {
            System.out.println((i + 1) + ". " + notas.get(idx).get(i));
        }
    }
    public static double calcularPromedio(ArrayList<Double> lista) {
        if (lista.isEmpty()) return 0;
        double suma = 0;
        for (double n : lista) {
            suma += n;
        }
        return suma / lista.size();
    }

}
