
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlabb;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duyguevrim
 */
public class SunucuIstekYogunlugu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Value value = new Value(0);
        mainAnlikDeger MainAnlikDeger = new mainAnlikDeger(0);
        ArrayList<AnlikAltDeger> anlikaltvaluelist = new ArrayList();
        ArrayList<Thread> SubProducerThreads = new ArrayList();
        ArrayList<Thread> SubConsumerThreads = new ArrayList();
        
        anlikaltvaluelist.add(new AnlikAltDeger(0,500,true));
        anlikaltvaluelist.add(new AnlikAltDeger(0,500,true));
        Thread main_thread_producer = new Thread(new MainProducer(value,MainAnlikDeger));
        Thread main_thread_consumer = new Thread(new MainConsumer(value,MainAnlikDeger));
  //     AnlikAltDeger anlikaltvalue = new AnlikAltDeger(0);
        Thread sub1_producer = new Thread(new SubProducer(value,0, anlikaltvaluelist,anlikaltvaluelist.get(0).SubKapasitesi));
	Thread sub1_consumer = new Thread(new SubConsumer(value,0,anlikaltvaluelist));
      //  AnlikAltDeger anlikaltvalue2 = new AnlikAltDeger(0);
	Thread sub2_producer = new Thread(new SubProducer(value,1,anlikaltvaluelist,anlikaltvaluelist.get(1).SubKapasitesi));
	Thread sub2_consumer = new Thread(new SubConsumer(value,1,anlikaltvaluelist));
        
        SubProducerThreads.add(sub1_producer);
        SubConsumerThreads.add(sub1_consumer);
        
        SubProducerThreads.add(sub2_producer);
        SubConsumerThreads.add(sub2_consumer);
        
        
        
        Thread Sub_Olusturucu = new Thread(new SubOlusturucu(anlikaltvaluelist,SubProducerThreads,SubConsumerThreads,value));
        
        Thread sunucutakip = new Thread(new SunucuTakip(anlikaltvaluelist,SubProducerThreads,SubConsumerThreads,MainAnlikDeger,value));
        sunucutakip.start();
        main_thread_producer.start();
        main_thread_consumer.start();
        sub1_producer.start();
        sub1_consumer.start();
        sub2_producer.start();
        sub2_consumer.start();
        Sub_Olusturucu.start();
        
        //Thread sunucutakip = new Thread(new SunucuTakip(anlikaltvaluelist));
       // sunucutakip.start();

    }
    
}


