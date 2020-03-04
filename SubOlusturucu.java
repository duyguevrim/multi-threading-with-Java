


package yazlabb;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SubOlusturucu implements Runnable {
    
     ArrayList<AnlikAltDeger> anlikaltvaluelist;
     ArrayList<Thread> SubProducerThreads;
     ArrayList<Thread> SubConsumerThreads;
     Value valueKilidi;
    public SubOlusturucu(ArrayList<AnlikAltDeger> anlikaltvaluelist, ArrayList<Thread> SubProducerThreads, ArrayList<Thread> SubConsumerThreads,Value value) {
        this.anlikaltvaluelist = anlikaltvaluelist;
        this.SubProducerThreads = SubProducerThreads;
        this.SubConsumerThreads = SubConsumerThreads;
        valueKilidi=value;
    }
    
    int esikDegeri=350;
    int yarim;
    int threadName;
    //int esikDegeri=350;
    @Override
    public void run() {

        while(true){
            synchronized(valueKilidi)
            {
                for (int i = 0; i < anlikaltvaluelist.size(); i++) {
                   
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SubOlusturucu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   // System.out.println("anlikaltvaluelist : "+anlikaltvaluelist.size());
                   // System.out.println("anlikaltvaluelist.get(i).SubAnlikDeger: "+anlikaltvaluelist.get(i).SubAnlikDeger);
                    
                    //(anlikaltvaluelist.get(i).SubKapasitesi*7)/10;
                    if(anlikaltvaluelist.get(i).SubAnlikDeger >= esikDegeri)
                    {
                        System.out.println("Sub"+(i+1)+" in kapasitesi %70 i asti");
                        System.out.println("YENİ SUBSERVER ÜRETİLDİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİ");
                        yarim = anlikaltvaluelist.get(i).SubAnlikDeger /2;
                        anlikaltvaluelist.get(i).SubAnlikDeger = anlikaltvaluelist.get(i).SubAnlikDeger /2;
                        anlikaltvaluelist.add(new AnlikAltDeger(yarim,500,true));
                       
                       
                        threadName= anlikaltvaluelist.size();
                        SubProducerThreads.add(new Thread(new SubProducer(valueKilidi, (anlikaltvaluelist.size()-1),anlikaltvaluelist,anlikaltvaluelist.get(anlikaltvaluelist.size()-1).SubKapasitesi)));
                        SubConsumerThreads.add(new Thread(new SubConsumer(valueKilidi, (anlikaltvaluelist.size()-1),anlikaltvaluelist)));
                        SubProducerThreads.get(SubProducerThreads.size()-1).setName("SubProducer"+Integer.toString(threadName));
                        SubConsumerThreads.get(SubConsumerThreads.size()-1).setName("SubConsumer"+Integer.toString(threadName));
                        SubProducerThreads.get(SubProducerThreads.size()-1).start();
                        SubConsumerThreads.get(SubConsumerThreads.size()-1).start();
                        System.out.println("YENİ OLU�?TURULAN THREADİN ANLIK DE�?ERİ............................"+anlikaltvaluelist.get(anlikaltvaluelist.size()-1).SubAnlikDeger);
                        System.out.println(Thread.activeCount());
                    }
                    //System.out.println("i indeksi : "+i);
                    //******************i>=2 yi silince ilk ba�taki iki sub sevrer� siliyor**************G�STERRRR
                    if(anlikaltvaluelist.get(i).SubAnlikDeger<=0 && i>=2)
                    {
                         
                       //System.out.println("SubThread"+i+" SİLİNDİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİ");
                       //anlikaltvaluelist.remove(i);
                       anlikaltvaluelist.get(i).durdur=false;
                       try {
   						Thread.sleep(25);
   					} catch (InterruptedException e) {
   						// TODO Auto-generated catch block
   						e.printStackTrace();
   					}
                       anlikaltvaluelist.remove(i);
                       SubProducerThreads.get(i).interrupt();
                       SubConsumerThreads.get(i).interrupt();
                       SubProducerThreads.remove(i);
                       SubConsumerThreads.remove(i);
                       System.out.println(Thread.activeCount());
                    }
                    
                    
                }
                
            }
        }

    }
    
}


