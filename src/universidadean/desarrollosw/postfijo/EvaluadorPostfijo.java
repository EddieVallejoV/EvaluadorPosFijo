/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogotá - Colombia)
 * Departamento de Tecnologías de la Información y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Evaluador de Expresiones Postfijas
 * Fecha: Febrero 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package universidadean.desarrollosw.postfijo;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import static universidadean.desarrollosw.postfijo.Token.*;

/**
 * Esta clase representa una clase que evalúa expresiones en notación polaca o
 * postfija. Por ejemplo: 4 5 +
 */
public class EvaluadorPostfijo {

    /**
     * Realiza la evaluación de la expresión postfijo utilizando una pila
     * @param expresion una lista de elementos con números u operadores
     * @return el resultado de la evaluación de la expresión.
     */
    static Double evaluarPostFija(List<String> expresion) {
        Stack<Double> pila = new Stack<>();
        String dato = expresion.toString();
        String dato2 = dato.replaceAll(",", "").replaceAll("\\[", "").replaceAll("\\]","").replaceAll("\\s","");
        System.out.println("Expresion Ingresada: " + dato);
        System.out.println("Expresion sin caracteres innecesarios:" + dato2);
        for (int i = 0; i<dato2.length(); i++){
            char letra = dato2.charAt(i);
            //System.out.println(letra);
            if (!isOperator(letra)) {
                double num = new Double(letra + "");
                pila.push(num);
                //System.out.println(num);
            }
            else{
                double num2 = (double)pila.pop();
                double num1 = (double)pila.pop();
                double num3 = operacion(letra, num1, num2);
                pila.push(num3);

                //System.out.println("son operadores: " + letra);
            }

        }


        //System.out.println(dato);
        // TODO: Realiza la evaluación de la expresión en formato postfijo

        return pila.peek();
    }

    private static int operacion(char letra, double num1, double num2) {
        if(letra == '*') return (int) (num1 * num2);
        if(letra == '+') return (int) (num1 + num2);
        if(letra == '-') return (int) (num1 - num2);
        if(letra == '/') return (int) (num1 / num2);
        if(letra == '%') return (int) (num1 % num2);
        return 0;
    }

    private static boolean isOperator(char letra) {
        if (letra == '+' || letra == '-' || letra == '*' || letra == '/' || letra == '%'){
            return true;
        }
        return false;
    }

    /**
     * Programa principal
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("> ");
        String linea = teclado.nextLine();

        try {
            List<String> expresion = dividir(linea);
            System.out.println(evaluarPostFija(expresion));
        }
        catch (Exception e) {
            System.err.printf("Error grave en la expresión: %s", e.getMessage());
        }

    }
}
