

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
public class SubConsumer implements Runnable {
    Value value;
	int SubNumber;
	//public int SubAnlikDeger;
        ArrayList<AnlikAltDeger> anlikaltvaluelist;
	public SubConsumer(Value value, int subNumber, ArrayList<AnlikAltDeger> anlikaltvaluelist) {
		this.value = value;
		this.SubNumber = subNumber;
		this.anlikaltvaluelist = anlikaltvaluelist;
	}
	public void run() {

		while (true) {

			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				return;
				//e.printStackTrace();
			}
			synchronized (value) {
				int SubTuketilenDeger = new Random().nextInt(100) + 1;

				if ((anlikaltvaluelist.get(SubNumber).SubAnlikDeger - SubTuketilenDeger) <= 0) {
					
                    /*anlikaltvaluelist.get(SubNumber).SubAnlikDeger = 0;
					if (SubNumber > 2) {
						return;
					}*/
					try {
						value.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					anlikaltvaluelist.get(SubNumber).SubAnlikDeger= anlikaltvaluelist.get(SubNumber).SubAnlikDeger-SubTuketilenDeger;
                    System.out.println("Sub Threadin"+SubNumber+" tukettigi :"+SubTuketilenDeger);
					//System.out.println("SubThread Değerleri tüketti, subanlikDeger: "+anlikaltvaluelist.get(SubNumber).SubAnlikDeger);
					value.notify();
				}
			}
                       
                      if(anlikaltvaluelist.get(SubNumber).durdur == false)
                        {
                            System.out.println("SubConsumerrrrr"+SubNumber+" silindi");
                            break;
                        }

		}
        return;

	}

}
