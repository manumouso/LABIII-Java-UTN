package edu.utn.validator;

public class NetworkValidator {

    public static boolean validPort(int port){

        return port >= 1500 && port <=65000;
    }

    public static boolean validIP(String IP){

        char[] ip=IP.toCharArray();
        int i=0;
        int quantityDigits = 0;
        int quantitySublots = 0;

        while(i<ip.length){
            quantityDigits = 0;
            while(i<ip.length && ip[i] != '.'){
                if(ip[i] >= '0' && ip[i] <= '9'){
                    quantityDigits++;
                }else{
                    return false;
                }
                i++;
            }
            if(quantityDigits > 3 || quantityDigits == 0) {
                return false;
            }
            quantitySublots++;
            i++;
        }

        return quantitySublots == 4;
    }

}
