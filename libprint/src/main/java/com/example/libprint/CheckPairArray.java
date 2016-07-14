package com.example.libprint;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Akhmad Muntako
 */
public class CheckPairArray {

    public static void main(String[] args) {
        //input digunakan untuk membaca input
        Scanner input = new Scanner(System.in);

        System.out.println("Input:");
        /**
         * @param line digunakan untuk menghimpun string input pada satu baris
         * @param array digunakan untuk memasukan input string angka ke array
         * @param numbers digunakan sebagai wadah dari input angka
         */
        String line = input.nextLine();
        String[] array = line.split(" ");
        int[] numbers = new int[array.length];

        /**
         * Untuk input sebanyak panjang array dilakukan parsing ke integer. Jika
         * bilangan bukan merupakan integer maka akan dilarikan ke exception not
         * a number
         */
        for (int i = 0; i < array.length; i++) {
            try {
                numbers[i] = Integer.parseInt(array[i]);
            } catch (NumberFormatException exception) {
                System.out.println(array[i] + " is not a valid number!");
            }
        }
        System.out.println("Output:");

        /**
         * @param stack digunakan untuk memasukan angka yang sudah di cek
         * @param sama digunakan sebagai flag adanya output angka yang sama.
         * Jika sama == 0, maka tidak terdapat pair
         * @param count digunakan untuk menghitung banyaknya angka yang terdapat
         * pada input
         */
        ArrayList stack = new ArrayList<>();
        int sama = 0;
        int count = 1;

        for (int i = 0; i < array.length; i++) {

            int indexAkhir = 0;
            int awal = numbers[i];

            for (int j = i + 1; j < array.length; j++) {
                if (stack.contains(numbers[j])) {
                    //Tidak melakukan apapun
                } else {
                    int next = numbers[j];
                    if (awal == next) {
                        count++;
                        indexAkhir = j;
                    }
                }
            }
            if (count == 2) {
                stack.add(awal);
                //mencetak keterangan saat ada pair number pada input 
                System.out.println("there is pair of number " + awal + " on index[" + i + "] and index[" + indexAkhir + "]");
                count = 1;
                sama++;
            } else {
                stack.add(awal);
                count = 1;
            }
        }
        if (sama == 0) {
            //mencetak keterangan saat tidak ada pair number
            System.out.println("no pair number found");
        }
    }
}
