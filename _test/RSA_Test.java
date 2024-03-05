package _test;
import gui.GUI;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;

import file.FileReaderWriter;
    
/**
 * Die Klasse RSA_fertig passt zum Anhang E 
 * des Buches "Simon Singh: Codes" (S. 282-283) 
 * @author akaibel
 */
public class RSA_Test implements Serializable{
   private final BigInteger b0  = new BigInteger("0");
   private final BigInteger b1  = new BigInteger("1");
   private final BigInteger b2  = new BigInteger("2");
   
   private BigInteger p = new BigInteger("17");
   private BigInteger q = new BigInteger("11");
   private BigInteger N;
   private BigInteger phi;
   private BigInteger e = new BigInteger("65537");
   private BigInteger M = new BigInteger("88");
   private BigInteger C;
   private BigInteger d;
   private BigInteger Mentschluesselt;
   
   private final Random random = new Random();

   /**
    * erzeugt eine zufaellige Zahl mit stellenzahl Stellen und 
    * sucht von da ausgehend die naechstgroesste Primzahl.
    * fuer den Primzahltest wird isProbablePrime(certainty) verwendet. 
    * @param stellenzahl
    * @param certainty
    * @return
    */
   public BigInteger findePrimzahl(int stellenzahl) {
		// TODO implementieren!
	   String r = "";
      for(int i =0;i<stellenzahl;i++){
         r += random.nextInt(10); 
      }

		return new BigInteger(r).nextProbablePrime();
   }
   
   public void p_und_q_finden(int stellenzahl){
	   //TODO
	   p = findePrimzahl(stellenzahl);
      q = findePrimzahl(stellenzahl);
   }
   
   public void berechneN(){
	   N = p.multiply(q);
   }
   
   public void berechnePhi(){
	   phi = (p.subtract(b1)).multiply(q.subtract(b1));
   }
   
   public void berechneC(){
	   C = M.modPow(e, N);
   }
   
   public void berechneD(){
	   d = e.modInverse(phi); 
   }
   
   public void berechneMentschluesselt(){
	   Mentschluesselt = C.modPow(d, N);
   }
      
   public String zufallsZifferString(){
	   int ziffer = random.nextInt(10);
	   return ""+ziffer;
   }
   
   /**
    * speichert das Objekt zur spaeteren Verwendung!
    */
   public void save(){
	   FileReaderWriter.saveSerialized(this, "RSA_Test.sibi");
   }
   
   public static void main(String[] args) {
	   RSA_Test rsa_test = (RSA_Test) FileReaderWriter.readSerialized("RSA_Test.sibi");
	   if(rsa_test == null){
		   rsa_test = new RSA_Test();
	   }
	   new GUI(rsa_test);
   }

}