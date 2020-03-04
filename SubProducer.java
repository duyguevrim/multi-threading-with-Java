
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlabb;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author duyguevrim
 */
public class SubProducer implements Runnable{
    
	Value value;
	int subNumber;
	//public int SubAnlikDeger;
        int SubKapasitesi;
        ArrayList<AnlikAltDeger> anlikaltvaluelist;

        
	public SubProducer(Value value, int subNumber, ArrayList<AnlikAltDeger> anlikaltvaluelist, int SubKapasitesi) {
		this.value = value;
		this.subNumber = subNumber;
		this.anlikaltvaluelist=anlikaltvaluelist;
        this.SubKapasitesi=SubKapasitesi;
              
	}
        public int sub_thread_sayisi=subNumber;


	@Override
	public void run() {
		
		while(true)
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				return;
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
			synchronized (value) {
				int subUretilenDeger = new Random().nextInt(300)+1;
				
				if(value.deger-subUretilenDeger>=0) {
					//genel degeri azaltmak icin sub in urettigi degeri genel degerden c�kard�k
					value.deger= value.deger-subUretilenDeger;
				}
				if(value.deger-subUretilenDeger<0) {
					//eksiye d��mesin diye
					value.deger=0;
				}
			/*	//baslangictaki kontrol icin, suan calismiyor
				if((anlikaltvaluelist.get(subNumber).SubAnlikDeger+subUretilenDeger)>=500) {
                                    System.out.println("Sub"+subNumber+"Doldu........");
					try {
						value.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}*/
                               /* if((SubAnlikDeger+subUretilenDeger)>=350){
                                    System.out.println("ESKİ SUB THREAD SAYISI:"+subNumber);
                                    createsubthread(value,subNumber,SubAnlikDeger);

                                }*/
				
				anlikaltvaluelist.get(subNumber).SubAnlikDeger+=subUretilenDeger;
                                System.out.println("Sub"+subNumber+" mainden aldığı değer : "+subUretilenDeger);
				System.out.println("SUBTHREAD"+subNumber+" ANLIK DE�?ERİ : "+anlikaltvaluelist.get(subNumber).SubAnlikDeger);
				value.notify();
			}
                        if(anlikaltvaluelist.get(subNumber).durdur == false)
                        {
                            
                            System.out.println("SubProducerrrr"+subNumber+" SİLİNDİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİ");
                            
                            break;
                        }
                 
		}
		return;
	}
	
	

     
   /* public void createsubthread(Value value,int sub_thread_sayisi, int SubAnlikDeger){
        sub_thread_sayisi += sub_thread_sayisi;
        Thread new_sub_producer = new Thread(new SubProducer(value,sub_thread_sayisi,SubAnlikDeger/2));
        Thread new_sub_consumer = new Thread(new SubConsumer(value,sub_thread_sayisi,SubAnlikDeger/2));
        new_sub_producer.start();
        new_sub_consumer.start();
        System.out.println("YENİ SUB THREAD OLU�?TU");
        System.out.println("YENİ SUB THREAD SAYISI:"+sub_thread_sayisi);

    } */
	
}

