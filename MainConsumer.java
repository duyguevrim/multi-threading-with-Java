
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlabb;

import java.util.Random;

/**
 *
 * @author duyguevrim
 */
public class MainConsumer implements Runnable {
    Value value;
    mainAnlikDeger MainAnlikDeger;
	 public MainConsumer(Value value,mainAnlikDeger MainAnlikDeger) {
		this.value = value;
		this.MainAnlikDeger=MainAnlikDeger;
         }

	@Override
	public void run() {

		while (true) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			synchronized (value) {
				int tuketilenDeger = new Random().nextInt(50)+1;
				
				if(value.deger-tuketilenDeger<=0)
				{
					try {
						//anlikDeger=kapasite;
						value.deger = 0;	
						MainAnlikDeger.mainAnlikDeger=0;
						System.out.println("main value.deger tüketildi: "+value.deger);
						value.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					//anlikDeger+=tuketilenDeger;
					value.deger-=tuketilenDeger;
					MainAnlikDeger.mainAnlikDeger-=tuketilenDeger;
					value.notify();
				}
			//	System.out.println("main value.deger tüketti: "+tuketilenDeger);
			//	System.out.println("main value.deger tüketildi, anlık deger: "+value.deger);
			}

		}


	}

}


