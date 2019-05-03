/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dsproject;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author saeid zangeneh
 */
public class Runner {


    public static List<String> us;


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        run();

    }
    public static void run() throws NumberFormatException, IOException {
        // TODO code application logic here
        fileCleaner();
        Scanner scnIn = new Scanner(System.in);

        System.out.println(
                "please select one of the options:"
                + "\n1-signUp new user"
                + "\n2-login"
                + "\n3-delete user"
                + "\n4-count votes"
                + "\n5-search by user"
                + "\n6-find all occurrences"
                + "\n7-search qoute by id"
                + "\n8-search by author"
                + "\n9-top qoutes"
                + "\n10-most recent qoutes"
        );
        int input = scnIn.nextInt();

        afterRun(input, scnIn);
    }
    public static void afterRun(int input, Scanner scnIn) throws IOException, NumberFormatException {
        fileCleaner();
        if (input == 1) {
            //sign up=creating new user
            System.out.println("please enter the username :");
            String userName = scnIn.next();
            User u = new User(userName);
            String latestUserId = new String(Files.readAllBytes(Paths.get("latestUserId.txt")), StandardCharsets.UTF_8);
            u.setUserId(Integer.parseInt(latestUserId)+1);
            System.out.println("congrajulation you are signed up and your id is:" + (Integer.parseInt(latestUserId) + 1));
            File f=new File("users\\"+u.getUserId()+".txt");
            FileOutputStream fout=new FileOutputStream(f);
            byte[] b="==".getBytes();
            fout.write(b);
            fout.close();
       
            ///saving new user id in the file
            FileWriter fw = new FileWriter("latestUserId.txt");
            fw.write(Integer.parseInt(latestUserId) + 1 + "");
            fw.close();
            
            
            String users=new String(Files.readAllBytes(Paths.get("Users.txt")),StandardCharsets.UTF_8);
           
            FileWriter fw1 = new FileWriter("Users.txt");
            fw1.append(users+"=="+userName+","+(Integer.parseInt(latestUserId) + 1));
            fw1.close();
            
            run();

        } else if (input == 2) {

            //sign in
            System.out.println("please enter userName then press enter:");
            String inUName = scnIn.next();
         

            if (Login(inUName) == true) {
                System.out.println("you are loggedIn\n");
                User loggedInUser = new User(inUName,  Integer.parseInt(us.get(1)));
                System.out.println("please select one of below options :\n"
                        + "1-Add a new qoute.\n"
                        + "2-voting an exist qoute\n"
                        + "3-devoting an exist qoute\n"
                        + "4-deleting a qoute\n"
                        + "5-log out\n"
                );
                int i = scnIn.nextInt();

                switch (i) {
                    case 1:
                        //a method for craeting vote
                        createQoute(loggedInUser);
                        break;
                    case 2:
                        //voter with (1,loggedInUser) parameter=logedInUser want to vote a qoute
                        voter(1, loggedInUser);
                        break;
                    case 3:
                        //voter with (2,loggedInUser) parameter=logedInUser want to dvote a qoute
                        voter(2, loggedInUser);
                        break;
                    case 4:
                        //deleting some vote
                        delete("qoutes.txt");
                        break;
                    case 5:
                        //logginig out
                        run();
                        break;

                    default:
                        break;
                }
            } else {
                System.out.println("userName is not correct !");
            }

        } else if (input == 3) {
            delete("Users.txt");
        } else if (input == 4) {
            String str = new String(Files.readAllBytes(Paths.get("qoutes.txt")), StandardCharsets.UTF_8);
            List<String> qoutes = Arrays.asList(str.split("\\s*==\\s*"));
            System.out.println("please enter the qoute id:");
            int qouteId = scnIn.nextInt();
            List<String> selectedQoute = null;
            for (String userQoute : qoutes) {
                selectedQoute = Arrays.asList(userQoute.split("\\s*,\\s*"));
                if (Integer.parseInt(selectedQoute.get(1)) == qouteId) {
                    System.out.println("the number of postive votes is:" + selectedQoute.get(2) + "\n\n"
                            + "the number of negative votes is:" + selectedQoute.get(2));
                    break;
                }
            }
    //any qoute has the structure like :qouteContent,qouteID,posVotes,negVotes,userID,savedOrNot,author
        } else if (input == 5) {
            String str = new String(Files.readAllBytes(Paths.get("qoutes.txt")), StandardCharsets.UTF_8);
            List<String> qoutes = Arrays.asList(str.split("\\s*==\\s*"));
            System.out.println("please enter user id:");
            int userID = scnIn.nextInt();
            List<String> selectedQoute = null;
            for (String userQoute : qoutes) {
                selectedQoute = Arrays.asList(userQoute.split("\\s*,\\s*"));
                if (Integer.parseInt(selectedQoute.get(4)) == userID) {
                    System.out.println("the content of qoute is:" + selectedQoute.get(0) + "\n"
                            + "the author(owner) of the qoute is:" + selectedQoute.get(6));
                    
                }
            }

        } else if (input == 6) {
            String str = new String(Files.readAllBytes(Paths.get("qoutes.txt")), StandardCharsets.UTF_8);
            List<String> qoutes = Arrays.asList(str.split("\\s*==\\s*"));
            System.out.println("please enter string :");
            String inputt= scnIn.next();
            List<String> selectedQoute = null;
            for (String userQoute : qoutes) {
                selectedQoute = Arrays.asList(userQoute.split("\\s*,\\s*"));
                if ( selectedQoute.get(0).contains(inputt)) {
                    System.out.println(selectedQoute.toString());
                }
            }

        } else if (input == 7) {
             String str = new String(Files.readAllBytes(Paths.get("qoutes.txt")), StandardCharsets.UTF_8);
            List<String> qoutes = Arrays.asList(str.split("\\s*==\\s*"));
            int qouteId = scnIn.nextInt();
            List<String> selectedQoute = null;
            for (String userQoute : qoutes) {
                selectedQoute = Arrays.asList(userQoute.split("\\s*,\\s*"));
                if (Integer.parseInt(selectedQoute.get(1)) == qouteId) {
                    System.out.println(selectedQoute.toString());
                    break;
                }
            }
            

        } else if (input == 8) {
            String str = new String(Files.readAllBytes(Paths.get("qoutes.txt")), StandardCharsets.UTF_8);
            List<String> qoutes = Arrays.asList(str.split("\\s*==\\s*"));
            String inputt= scnIn.nextLine();
            List<String> selectedQoute = null;
            for (String userQoute : qoutes) {
                selectedQoute = Arrays.asList(userQoute.split("\\s*,\\s*"));
                if ( selectedQoute.get(6).equals(inputt)) {
                    System.out.println("qotes that you searched is :"+selectedQoute.toString());
                }
            }

        } else if (input == 9) {
            System.out.println("please enter the number of recents that you want to see:");
            int k=scnIn.nextInt()+1;
            
             String str = new String(Files.readAllBytes(Paths.get("qoutes.txt")), StandardCharsets.UTF_8);
            List<String> qoutes = Arrays.asList(str.split("\\s*==\\s*"));
            
            String[]  topvoteQoutes=new String[qoutes.size()];
            int[]   topvotes=new int[qoutes.size()];
            
            List<String> selectedQoute = null;
            int i=0;
            for (String userQoute : qoutes) {
                selectedQoute = Arrays.asList(userQoute.split("\\s*,\\s*"));
                //computting difference of upvote and down vote for each node
                topvotes[i]=Integer.parseInt(selectedQoute.get(2))-Integer.parseInt(selectedQoute.get(3));
                topvoteQoutes[i]=selectedQoute.get(0);
                i++;
            }
            //using bubblesort
            bubbleSort(topvotes, topvoteQoutes,k);
            
            
            

        } else if (input == 10) {
            System.out.println("please enter the number of recents that you want to see:");
            int k=scnIn.nextInt();
            String str = new String(Files.readAllBytes(Paths.get("qoutes.txt")), StandardCharsets.UTF_8);
            List<String> qoutes = Arrays.asList(str.split("\\s*==\\s*"));
            List<String> selectedQoute = null;   
            try{
            for (int i = k-1; i>=0; i--) {
                selectedQoute = Arrays.asList(qoutes.get(i).split("\\s*,\\s*"));
                System.out.println(selectedQoute.get(0));
            }
            }catch(java.lang.ArrayIndexOutOfBoundsException e){
                System.out.println("there is not much enough qoute !" );
            }

        }
    }
    //////a method for checking username 
    private static boolean Login(String inUName) throws IOException {
        fileCleaner();
        String str = new String(Files.readAllBytes(Paths.get("Users.txt")), StandardCharsets.UTF_8);
        List<String> ml = Arrays.asList(str.split("\\s*==\\s*"));
        boolean result = false;

        for (String string : ml) {
            if (string.contains(inUName)) {
                result = true;
                us = Arrays.asList(string.split("\\s*,\\s*"));
            }
            fileCleaner();

        }

//a method for creating nodes
        return result;
    }
    //any qoute has the structure like :qouteContent,qouteID,posVotes,negVotes,userID,savedOrNot,author
    private static void createQoute(User user) throws IOException {
        fileCleaner();
        //reading the latest qoute id 
        String str = new String(Files.readAllBytes(Paths.get("latestQID.txt")), StandardCharsets.UTF_8);
        Scanner scan = new Scanner(System.in);
        int qid = Integer.parseInt(str);
        //creating new qoute and getting information from the user
        Quote q = new Quote();
        q.setQID(qid + 1);
        q.setUID(user.getUserId());
         ///saving new user id in the file
            FileWriter fw = new FileWriter("latestQID.txt");
            fw.write((qid+ 1)+"");
            fw.close();
        System.out.println("please enter the qoute content and press enter:");
        q.setContent(scan.nextLine());
        System.out.println("please enter the name of qoute owner (the person who said this qout):");
        q.setQouteOwner(scan.nextLine());
        //reading nodes and adding new qoute to the qoute file
        String str1 = new String(Files.readAllBytes(Paths.get("qoutes.txt")), StandardCharsets.UTF_8);
        FileWriter fileWriter = new FileWriter("qoutes.txt");

        fileWriter.append(str1 + q.toSstring(q) + "==");
        fileWriter.close();
        //printing information to the user
        System.out.println("the qoute saved by this info :"
                + "\nqouteContent,qouteID,posVotes,negVotes,WriterID,savedOrNot,owner\n" + q.toSstring(q));
        fileCleaner();
    }
    //a method for voting nodes
    private static void voter(int i, User loggedInUser) throws IOException {
       
              
        fileCleaner();
        //geeting qoute id to can find it in file
        System.out.println("please enter qoute ID :");
        Scanner in = new Scanner(System.in);
        int qouteId = in.nextInt();
        //checking that user voted this qoute or not 
        if (userVotedThisQoute(qouteId, loggedInUser.getUserId()) == 0) {
            File f=new File("users\\"+loggedInUser.getUserId()+".txt");
            FileOutputStream fout2=new FileOutputStream(f);
            byte[] g=(qouteId+"==").getBytes();
            fout2.write(g);
            fout2.close();
            
            //reading votes from file
            String str = new String(Files.readAllBytes(Paths.get("qoutes.txt")), StandardCharsets.UTF_8);
            //writting votes in a list for flexability
            List<String> qoutes = Arrays.asList(str.split("\\s*==\\s*"));

            //a counter for knowing wich qoute should be vote 
            int counter = 0;
            //
            List<String> selectedQoute = null;
            for (String userQoute : qoutes) {
                counter++;
                selectedQoute = Arrays.asList(userQoute.split("\\s*,\\s*"));
                if (Integer.parseInt(selectedQoute.get(1)) == qouteId) {
                    System.out.println(selectedQoute.toString());
                    System.out.println("the qoute is found :\n" + selectedQoute.get(0) + " and the owner is: " + selectedQoute.get(6));
                    break;

                } else {
                    System.out.println("there is no qoute with this id !!");
                }
            }
            //getting the postive and negative votes to change
            int pVote = Integer.parseInt(selectedQoute.get(2));
            System.out.println("postive vote:" + pVote);
            int nVote = Integer.parseInt(selectedQoute.get(3));
            System.out.println("negetive votes:" + nVote);
            //voting
            if (i == 1) {
                pVote += 1;
                if (pVote >= nVote + 3) {
                    saveqoute(selectedQoute);
                    selectedQoute.set(5,"1");
                }
                System.out.println("the posetive vote incresead from  " + (pVote - 1) + "  to  " + pVote);
            } else //devoting
            {
                if (i == 2) {
                    if (pVote >= nVote + 3) {
                        if (!(nVote - 1 >= pVote + 3)) {
                            delete("savedQoutes",Integer.parseInt(selectedQoute.get(1)));
                            selectedQoute.set(5,"0");
                        } else {
                        }
                    } else {
                    }
                    nVote += 1;
                    System.out.println("the negative vote decreased from  " + (nVote - 1) + "  to  " + nVote);
                } //wrrong
                else {
                    System.out.println("the input number is not correct !");
                }
            }
            //setting the vote to the text
            selectedQoute.set(2, pVote + "");
            selectedQoute.set(3, nVote + "");

            qoutes.set(counter - 1, selectedQoute.get(0) + "," + selectedQoute.get(1) + "," + selectedQoute.get(2) + "," + selectedQoute.get(3) + "," + selectedQoute.get(4) + "," + selectedQoute.get(5) + "," + selectedQoute.get(6));

            String res = "";
            for (String qoute : qoutes) {
                res += qoute + "==";
            }
            ///rewritting the voted nodes
            FileWriter fileWriter;

            fileWriter = new FileWriter("qoutes.txt");
            fileWriter.write(res);
            fileWriter.close();
        } else {
            //if the user voted this qoute once he/she can't voe again
            System.out.println("you can not vote this qoute because you voted this qoute!");
        }

    }
    private static void delete(String path) throws IOException {
        fileCleaner();
        Scanner input = new Scanner(System.in);
        System.out.println("please enter the id that you want to delete :");
        int id = input.nextInt();
        //reading nodes from file
        String str = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
        //writting nodes in a list for flexability
        List<String> nodes = Arrays.asList(str.split("\\s*==\\s*"));
        //a counter for knowing wich node should be taken 
        int counter = 0;
        List<String> selectedQoute = null;
        for (String userQoute : nodes) {
            counter++;
            selectedQoute = Arrays.asList(userQoute.split("\\s*,\\s*"));
            if (id == Integer.parseInt(selectedQoute.get((1)))) {
                nodes.set(counter - 1, "");
                String res = "";
                for (String qoute : nodes) {
                    res += qoute + "==";
                }
             fileCleaner();
                ///rewritting the nodes 
                FileWriter fileWriter;
                fileWriter = new FileWriter("path");
                fileWriter.write(res);
                fileWriter.close();
                System.out.println("the node was deleted successfully");

                break;

            } else if (counter == nodes.size()) {
                System.out.println("there is no qoute with this id !!");
            }
        }
    }
    private static void delete(String path,int id) throws IOException {
        fileCleaner();
        //reading nodes from file
        String str = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
        //writting nodes in a list for flexability
        List<String> nodes = Arrays.asList(str.split("\\s*==\\s*"));
        //a counter for knowing wich node should be taken 
        int counter = 0;
        List<String> selectedQoute = null;
        for (String userQoute : nodes) {
            counter++;
            selectedQoute = Arrays.asList(userQoute.split("\\s*,\\s*"));
            if (id == Integer.parseInt(selectedQoute.get((1)))) {
                nodes.set(counter - 1, "");
                String res = "";
                for (String qoute : nodes) {
                    res += qoute + "==";
                }
             fileCleaner();
                ///rewritting the nodes 
                FileWriter fileWriter;
                fileWriter = new FileWriter("path");
                fileWriter.write(res);
                fileWriter.close();
                System.out.println("the node was deleted successfully");

                break;

            } else if (counter == nodes.size()) {
                System.out.println("there is no qoute with this id !!");
            }
        }
    }
    //a method for cheking that user voted this qoute id or not
    private static int userVotedThisQoute(int qouteId, int userId) throws IOException {
        fileCleaner();
        int a = 0;
        String userVotedQoutesID = new String(Files.readAllBytes(Paths.get("users\\"+userId +".txt")), StandardCharsets.UTF_8);
        List<String> qouteIDS = Arrays.asList(userVotedQoutesID.split("\\s*,\\s*"));
        for (String qoute : qouteIDS) {
            if (qouteId == Integer.parseInt(qoute)) {
                a = 1;
            }
        }

        return a;

    }
    private static void saveqoute(List<String> selectedQoute) throws IOException {
        
        fileCleaner();
        String str=new String(Files.readAllBytes(Paths.get("savedQoutes")),StandardCharsets.UTF_8);
        FileWriter SaveQoute=new FileWriter("savedQoutes");
        SaveQoute.write(str+selectedQoute.get(0)+","+selectedQoute.get(1)+","+selectedQoute.get(2)+","+selectedQoute.get(3)+","+selectedQoute.get(4)+","+selectedQoute.get(5)+","+selectedQoute.get(6)+"==");
        SaveQoute.close();
        fileCleaner();

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public  static void bubbleSort(int[] arr,String[] qoute,int k) {  
        int n = arr.length;  
        int temp = 0;
        String tmpstr="";
         for(int i=0; i < n; i++){  
                 for(int j=1; j < (n-i); j++){  
                          if(arr[j-1] > arr[j]){  
                                 //swap elements  
                                 temp = arr[j-1]; 
                                 tmpstr=qoute[j-1];
                                 
                                 arr[j-1] = arr[j]; 
                                 qoute[j-1]=qoute[j];
                                 
                                 arr[j] = temp;  
                                 qoute[j]=tmpstr;
                         }  
                          
                 }  
         }
         for (int i = arr.length-1; i > arr.length-k; i--) {
             System.out.println(qoute[i]+"  and the vote is :"+arr[i]);
       }
  
    }  
    public static void fileCleaner() throws IOException{
        String path1="qoutes.txt";
        String path2="Users.txt";
        String path3="savedQoutes.txt";
        String[] content=new String[3];
        content[0]=new String(Files.readAllBytes(Paths.get(path1)),StandardCharsets.UTF_8);
        content[1]=new String(Files.readAllBytes(Paths.get(path2)),StandardCharsets.UTF_8);
        content[2]=new String(Files.readAllBytes(Paths.get(path3)),StandardCharsets.UTF_8);
        
        for(int i=0;i<3;i++){
            if(content[i].contains("====")){
                content[i]=content[i].replace("====", "==");
            }
            if(content[i].startsWith("==")){
              content[i] = content[i].replaceFirst("==", "");
            }
        }
        FileWriter fw1=new FileWriter(path1);
        fw1.write(content[0]);
        fw1.close();
        
        FileWriter fw2=new FileWriter(path2);
        fw2.write(content[1]);
        fw2.close();
        
        FileWriter fw3=new FileWriter(path3);
        fw3.write(content[2]);
        fw3.close();

    }

}

