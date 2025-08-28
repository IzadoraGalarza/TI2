package Ex1;
import java.util.Scanner;

public class Ex1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		
		//variaveis
		int num1=0;
		int num2=0;
		int soma=0;
		
		//entrada de dados
		System.out.printf("Entre com um valor inteiro: ");
		num1=sc.nextInt();
		System.out.printf("Entre com outro valor inteiro: ");
		num2=sc.nextInt();
		
		//soma de variaveis
		soma= num1 + num2;
		
		//mostrar resultado
		System.out.printf("Soma = %d", soma);
		sc.close();
	}

}