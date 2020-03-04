


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
public class MainProducer implements Runnable {
    int kapasite = 10000;
	//int anlikDeger = 0;

	Value value;
	mainAnlikDeger MainAnlikDeger;

	 public MainProducer(Value value,mainAnlikDeger MainAnlikDeger) {
		this.value = value;
		this.MainAnlikDeger=MainAnlikDeger;
	}
         
	public void run() {

		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			synchronized (value) {
				int uretilenDeger = new Random().nextInt(1500)+0;
				
				if(value.deger+uretilenDeger>=kapasite)
				{
					try {
						//anlikDeger=kapasite;
						value.deger = kapasite;	
						MainAnlikDeger.mainAnlikDeger=kapasite;
						System.out.println("main value.deger : "+value.deger);
						value.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					//anlikDeger+=uretilenDeger;
					value.deger+=uretilenDeger;
					MainAnlikDeger.mainAnlikDeger+=uretilenDeger;
					System.out.println("MainAnlikDeger.mainAnlikDeger: "+MainAnlikDeger.mainAnlikDeger);
					value.notify();
				}
				//System.out.println("main value.deger üretti: "+uretilenDeger);
				//System.out.println("main value.deger tüketildi, anlık deger: "+value.deger);
			}

		}

	}

}


