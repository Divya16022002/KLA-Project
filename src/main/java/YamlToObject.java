import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Date;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class YamlToObject {

	public static void main(String[] args) throws Exception {
		MileStone1();
		//MileStone2();
		
	}
	
	public static void MileStone1() throws Exception {
		InputStream inputstream=new FileInputStream("C:\\Users\\91868\\Downloads\\DataSet\\Milestone1\\Milestone1A.yaml");
	    Yaml yaml=new Yaml(new Constructor(YamlSubstitute.class));
		YamlSubstitute data=yaml.load(inputstream);
		System.out.println(data.M1A_Workflow.Type);
		PrintWriter out = new PrintWriter("Milestone1A_Log.txt");
		
		if(data.M1A_Workflow.Execution.equals("Sequential")) {
			out.println(new Timestamp(new Date().getTime()).toString()+";"+data.toString() +" Entry");
			Activity act=data.M1A_Workflow.Activities;
			Activity2 act1=data.M1A_Workflow.Activities.FlowA.Activities;
			Field[] f=act.getClass().getDeclaredFields();
			Field[] f1=act1.getClass().getDeclaredFields();
			printElements(data.toString(),out,data.M1A_Workflow.Activities.TaskA,act,0);
			printElements(data.toString(),out,data.M1A_Workflow.Activities.TaskB,act,1);
			out.println(new Timestamp(new Date().getTime()).toString()+";"+data.toString()+"."+f[2].getName()  +" Entry");
			printElementsforFlow(data.toString(),out,f[2].getName(),data.M1A_Workflow.Activities.FlowA.Activities.TaskC,act1,0);
			printElementsforFlow(data.toString(),out,f[2].getName(),data.M1A_Workflow.Activities.FlowA.Activities.TaskD,act1,1);
			out.println(new Timestamp(new Date().getTime()).toString()+";"+data.toString()+"."+f[2].getName()  +" Exit");
			out.println(new Timestamp(new Date().getTime()).toString()+";"+data.toString() +" Exit");
		}
		else {
			
		}
		out.close();
	}
	
	public static void MileStone2() throws Exception {
		InputStream inputstream=new FileInputStream("C:\\Users\\91868\\Downloads\\DataSet\\Milestone1\\Milestone1B.yaml");
	    Yaml yaml=new Yaml(new Constructor(YamlSubstitute.class));
		YamlSubstitute data=yaml.load(inputstream);
		System.out.println(data.M1A_Workflow.Type);
		PrintWriter out = new PrintWriter("Milestone1B_Log.txt");
		
		if(data.M1A_Workflow.Execution.equals("Sequential")) {
			out.println(new Timestamp(new Date().getTime()).toString()+";"+data.toString() +" Entry");
			Activity act=data.M1A_Workflow.Activities;
			Activity2 act1=data.M1A_Workflow.Activities.FlowA.Activities;
			Field[] f=act.getClass().getDeclaredFields();
			Field[] f1=act1.getClass().getDeclaredFields();
			printElements(data.toString(),out,data.M1A_Workflow.Activities.TaskA,act,0);
			printElements(data.toString(),out,data.M1A_Workflow.Activities.TaskB,act,1);
			printElementsforFlow(data.toString(),out,f[2].getName(),data.M1A_Workflow.Activities.FlowA.Activities.TaskC,act1,0);
			printElementsforFlow(data.toString(),out,f[2].getName(),data.M1A_Workflow.Activities.FlowA.Activities.TaskD,act1,1);
			out.println(new Timestamp(new Date().getTime()).toString()+";"+data.toString() +" Exit");
		}
		else {
			
		}
		out.close();
	}
	
	public static void printElements(String flow,PrintWriter out,Task task,Activity act,int i) throws NumberFormatException, InterruptedException {
		Field[] f=act.getClass().getDeclaredFields();
		out.println(new Timestamp(new Date().getTime()).toString()+";"+flow+"."+f[i].getName()  +" Entry");
		out.println(new Timestamp(new Date().getTime()).toString()+";"+flow+"."+f[i].getName()  +" Executing "+task.Function+"("+task.Inputs.FunctionInput+","+task.Inputs.ExecutionTime+")");
		Thread.sleep(Long.parseLong(task.Inputs.ExecutionTime)*1000);
		out.println(new Timestamp(new Date().getTime()).toString()+";"+flow+"."+f[i].getName()  +" Exit");
	}
	
	public static void printElementsforFlow(String flow,PrintWriter out,String flowName,Task task,Activity2 act,int i) throws NumberFormatException, InterruptedException {
		Field[] f=act.getClass().getDeclaredFields();
		System.out.println(f[0].getName());
		System.out.println(f[1].getName());
		out.println(new Timestamp(new Date().getTime()).toString()+";"+flow+"."+flowName+"."+f[i].getName()  +" Entry");
		out.println(new Timestamp(new Date().getTime()).toString()+";"+flow+"."+flowName+"."+f[i].getName()  +" Executing "+task.Function+"("+task.Inputs.FunctionInput+","+task.Inputs.ExecutionTime+")");
		Thread.sleep(Long.parseLong(task.Inputs.ExecutionTime)*1000);
		out.println(new Timestamp(new Date().getTime()).toString()+";"+flow+"."+flowName+"."+f[i].getName()  +" Exit");
	}
	

}
