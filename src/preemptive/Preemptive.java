
package preemptive;

/**
 *
 * @author faizan
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;


public class Preemptive {
    
    
// ----------------------- FUNCTIONALITY DEFINED FOR SRF -------------------------------------
     
  public static void SRF(Process[] array, int n, double completionTime, Process store, String ganttchart)    
    {
        Process[] srfArray=new Process[n];
        ArrayList <Process> Queue = new ArrayList(n);
        for(int i=0;i<n;i++)
        {
            srfArray[i] = new Process();
            srfArray[i].arrival_Time=array[i].arrival_Time;
            srfArray[i].brust_Time=array[i].brust_Time;
            srfArray[i].priority=array[i].priority;
            srfArray[i].process_Name=array[i].process_Name;
            srfArray[i].waiting_Time=array[i].waiting_Time;
        }
        srfArray=sort(srfArray);
        int count2=0;
        while(count2<n)
        {
            while(true)
            {
                if(count2<n && completionTime>=srfArray[count2].arrival_Time )
                { 
                   Queue.add(srfArray[count2]);
                   count2++;
                }
                else
                {
                    break;
                }
            }
            if(store!=null)
            {
                Queue.add(store);
                store=null;
            }
            if(!Queue.isEmpty())
            {
                Collections.sort(Queue,new ComparingBursttTime());
                Process temp = Queue.remove(0);
                ganttchart=ganttchart+temp.process_Name;
                temp.brust_Time=temp.brust_Time-1;
                temp.waiting_Time=temp.waiting_Time+completionTime-temp.arrival_Time;
                completionTime=completionTime+1;
                temp.arrival_Time=completionTime;
                if(temp.brust_Time>0)
                    store=temp;
            }
            else
            {
                completionTime=srfArray[count2].arrival_Time;
            }
        }
        while(true)
        {
            if(store!=null)
            {
                Queue.add(store);
                store=null;
            }
            if(Queue.isEmpty())
                break;
            Collections.sort(Queue,new ComparingBursttTime());
            Process temp = Queue.remove(0);
            ganttchart=ganttchart+temp.process_Name;
            temp.brust_Time=temp.brust_Time-1;
            temp.waiting_Time=temp.waiting_Time+completionTime-temp.arrival_Time;
            completionTime=completionTime+1;
            temp.arrival_Time=completionTime;
            if(temp.brust_Time>0)
                store=temp;
        }
        double sum2=0;
        for(int i=0;i<n;i++)
        {
            sum2=sum2+srfArray[i].waiting_Time;
        }
        System.out.println("Avarage waiting time is "+(sum2/n));
        System.out.println("GANTT CHART IS : "+ganttchart);
                         
    }
  
  // ----------------------- FUNCYIONALITY DEFINED FOR PRIORITY SCHEDULING -------------------------------------
    
    public static void priorityPreemptive(Process[] array, int n, double completionTime, Process store, String ganttchart)  
    { 
        Process[] priorityArray=new Process[n];
        ArrayList <Process> Queue = new ArrayList(n);
        for(int i=0;i<n;i++)
        {
            priorityArray[i] = new Process();
            priorityArray[i].arrival_Time=array[i].arrival_Time;
            priorityArray[i].brust_Time=array[i].brust_Time;
            priorityArray[i].priority=array[i].priority;
            priorityArray[i].process_Name=array[i].process_Name;
            priorityArray[i].waiting_Time=array[i].waiting_Time;
        }
        priorityArray=sort(priorityArray);
        int count3=0;
        while(count3<n)
        {
            while(true)
            {
                if(count3<n && completionTime>=priorityArray[count3].arrival_Time )
                { 
                   Queue.add(priorityArray[count3]);
                   count3++;
                }
                else
                {
                    break;
                }
            }
            if(store!=null)
            {
                Queue.add(store);
                store=null;
            }
            if(!Queue.isEmpty())
            {
                Collections.sort(Queue,new ComparingPriority());
                Process temp = Queue.remove(0);
                ganttchart=ganttchart+temp.process_Name;
                temp.brust_Time=temp.brust_Time-1;
                temp.waiting_Time=temp.waiting_Time+completionTime-temp.arrival_Time;
                completionTime=completionTime+1;
                temp.arrival_Time=completionTime;
                if(temp.brust_Time>0)
                    store=temp;
            }
            else
            {
                completionTime=priorityArray[count3].arrival_Time;
            }
        }
        while(true)
        {
            if(store!=null)
            {
                Queue.add(store);
                store=null;
            }
            if(Queue.isEmpty())
                break;
            Collections.sort(Queue,new ComparingPriority());
            Process temp = Queue.remove(0);
            ganttchart=ganttchart+temp.process_Name;
            temp.brust_Time=temp.brust_Time-1;
            temp.waiting_Time=temp.waiting_Time+completionTime-temp.arrival_Time;
            completionTime=completionTime+1;
            temp.arrival_Time=completionTime;
            if(temp.brust_Time>0)
                store=temp;
        }
        double sum3=0;
        for(int i=0;i<n;i++)
        {
            sum3=sum3+priorityArray[i].waiting_Time;
        }
        System.out.println("Avarage waiting time is "+(sum3/n));
        System.out.println("GANTT CHART IS : "+ganttchart);
               
    }
    
// ----------------------- FUNCYIONALITY DEFINED FOR ROUND ROBIN -------------------------------------

   public void roundRobin(Process[] array, double timeSlice,  int n, double completionTime, Process store, String ganttchart)
    {
        ArrayList<Process> Queue = new ArrayList(n);
        Process[] roundRobinArray= new Process[n];
        for(int i=0;i<n;i++)
        {
            roundRobinArray[i] = new Process();
            roundRobinArray[i].arrival_Time=array[i].arrival_Time;
            roundRobinArray[i].brust_Time=array[i].brust_Time;
            roundRobinArray[i].priority=array[i].priority;
            roundRobinArray[i].process_Name=array[i].process_Name;
            roundRobinArray[i].waiting_Time=array[i].waiting_Time;
        }
        roundRobinArray=sort(roundRobinArray);
        int count=0;
        while(count<n)
        {
            while(true)
            {
                if(count<n && completionTime>=roundRobinArray[count].arrival_Time )
                { 
                   Queue.add(roundRobinArray[count]);
                   count++;
                }
                else
                {
                    break;
                }
            }
            if(store!=null)
            {
                Queue.add(store);
                store=null;
            }
            if(!Queue.isEmpty())
            {
                Process temp = Queue.remove(0);
                ganttchart=ganttchart+temp.process_Name;
                if(temp.brust_Time<=timeSlice)
                {
                    temp.waiting_Time=temp.waiting_Time+completionTime-temp.arrival_Time;
                    completionTime=completionTime+temp.brust_Time;
                    temp.arrival_Time=completionTime;
                    temp.brust_Time=0;
                }
                else{
                    temp.brust_Time=temp.brust_Time-timeSlice;
                    temp.waiting_Time=temp.waiting_Time+completionTime-temp.arrival_Time;
                    completionTime=completionTime+timeSlice;
                    temp.arrival_Time=completionTime;
                    if(temp.brust_Time>0)
                        store=temp;
                }
            }
            else
            {
                completionTime=roundRobinArray[count].arrival_Time;
            }
        }
        while(true)
        {
            if(store!=null)
            {
                Queue.add(store);
                store=null;
            }
            if(Queue.isEmpty())
                break;
            Process temp = Queue.remove(0);
            ganttchart=ganttchart+temp.process_Name;
            if(temp.brust_Time<=timeSlice)
            {
                temp.waiting_Time=temp.waiting_Time+completionTime-temp.arrival_Time;
                completionTime=completionTime+temp.brust_Time;
                temp.arrival_Time=completionTime;
                temp.brust_Time=0;
            }
            else{
                temp.brust_Time=temp.brust_Time-timeSlice;
                temp.waiting_Time=temp.waiting_Time+completionTime-temp.arrival_Time;
                completionTime=completionTime+timeSlice;
                temp.arrival_Time=completionTime;
                if(temp.brust_Time>0)
                    store=temp;
            }
        }
        double sum=0;
        for(int i=0;i<n;i++)
        {
            sum=sum+roundRobinArray[i].waiting_Time;
        }
        System.out.println("AVERAGE WAITING TIME FOR ROUND ROBIN IS : "+(sum/n));
        System.out.println("GANTT CHART IS : "+ganttchart);
    }
   
 // ----------------------- FUNCTIONALITY FOR SORT METHOD ------------------------------------------- 
    
   private static Process[] sort(Process[] arr)          
    {
        for(int i=arr.length-1; i<0; i++)
        {
            int sm = i;
            for(int j=i+1; j<arr.length; j++)
            {    
                if(arr[j].arrival_Time>arr[sm].arrival_Time)
                       sm=j;    
            }
            Process temp = arr[i];
            arr[i] = arr[sm];
            arr[sm] = temp;
        }
        return arr;
    }
    
// ----------------------- MAIN METHOD FOR USER INPUT ---------------------------------------------
   
    public static void main(String[] args) {
       
                
        Scanner sc = new Scanner(System.in);
        Preemptive obj = new Preemptive();
        
        
 // ---------------- ASKING USER FOR NUMBER OF PROCESSES ----------------------------------
        
        System.out.println("ENTER NUMBER OF PROCESSES : ");       
        int input1 = sc.nextInt(); 
        
 // ---------------- CREATING AN ARRAY OF TYPE PROCESS OF SIZE OF NUMBER OF PROCESS ENTERD BY USER ---------------
        
        Process processes[] = new Process[input1];  
        int n =processes.length;
        double completionTime=0;
        Process store=null;
        String ganttchart="";
        int c = 0;
        while(c < input1)
        {
           System.out.println( "PROCESS "+ (c+1) +" NAME \n"
                   + "BURST TIME \n"
                   + "ARRIVAL TIME \n"
                   + "PRIORITY \n");
           String name = sc.next();
           double bt = sc.nextDouble();
           double at = sc.nextDouble();
           double pp = sc.nextDouble();
           processes[c] = new Process(name,bt,at,pp);
           c++;
        }
  // ---------------------- PROMPTING THE USER FOR HIS CHOICE OF ALGORITHM CALCULATION ------------------------
        while(true)                             
        {
           System.out.println("MAKE YOUUR CHOICE :");
           System.out.println("PRESS 1 FOR SRF : ");
           System.out.println("PRESS 2 FOR PRIORITY SCHEDULING : ");
           System.out.println("PRESS 3 FOR ROUND ROBIN : ");
           int input2 = sc.nextInt();
           
 // ---------------------- USER CHOOSE SRF FCFS CALCULATION ----------------------------------
           if(input2 == 1)                
           {
               obj.SRF(processes, n, completionTime, store, ganttchart);
           }
 // ---------------------- USER CHOOSE FOR SJF CALCULATION ----------------------------------
           if(input2 == 2)              
           {
               obj.priorityPreemptive(processes, n, completionTime, store, ganttchart);
           }
 // ---------------------- USER CHOOSE FOR PRIORITY CALCULATION ----------------------------------
           if(input2 == 3)               
           {
 // --------------------- ASKING FRO TIME SLICE FROM USE --------------------------------------------
              System.out.println("ENTER TIME SLICE :");  
              double timeSlice = sc.nextDouble();
              obj.roundRobin(processes, timeSlice, n, completionTime, store, ganttchart);
           }
           System.out.println("DO YOU WANT TO PROCEED? ENTER '1' FOR CALCULATION OF OTHER ALGORITHMS OR PRESS 0 TO EXIT");
           if(!(sc.nextInt()==1))
           {
              break;
           }
           else
           {
              System.out.println("INVALID INPUT ! PLEASE TRY AGAIN ");
           }
        }
    }
        
}

// ------------------------------------- Process Class --------------------------------------------------------------

class Process
{
    
  public String process_Name ;
  public double brust_Time;
  public double arrival_Time;
  public double priority;
  public double waiting_Time;
   public Process()
    {
        
    }
    public Process(String process_Name, double brust_Time, double arival_Time, double priority) {
        this.process_Name = process_Name;
        this.brust_Time = brust_Time;
        this.arrival_Time = arival_Time;
        this.priority = priority;
    } 

}

// -------------------- COMPARING AND SORTING ARRAY ON THE BASIS OF BURST TIME FOR SJF ALGORITHM --------------------

class ComparingBursttTime implements Comparator<Process> 
{
     public int compare(Process a1,Process a2){
         String a= a1.brust_Time+"";
         String b= a2.brust_Time+"";
         return a.compareTo(b);      
    }
}

// ------------------ COMPARING AND SORTING ARRAY ON THE BASIS OF BURST TIME FOR PRIORITY ALGORITHM --------------------

class ComparingPriority implements Comparator<Process>
{

    public int compare(Process a1,Process a2){
         String a= a1.priority+"";
         String b= a2.priority+"";
         return a.compareTo(b);      
   }
}



// ------------------------------------------- THE END ---------------------------------------------------------------------
