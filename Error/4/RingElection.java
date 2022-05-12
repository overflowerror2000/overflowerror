import java.util.Scanner; 

class Process { 

    int processId; 
    boolean active; 

    public Process(int processId) { 
        this.processId = processId; 
        active = true; 
    } 
} 

public class RingElection { 
     
    private Scanner consoleInput; 
    private Process[] process; 
    private int NosOfProcess; 

    public RingElection() { 
        System.out.println("\n\t\tRing Coordinator Election Algorithm\n"); 
        consoleInput = new Scanner(System.in); 
    } 

    public void getInput() { 
        System.out.println("Enter number of process in ring: "); 
        NosOfProcess = consoleInput.nextInt(); 
        process = new Process[NosOfProcess]; 

        for (int i = 0; i < NosOfProcess; i++) { 
            System.out.print("Enter Process ID of p" + i + ": "); 
            int pid = consoleInput.nextInt(); 
            initializeProcess(i, pid); 
        } 
        sortProcess(); 
        putOutput(); 
    } 

    private void initializeProcess(int i, int pid) { 
        process[i] = new Process(pid); 
    } 

    public void conductElection() { 
                 
        try{ 
            Thread.sleep(2000); 
        }catch(Exception e ){ 
            System.out.println(e); 
        } 
        System.out.println("process "+ process[getMax()].processId +" Fail"); 
        process[NosOfProcess-1].active = false; 
         
        while(true){ 
            System.out.print("Conduct Election?\nyes or exit: "); 
            String choice = consoleInput.next(); 
            if("yes".equals(choice) || "Yes".equals(choice) || "y".equals(choice) || "Y".equals(choice)){ 
                System.out.println("Election initiated by: "); 
                int initiatorProcess = consoleInput.nextInt(); 
                for(int i = 0; i< NosOfProcess; i++){ 
                    if(process[i].processId == initiatorProcess){ 
                        initiatorProcess = i; 
                        break; 
                    } 
                } 
                int prev = initiatorProcess; 
                int next = prev+1; 
                
                while(true){ 
                    if(process[next].active) { 
                        System.out.println("Process "+ process[prev].processId +" pass message to process "+process[next].processId ); 
                        prev = next; 
                    } 
                    next = (next+1) % NosOfProcess; 
                     
                    if(next == initiatorProcess) { 
                        break; 
                    } 
                } 
                System.out.println("Process "+ process[getMax()].processId +" becomes coordinator"); 
            } else { 
                System.exit(0);             
            } 
        } 
    } 
     
    public void putOutput(){ 
        System.out.println("Processes in the ring: ");    
        for(int i = 0; i < NosOfProcess; i++){ 
            System.out.print(process[i].processId +", active: "+ process[i].active); 
            if(i == getMax()){ 
                System.out.print(", Coordinator\n"); 
            }else { 
                System.out.print("\n"); 
            } 
             
        } 
    } 
     
    private void sortProcess() { 
        for (int i = 0; i < NosOfProcess - 1; i++) { 
            for (int j = 0; j < (NosOfProcess - i) -1; j++) { 
                if (process[j].processId > process[j + 1].processId) { 
                    int temp = process[j].processId; 
                    process[j].processId = process[j + 1].processId; 
                    process[j + 1].processId = temp; 

                } 
            } 
        } 
    } 
     
    private int getMax(){ 
        int max = 0, indexOfMax = 0; 
        for(int i = 0; i < NosOfProcess; i++){ 
            if(process[i].active && max <= process[i].processId ) { 
                max = process[i].processId; 
                indexOfMax = i; 
            } 
        } 
        return indexOfMax; 
    } 

    public static void main(String arg[]) { 
        RingElection ringElection = new RingElection(); 
        ringElection.getInput(); 
        ringElection.conductElection(); 
     
    } 
} 
/*run:

		Ring Coordinator Election Algorithm

Enter number of process in ring: 
6
Enter Process ID of p0: 1
Enter Process ID of p1: 2
Enter Process ID of p2: 3
Enter Process ID of p3: 4
Enter Process ID of p4: 5
Enter Process ID of p5: 6
Processes in the ring: 
1, active: true
2, active: true
3, active: true
4, active: true
5, active: true
6, active: true, Coordinator
process 6 Fail
Conduct Election?
yes or exit: yes
Election initiated by: 
3
Process 3 pass message to process 4
Process 4 pass message to process 5
Process 5 pass message to process 1
Process 1 pass message to process 2
Process 5 becomes coordinator
Conduct Election?
yes or exit: exit
BUILD SUCCESSFUL (total time: 25 seconds)
*/
