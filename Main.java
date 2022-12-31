import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	
	static int total_weight = 30*60*1000;
	
        public static void main(String[] args) throws IOException{
                List<List<String>> list = readValues();
                System.out.println(list.get(0));
		List<Integer> valueList = new ArrayList<Integer>();
		List<Integer> weightList = new ArrayList<Integer>();
		for(int i=1;i<list.size();i++) {
			valueList.add(Integer.parseInt(list.get(i).get(4))); 
			weightList.add(Integer.parseInt(list.get(i).get(5)));
			}
		List<List<String>> list1 = readSequential();
		List<ArrayList<Double>> sequential_data = new ArrayList<ArrayList<Double>>();
		for (int i=1;i<list1.size();i++){
			ArrayList<Double> row = new ArrayList<>();
			for (int j=1;j<list1.get(0).size();j++){
				row.add(Double.parseDouble(list1.get(i).get(j)));
			}
			sequential_data.add(row);
		}
		System.out.println(sequential_data.get(0).get(1));
		
		ArrayList<Double> ratioList = new ArrayList<>();
		for(int i=0 ; i<valueList.size();i++) {
			ratioList.add((double)valueList.get(i)/weightList.get(i));
		}
		
		ArrayList<Integer> sortedList = q1(ratioList, weightList);
		for(int i=0; i<sortedList.size();i++) {
			System.out.print(sortedList.get(i)+" ");
		}
		
		double total_duration = 30*60*1000-total_weight;
		System.out.println();
		System.out.println("Total sec used: "+ total_duration/1000);
		
		ArrayList<Integer> new_sortedList = q2(sortedList,sequential_data);
		for(int i=0; i<new_sortedList.size();i++) {
			System.out.print(new_sortedList.get(i)+" ");
		}
		
        }
        
        public static ArrayList<Integer> q1(ArrayList<Double> ratioList, List<Integer> weightList){
        	boolean[] flagList = new boolean[ratioList.size()];
        	int counter = 0;
        	ArrayList<Integer> sorted_values = new ArrayList<>();
        	while(total_weight>0 && counter <ratioList.size()) {
        		double max = 0;
            	int loc = 0;
        		for(int i = 0; i<ratioList.size(); i++) {
        			if(max <ratioList.get(i) && flagList[i]==false) {
        				max = ratioList.get(i);
        				loc = i;
        			}
        		}
        		flagList[loc] = true;
        		counter++;
        		if(total_weight-weightList.get(loc)<0) {
        			continue;
        		}
        		sorted_values.add(loc);
        		total_weight -= weightList.get(loc);
        	}
        	
        	return sorted_values;
        }
        
        public static ArrayList<Integer> q2(ArrayList<Integer> sortedList, List<ArrayList<Double>> seqList){
        	ArrayList<Integer> new_sortedList = new ArrayList<Integer>();
        	boolean[] flagList = new boolean[sortedList.size()];
        	new_sortedList.add(sortedList.get(0));
        	flagList[0]=true;
        	int index = sortedList.get(0);
        	
        	while(new_sortedList.size()<sortedList.size()-1) {
        		Double max = 0.0;
        		int loc = 0;
        		for(int i=2; i<sortedList.size();i++) {
        			if(max<seqList.get(index).get(sortedList.get(i)) && flagList[i]==false) {
        				max = seqList.get(index).get(sortedList.get(i));
        				loc = i;
        				System.out.println(max);
        			}
        		}
        		System.out.println("MAX: "+max);
        		System.out.println("for bitti");
        		
        		flagList[loc] = true;
        		index = sortedList.get(loc);
        		new_sortedList.add(sortedList.get(loc));
        	}
        	new_sortedList.add(sortedList.get(1));
        	
        	return new_sortedList;
        }
        
        public static List<List<String>> readValues() throws IOException { 
                try
		{
			List< List<String> > data = new ArrayList<>();//list of lists to store data
			String file = "term_project_value_data.csv";//file path
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			//Reading until we run out of lines
			String line = br.readLine();
			while(line != null)
			{
				List<String> lineData = Arrays.asList(line.split(","));//splitting lines
				data.add(lineData);
				line = br.readLine();
			}
			
			//printing the fetched data
			for(List<String> list : data)
			{
				for(String str : list)
					System.out.print(str + " ");
				System.out.println();
			}
			br.close();
                        return data;
		}
		catch(Exception e)
		{
			System.out.print(e);
                        List< List<String> > data = new ArrayList<>();//list of lists to store data
                        return data;
		}
                
        }
	public static List<List<String>> readSequential() throws IOException { 
                try
		{
			List< List<String> > data = new ArrayList<>();//list of lists to store data
			String file = "term_project_sequential_data.csv";//file path
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			//Reading until we run out of lines
			String line = br.readLine();
			while(line != null)
			{
				List<String> lineData = Arrays.asList(line.split(","));//splitting lines
				data.add(lineData);
				line = br.readLine();
			}
			
			//printing the fetched data
			for(List<String> list : data)
			{
				for(String str : list)
					System.out.print(str + " ");
				System.out.println();
			}
			br.close();
                        return data;
		}
		catch(Exception e)
		{
			System.out.print(e);
                        List< List<String> > data = new ArrayList<>();//list of lists to store data
                        return data;
		}
                
        }
}