public class MasterMind {
    public static void main(String[] args) {
        final char[] COLORES = new char[]{'r', 'v', 'a', 'm', 'n', 'g'};
        int numeroTotalDatos = 0;
        int verificacionColor = 0;
        int contadorFilas = 0;
        int contadorColumnas = 0;
        boolean comienza = true;
        boolean repetidos = false;
        boolean colorCorrecto = true;
        int contadorColoresRepetidos = 0;
        final char[][] COMBINACIONES_PROPUESTAS = new char[][]{
                {'v', 'n', 'a', 'g'},
                {'n', 'v', 'm', 'r'},
                {'g', 'v', 'm', 'n'},
                {'r', 'v', 'm', 'n'},
                {'r', 'v', 'm', 'g'},
                {'r', 'v', 'm', 'a'}};
        final char[] COMBINACION_SECRETA = new char[]{'r', 'v', 'm', 'a'};
        final char[][] RESULTADOS = new char[][]{
                {'b', 'b', '-', '-'},
                {'b', 'n', 'n', '-'},
                {'n', 'n', 'b', 'n'},
                {'n', 'n', 'n', '-'},
                {'n', 'n', 'n', '-'},
                {'n', 'n', 'n', 'n'}};
        char[][] resultados = new char[COMBINACIONES_PROPUESTAS.length][COMBINACIONES_PROPUESTAS[0].length];
        int posicion = 0, contadorNegroPropuesta = 0, contadorBlancoPropuesta = 0, contadorNadaPropuesta = 0;
        int contadorNegroResultado = 0, contadorBlancoResultado = 0, contadorNadaResultado = 0;
//metemos primero los requisitos de los colores y que no se pueden repetir:

        if (COMBINACIONES_PROPUESTAS.length == RESULTADOS.length && COMBINACIONES_PROPUESTAS.length <= 10) {
            for (int i = 0; i < COMBINACIONES_PROPUESTAS.length; i++) {
                int j = 0;
                repetidos = false;
                while (j < COMBINACIONES_PROPUESTAS[i].length && repetidos == false) {
                    int k = j + 1;
                    contadorColoresRepetidos = 0;
                    while (contadorColoresRepetidos == 0 && k < COMBINACIONES_PROPUESTAS[i].length) {
                        if (COMBINACIONES_PROPUESTAS[i][j] == COMBINACIONES_PROPUESTAS[i][k]) {
                            contadorColoresRepetidos++;
                            repetidos = true;
                            System.out.println("En el intento numero " + (i + 1) + ", el color " + COMBINACIONES_PROPUESTAS[i][j] + ", esta repetido.");
                        } else {
                            k++;
                        }
                    }
                    j++;
                }
                j = 0;
                colorCorrecto = true;
                while (j < COMBINACIONES_PROPUESTAS[i].length && colorCorrecto) {
                    verificacionColor = 0;
                    int k = 0;
                    while (k < COLORES.length && verificacionColor == 0) {
                        if (COMBINACIONES_PROPUESTAS[i][j] == COLORES[k]) {
                            verificacionColor++;
                        } else if (verificacionColor == 0 && k == COLORES.length - 1) {
                            System.out.println("El color " + COMBINACIONES_PROPUESTAS[i][j] + " de la fila " + i +
                                    " es un color que no se puede usar, los colores que se pueden usar son: " +
                                    "rojo (r), verde(v), azul (a), morado (m), naranja (n) y gris (g)");
                            colorCorrecto = false;
                        }
                        k++;
                    }
                    j++;
                }
                if (colorCorrecto && !repetidos) {
                    contadorBlancoPropuesta = 0;
                    contadorNegroPropuesta = 0;
                    posicion = 0;
                    for (j = 0; j < COMBINACIONES_PROPUESTAS[i].length; j++) {
                        int k = 0;
                        boolean encontrado = false;
                        while (k < COMBINACION_SECRETA.length && !encontrado) {
                            if (COMBINACIONES_PROPUESTAS[i][j] == COMBINACION_SECRETA[k] && j == k) {
                                contadorNegroPropuesta++;
                                resultados[i][posicion] = 'n';
                                posicion++;
                                encontrado = true;
                            } else if (COMBINACIONES_PROPUESTAS[i][j] == COMBINACION_SECRETA[k]) {
                                contadorBlancoPropuesta++;
                                resultados[i][posicion] = 'b';
                                posicion++;
                                encontrado = true;
                            } else {
                                k++;
                            }
                        }
                    }
                    for (j = posicion; j < COMBINACION_SECRETA.length; j++) {
                        resultados[i][posicion] = '-';
                        posicion++;
                    }
                    contadorNegroResultado = 0;
                    contadorBlancoResultado = 0;

                    for (j = 0; j < RESULTADOS[i].length; j++) {
                        if (RESULTADOS[i][j] == 'n') {
                            contadorNegroResultado++;
                        } else if (RESULTADOS[i][j] == 'b') {
                            contadorBlancoResultado++;
                        }
                    }
                    if (contadorBlancoResultado == contadorBlancoPropuesta &&
                            contadorNegroResultado == contadorNegroPropuesta) {
                        System.out.println("Lo propuesto coincide con el resultado, el juego no ha tenido fallos en el intento " + (i + 1));
                    } else {
                        System.out.print("Error en el resultado en el intento " + (i + 1));
                        if (contadorNegroResultado != contadorNegroPropuesta) {
                            System.out.println(". El recuento del resultado de negros es " + contadorNegroResultado + " pero deberia ser de " + contadorNegroPropuesta);
                            System.out.print("El resultado deberia ser [");
                            for (j = 0; j < resultados[i].length; j++) {
                                if (j == resultados[i].length - 1) {
                                    System.out.print(resultados[i][j]);
                                } else {
                                    System.out.print(resultados[i][j] + ",");
                                }
                            }
                            System.out.print("]");
                            System.out.print(" y no deberia ser [");
                            for (j = 0; j < RESULTADOS[i].length; j++) {
                                if (j == RESULTADOS[i].length - 1) {
                                    System.out.print(RESULTADOS[i][j]);
                                } else {
                                    System.out.print(RESULTADOS[i][j] + ",");
                                }
                            }
                            System.out.println("]");
                        } else if (contadorBlancoResultado != contadorBlancoPropuesta) {
                            System.out.println(". El recuento del resultado de blancos es " + contadorBlancoResultado + " pero deberia ser de " + contadorBlancoPropuesta);
                            System.out.print("El resultado deberia ser [");
                            for (j = 0; j < resultados[i].length; j++) {
                                if (j == resultados[i].length - 1) {
                                    System.out.print(resultados[i][j]);
                                } else {
                                    System.out.print(resultados[i][j] + ",");
                                }
                            }
                            System.out.print("]");
                            System.out.print(" y no deberia ser [");
                            for (j = 0; j < RESULTADOS[i].length; j++) {
                                if (j == RESULTADOS[i].length - 1) {
                                    System.out.print(RESULTADOS[i][j]);
                                } else {
                                    System.out.print(RESULTADOS[i][j] + ",");
                                }
                            }
                            System.out.println("]");
                        }
                    }
                }
            }
        } else {
            if (COMBINACIONES_PROPUESTAS.length != RESULTADOS.length) {
                System.out.println("EL numero de soluciones propuestas no coincide con el numero de resultados, no se puede evaluar.");
            } else if (COMBINACIONES_PROPUESTAS.length > 10) {
                System.out.println("El numero de soluciones propuestas supera el total. No se puede evaluar.");
            }
        }
    }
}
