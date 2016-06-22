package com.example.libprint;
/**
 * Write a description of class PairNumber here.
 *
 * @author (Abdurohman)
 * @version (14 June 2016)
 */

import java.util.Scanner;

public class testtest
{
    static Scanner scan = new Scanner(System.in);
    static int jumlah;
    static int counter = 0;
    static int checker = 0;
    static int checker2;
    static boolean found = false;

    public static void main(String[] args){
        System.out.println("Masukan angka jumlah angka");
        jumlah = scan.nextInt();                                                                //Banyaknya angka
        int[] angka = new int[jumlah];                                                          //Array dengan ukuran sesuai banyaknya angka yang diinput user
        System.out.println("Masukan angka sebanyak " + jumlah + " Kali");
        while(jumlah != counter){                                                               //Input element angka sebanyak yang telah user tentukan sebelumnya
            angka[counter] = scan.nextInt();
            counter++;
        }

        for(counter = 0; counter < jumlah - 1; counter++){                                      /*Loop untuk mencari paired numbers pada array.Pengurangan 1 untuk
                                                                                                 menghindari array out of bound karena ada penjumlahan 1 index di bawah*/
            if(angka[counter] == angka[counter+1] && counter < jumlah - 2){                     //Jika hanya ada 2 angka sama berdekatan
                if(angka[counter] == angka[counter+2]){                                         //Jika ada 3 angka sama
                    checker2 = 0;                                                               //Sebagai penambah index array
                    for(checker = counter + 2; checker < jumlah - 1; checker++){                //Loop untuk memeriksa apakah ada lebih dari 3 angka sama
                        if(angka[counter + 2] == angka[counter + 2 + checker2]){                //Jika lebih dari 3 angka sama
                            checker2++;                                                         //Cek angka selanjutnya
                        }
                        else{
                            checker = jumlah;                                                   //Jika tidak ada maka keluar loop karena checker tidak memenuhi syarat
                            counter += checker2;                                                //Counter diset sesuai aktivitas terbaru
                        }
                    }
                }
                else{
                    System.out.println("There is pair number " + angka[counter] + " on index[" + counter + "] dan index[" + (counter+1) + "]");
                    found = true;                                                               //Tanda bahwa ada pair number ditemukan
                }
            }
            else if(angka[counter] == angka[counter+1] && angka[counter] != angka[counter-1] && counter < jumlah - 1){      //Jika ada pair number pada index akhir untuk menghindari
                //array out of bounds jadi tidak dicek selanjutnya
                System.out.println("There is pair number " + angka[counter] + " on index[" + counter + "] dan index[" + (counter+1) + "]");
                found = true;
            }
            else{
                //Do nothing
            }
        }

        if(found == false){                                                                     //Jika tidak ada pair number ditemukan
            System.out.println("No pair number found");
        }
    }
}