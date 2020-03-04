


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlabb;
import java.awt.*;
import javax.swing.*;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duyguevrim
 */
public class SunucuTakip implements Runnable {

	ArrayList<AnlikAltDeger> anlikaltvaluelist;
	ArrayList<Thread> SubProducerThreads;
	ArrayList<Thread> SubConsumerThreads;
	Value value;
	mainAnlikDeger MainAnlikDeger;
	
	JFrame jframe =new JFrame();
	JPanel jpanel = new JPanel();
	
	Label mainLabel= new Label();
	JProgressBar mainServerBar = new JProgressBar();
	
	ArrayList<JProgressBar> SubServerBarlari = new ArrayList();
	ArrayList<Label> SubLabels = new ArrayList();
	
	JProgressBar constServerBar1 = new JProgressBar();
	JProgressBar constServerBar2 = new JProgressBar();
	Label Sublabel1 = new Label();
	Label Sublabel2 = new Label();
	

    public SunucuTakip(ArrayList<AnlikAltDeger> anlikaltvaluelist, ArrayList<Thread> subProducerThreads,ArrayList<Thread> subConsumerThreads,mainAnlikDeger MainAnlikDeger, Value value) {
		this.anlikaltvaluelist = anlikaltvaluelist;
		SubProducerThreads = subProducerThreads;
		SubConsumerThreads = subConsumerThreads;
		this.value=value;
		this.MainAnlikDeger = MainAnlikDeger;
	}


	@Override
    public void run() {
		 jframe = new JFrame();
         
	       mainServerBar.setBounds(250, 250, 250, 250);
	       mainServerBar.setValue(0); 
	       mainServerBar.setStringPainted(true); 
	       mainLabel.setText("Main Server");
	       mainLabel.setFont(new Font(mainLabel.getName(), Font.PLAIN, 15));
	      
	       constServerBar1.setBounds(250, 250, 250, 250);
	       constServerBar1.setValue(0);
	       constServerBar1.setStringPainted(true);
	       Sublabel1.setText("Sub Server1");
	       Sublabel1.setFont(new Font(Sublabel1.getName(), Font.PLAIN, 15));
	 
	   
	       constServerBar2.setBounds(250, 250, 250, 250);
	       constServerBar2.setValue(0);
	       constServerBar2.setStringPainted(true);
	       
	       
	       Sublabel2.setText("Sub Server2");
	       Sublabel2.setFont(new Font(Sublabel2.getName(), Font.PLAIN, 15));
	       
	       SubServerBarlari.add(constServerBar1);
	       SubServerBarlari.add(constServerBar2);
	       
	       SubLabels.add(Sublabel1);
	       SubLabels.add(Sublabel2);
	       
	       jframe.add(jpanel);
	       jpanel.add(mainLabel);
	       jpanel.add(mainServerBar);
	       jpanel.add(Sublabel1);
	       jpanel.add(constServerBar1);
	       jpanel.add(Sublabel2);
	       jpanel.add(constServerBar2);
	       jframe.setSize(350, 1000); 
	       jframe.setVisible(true);
	       

		 
		 
		 /*
		 mainServerBar.setBounds(250,250,250,250);
		 mainServerBar.setValue(0);
		 mainServerBar.setStringPainted(true);
		 mainLabel.setText("Main Server");
		 jpanel.add(mainLabel);
		 jpanel.add(mainServerBar);
		 
		 SubServerBarlari.add(new JProgressBar());
		 SubServerBarlari.get(0).setBounds(60,215,80,30);
		 SubServerBarlari.get(0).setValue(0);
		 SubServerBarlari.get(0).setStringPainted(true);
		 jpanel.add(SubServerBarlari.get(0));
		 SubLabels.add(new Label());
		 //SubLabels.get(0).add(arg0);
		 SubLabels.get(0).setText("Sub Server 1");
		 jpanel.add(SubServerBarlari.get(0));
		 SubServerBarlari.add(new JProgressBar());
		 SubServerBarlari.get(1).setBounds(60,220,80,30);
		 SubServerBarlari.get(1).setValue(0);
		 SubServerBarlari.get(1).setStringPainted(true);
		 jpanel.add(SubServerBarlari.get(1));
		 SubLabels.add(new Label());
		 
		 SubLabels.get(1).setText("Sub Server 2");
		 jframe.add(jpanel);
		 jframe.add(mainLabel);
		 jframe.add(SubLabels.get(0));
		 jframe.add(SubLabels.get(1));
		 jframe.setSize(2000,2000);
		 jframe.setVisible(true); */
		 
		while(true)
		{
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    mainServerBar.setValue(MainAnlikDeger.mainAnlikDeger/100);
		    jpanel.revalidate();
		    jpanel.repaint();
			System.out.println("Main server yüzdesi %"+(MainAnlikDeger.mainAnlikDeger)/100);
			//jpanel.add(mainServerBar);
			
			SubServerBarlari.get(0).setValue(anlikaltvaluelist.get(0).SubAnlikDeger/5);
			jpanel.revalidate();
			jpanel.repaint();
			
			SubServerBarlari.get(1).setValue(anlikaltvaluelist.get(1).SubAnlikDeger/5);
			jpanel.revalidate();
			jpanel.repaint();
			
			 for (int i = 2; i <anlikaltvaluelist.size(); i++) {
				 SubServerBarlari.add(new JProgressBar());
				 SubServerBarlari.get(SubServerBarlari.size()-1).setBounds(60,220+30*i,80,30);
				 SubServerBarlari.get(SubServerBarlari.size()-1).setStringPainted(true);
				 SubServerBarlari.get(i).setValue((anlikaltvaluelist.get(i).SubAnlikDeger/5));
				 jpanel.add(SubServerBarlari.get(SubServerBarlari.size()-1));
				 SubLabels.add(new Label());
				 if(anlikaltvaluelist.size()==SubProducerThreads.size() && SubProducerThreads.size()==SubConsumerThreads.size())
				 {
					 System.out.println("SubServer"+i+" yüzdesi: %"+(anlikaltvaluelist.get(i).SubAnlikDeger/5));
					 SubServerBarlari.get(i).setValue((anlikaltvaluelist.get(i).SubAnlikDeger/5));
					 SubLabels.get(i).setText("Sub Server"+i+" ");
					 jpanel.revalidate();
						jpanel.repaint();
						
				 }
				 
				 jpanel.revalidate();
			     jpanel.repaint();
			}
			 jpanel.revalidate();
		     jpanel.repaint();
		}
		
		
		
		//System.out.println("Anlýk toplam alt thread sayýsý: "+anlikaltvaluelist.size());
		//System.out.println("SubThread");
    	
    }


    
}

