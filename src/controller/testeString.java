package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javafx.util.converter.LocalDateTimeStringConverter;

/**
 *
 * @author Idelfonso
 */
public class testeString {

    public static void main(String[] args) {

//        String frase = "12.123.123"; //
//        String nome = "Cristiano Idelfonso da Silva "; // 29 letras e espaços
//
//        System.out.println(frase + nome);
//        System.out.println(frase.length() + nome.length()); //48
//        System.out.println(frase.length() +" | "+ nome.length()); //19 | 29
//        System.out.println(frase.trim().length() +" | "+ nome.trim().length()); //18 | 28
//        
//        System.out.println(frase.replace(" ", "").length() +" | "+ nome.replace(" ", "").length()); //15 | 25  
            

        LocalDateTime dt = LocalDateTime.now();
        String dataAtual = dt.getDayOfMonth() + "/" + dt.getMonth() + "/" + dt.getYear();
        String horaAtual = dt.getHour() + ":" + dt.getMinute();
        System.out.println(dataAtual);
        System.out.println(horaAtual);  

        
    }

}